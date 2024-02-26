#ifndef ConnectivityManager_h
#define ConnectivityManager_h

#include "WiFi.h"
#include "HTTPClient.h"
#include "esp_camera.h"


class ConnectivityManager {
public:
  ConnectivityManager(const char* ssid, const char* password, const char* serverUrl);
  void connectToWiFi();
  void sendPhoto(camera_fb_t* fb);

private:
  const char* _ssid;
  const char* _password;
  const char* _serverUrl;
};

#endif
