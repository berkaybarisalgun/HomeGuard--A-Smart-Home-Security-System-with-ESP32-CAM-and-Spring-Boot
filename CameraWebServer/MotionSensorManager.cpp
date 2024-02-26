#include "Arduino.h"
#include "MotionSensorManager.h"

MotionSensorManager::MotionSensorManager(int pin) : pin(pin) {}

void MotionSensorManager::init() {
    pinMode(pin, INPUT_PULLUP);
}

bool MotionSensorManager::isMotionDetected() {
    return digitalRead(pin) == HIGH;
}
