#include "Arduino.h"
#include "UltrasonicSensorManager.h"

UltrasonicSensorManager::UltrasonicSensorManager(int trigPin, int echoPin) {
  this->trigPin = trigPin;
  this->echoPin = echoPin;
}

void UltrasonicSensorManager::init() {
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
}

long UltrasonicSensorManager::measureDistance() {
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  
  long duration = pulseIn(echoPin, HIGH);
  long distance = duration * 0.034 / 2;
  return distance;
}
