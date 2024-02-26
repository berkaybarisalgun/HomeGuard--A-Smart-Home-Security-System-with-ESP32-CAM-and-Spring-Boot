#ifndef LEDManager_h
#define LEDManager_h

class LEDManager {
private:
    int pin;
public:
    LEDManager(int pin);
    void init();
    void on();
    void off();
};

#endif
