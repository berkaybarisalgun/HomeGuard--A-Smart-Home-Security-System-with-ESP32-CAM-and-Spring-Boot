#ifndef MotionSensorManager_h
#define MotionSensorManager_h

class MotionSensorManager {
private:
    int pin;

public:
    MotionSensorManager(int pin);
    void init();
    bool isMotionDetected();
};

#endif
