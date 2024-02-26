#include "ConnectivityManager.h"
#include "Arduino.h"


ConnectivityManager::ConnectivityManager(const char* ssid, const char* password, const char* serverUrl)
  : _ssid(ssid), _password(password), _serverUrl(serverUrl) {}

void ConnectivityManager::connectToWiFi() {
  WiFi.begin(_ssid, _password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("WiFi connected");
}

void ConnectivityManager::sendPhoto(camera_fb_t* fb) {
  if (fb == nullptr) return;

  HTTPClient http;
  http.begin(_serverUrl);
  http.addHeader("Content-Type", "image/jpeg");

  int httpResponseCode = http.POST(fb->buf, fb->len);
  if (httpResponseCode > 0) {
    Serial.println("Photo sent successfully");
  } else {
    Serial.println("Error sending photo");
  }
  http.end();
}
