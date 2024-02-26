#ifndef UltrasonicSensorManager_h
#define UltrasonicSensorManager_h

class UltrasonicSensorManager {
  private:
    int trigPin;
    int echoPin;
  public:
    UltrasonicSensorManager(int trigPin, int echoPin);
    void init();
    long measureDistance();
};

#endif
