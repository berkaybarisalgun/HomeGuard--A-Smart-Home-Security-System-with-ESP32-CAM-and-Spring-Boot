# HomeGuard: Smart Home Security System

HomeGuard is a comprehensive smart security system designed for homes, integrating ESP32-CAM and Spring Boot for enhanced safety through live image surveillance and motion detection. This project aims to provide homeowners with a reliable, expandable security system that can monitor various environmental and security threats.

## Features

- **Live Image Capture:** Utilizes ESP32-CAM for capturing live images when motion is detected.
- **Motion Detection:** Alerts homeowners via the API when unexpected motion is observed.
- **Sound Triggered Captures:** Captures images when a significant sound is detected, adding another layer of security.
- **Distance Measurement:** After capturing an image, the system estimates the distance of the detected object or person.
- **Expandable:** Planned upgrades include a React frontend for user-friendly access and additional sensors (fire, flood, temperature, humidity, air quality, carbon monoxide, and flammable gases) for comprehensive environmental monitoring.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

## Current Status and Workaround

Due to Tinkercad's current lack of support for the ESP32, I'm unable to provide an interactive simulation for this prototype. However, to ensure an accessible start and offer a clear visualization of the project setup, I've included photographs of the assembled prototype.
Please understand that these images are a temporary solution. Despite the absence of a Tinkercad simulation, I believe these photographs will help beginners to take their first steps with the ESP32 more easily.

![ESP 32 Arduino](images_for_Readme/esp32_circuit.jpg)

### Prerequisites

- ESP32-CAM module
- PIR Motion Sensor Module
- Ultrasonic Distance Sensor
- A computer with Arduino IDE installed
- Spring Boot environment setup
- Postman for API testing


### Running the Project

1. **Start the Spring Boot application:** Navigate to your Spring Boot project's root directory and run the application using your IDE or the command line.

2. **ESP32-CAM Configuration:** Upload the provided Arduino sketch to your ESP32-CAM. Adjust WiFi settings and API endpoints as necessary.

3. **Accessing the Live Feed:** With everything set up, access the live feed through your Spring Boot application's endpoints, detailed in the Postman workspace.

### Viewing the Database

To view the database and its contents: 

1. **Access the H2 Database Console:** Once your application is running, you can access the H2 database console by navigating to `http://localhost:8080/h2-console` in your web browser. The exact URL may vary based on your application's configuration.

2. **Login to H2 Console:** Use the JDBC URL, username, and password as configured in your `application.properties` file to log in to the H2 console. <br>![Database Image](images_for_Readme/database.png)
<br>


### Accessing Photos with Postman

To view and interact with the live feed photos: ![Database Image](images_for_Readme/postman_collection.png)

1. **Open Postman:** Start Postman on your computer.

2. **Import Workspace:** Import the Postman workspace provided with the project. This workspace contains pre-configured requests to interact with your Spring Boot application. <a href="https://github.com/berkaybarisalgun/HomeGuard--A-Smart-Home-Security-System-with-ESP32-CAM-and-Spring-Boot/blob/main/Arduino%20camera.postman_collection.json">Workspace for Postman</a>

3. **Send Requests:** Use the requests in the Postman workspace to fetch live feed photos from the ESP32-CAM. You can view, save, or manipulate these photos as needed.

Please ensure that your ESP32-CAM and Spring Boot application are properly configured and running before attempting to access the live feed or view the database. This will ensure a smooth experience and successful interaction with the system.
   

## Upcoming Features

I plan to enhance HomeGuard by integrating a React frontend for easier interaction and adding more sensors for a thorough home protection system.

## Contributions

As this project evolves, I'll be adding more photos and making continuous improvements. Your contributions and suggestions are welcome!

## Credits

This project was inspired by and built upon the knowledge from the following tutorials:
- [Getting Started with ESP32-CAM](https://lastminuteengineers.com/getting-started-with-esp32-cam/)
- [ESP32-CAM Access Point (AP) for Web Server](https://randomnerdtutorials.com/esp32-cam-access-point-ap-web-server/)
- [AutoConnect Library for ESP32-CAM](https://hieromon.github.io/AutoConnect/esp32cam.html)

