#ifndef CameraManager_h
#define CameraManager_h

#include "esp_camera.h"

class CameraManager {
public:
  CameraManager();
  void init();
  camera_fb_t* capture();
  void release(camera_fb_t* fb);
  void enableFlash(bool enable); //flash control
};

#endif
