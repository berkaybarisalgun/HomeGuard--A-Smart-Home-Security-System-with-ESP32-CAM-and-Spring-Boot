#include "CameraManager.h"
#include "ConnectivityManager.h"
#include "MotionSensorManager.h"
#include "UltrasonicSensorManager.h"

// The specified connection information, motion sensor pin, and ultrasonic sensor pins
const char* ssid = "yourSSID";
const char* password = "yourpassword";
const char* serverUrl = "http://192.168.1.109:8080/api/images/";
const int motionSensorPin = 15; // Motion sensor pin number
const int trigPin = 13; // Ultrasonic sensor Trig pin number (Update the pin numbers)
const int echoPin = 14; // Ultrasonic sensor Echo pin number (Update the pin numbers)

CameraManager cameraManager;
ConnectivityManager connectivityManager(ssid, password, serverUrl);
MotionSensorManager motionSensor(motionSensorPin);
UltrasonicSensorManager ultrasonicSensor(trigPin, echoPin); // UltrasonicSensorManager object

void setup() {
  Serial.begin(115200);
  connectivityManager.connectToWiFi();
  cameraManager.init();
  motionSensor.init();
  ultrasonicSensor.init(); // Initialize the ultrasonic sensor
}

void loop() {
  if(motionSensor.isMotionDetected()){
    long distance = ultrasonicSensor.measureDistance(); // Measure the distance
    Serial.println("Human detected.");
    Serial.println("The person's distance to the sensor:");
    Serial.print(distance);
    Serial.print(" cm");
    camera_fb_t* fb = cameraManager.capture();
    if (fb != nullptr) {
      connectivityManager.sendPhoto(fb);
      cameraManager.release(fb);
    } else {
      Serial.println("The photo could not be taken");
    }
  }

  /*
  if (distance < 30) { // If the distance is less than 30 cm
    Serial.println("Object is close, taking photo...");
    camera_fb_t* fb = cameraManager.capture();
    if (fb != nullptr) {
      connectivityManager.sendPhoto(fb);
      cameraManager.release(fb);
    } else {
      Serial.println("The photo could not be taken");
    }
  }
  */
  delay(5000); // Delay for control
}
