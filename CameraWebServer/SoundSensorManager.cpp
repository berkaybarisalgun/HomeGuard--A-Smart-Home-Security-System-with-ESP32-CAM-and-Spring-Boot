#include "SoundSensorManager.h"
#include "Arduino.h"

SoundSensorManager::SoundSensorManager(int pin) : _pin(pin) {}

void SoundSensorManager::init() {
    pinMode(_pin, INPUT);
}
