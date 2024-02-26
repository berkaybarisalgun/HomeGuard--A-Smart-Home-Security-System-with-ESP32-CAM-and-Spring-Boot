#define CAMERA_MODEL_AI_THINKER
#include "Arduino.h"
#include "CameraManager.h"
#include "camera_pins.h"

#define FLASH_GPIO 4 // GPIO pin used for the flash

CameraManager::CameraManager() {
  // Content for the constructor can be written here when needed
}

void CameraManager::init() {
  camera_config_t config;
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0 = Y2_GPIO_NUM;
  config.pin_d1 = Y3_GPIO_NUM;
  config.pin_d2 = Y4_GPIO_NUM;
  config.pin_d3 = Y5_GPIO_NUM;
  config.pin_d4 = Y6_GPIO_NUM;
  config.pin_d5 = Y7_GPIO_NUM;
  config.pin_d6 = Y8_GPIO_NUM;
  config.pin_d7 = Y9_GPIO_NUM;
  config.pin_xclk = XCLK_GPIO_NUM;
  config.pin_pclk = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href = HREF_GPIO_NUM;
  config.pin_sccb_sda = SIOD_GPIO_NUM;
  config.pin_sccb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn = PWDN_GPIO_NUM;
  config.pin_reset = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.pixel_format = PIXFORMAT_JPEG;
  config.frame_size = FRAMESIZE_UXGA;
  config.jpeg_quality = 12;
  config.fb_count = 1;

  // Initialize the camera module
  esp_err_t err = esp_camera_init(&config);
  if (err != ESP_OK) {
    Serial.printf("Camera init failed with error 0x%x", err);
    return;
  }
  Serial.println("Camera initialized");

  pinMode(FLASH_GPIO, OUTPUT); // Set the flash GPIO as OUTPUT
  digitalWrite(FLASH_GPIO, LOW); // Keep the flash off initially
}

camera_fb_t* CameraManager::capture() {
  enableFlash(true); // Turn on the flash
  camera_fb_t* fb = esp_camera_fb_get();
  if (!fb) {
    Serial.println("Camera capture failed");
    enableFlash(false); // Turn off the flash in case of error
    return nullptr;
  }
  Serial.print("Captured photo, size: ");
  Serial.println(fb->len);
  enableFlash(false); // Turn off the flash after capturing the photo
  return fb;
}

void CameraManager::release(camera_fb_t* fb) {
  if (fb) {
    esp_camera_fb_return(fb);
  }
}

void CameraManager::enableFlash(bool enable) {
  digitalWrite(FLASH_GPIO, enable ? HIGH : LOW); // Turn the flash LED on or off
}
