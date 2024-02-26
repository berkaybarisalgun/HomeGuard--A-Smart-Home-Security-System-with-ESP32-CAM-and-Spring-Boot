#ifndef SoundSensorManager_h
#define SoundSensorManager_h

class SoundSensorManager {
public:
    SoundSensorManager(int pin);
    void init();
private:
    int _pin;
};

#endif
