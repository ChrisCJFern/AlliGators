# AlliGators Tours

![Tour Map](https://github.com/ChrisCJFern/AlliGators/blob/main/pictures/map.png)
![Tour Chatbot](https://github.com/ChrisCJFern/AlliGators/blob/main/pictures/chatbot.png)

### Tour the University of Florida's campus with ease 

Alligator Tours is an Android application compatible with Android SDK versions 21 and up. It utilizes the Google Maps API, the official University of Florida (UF) chatbot, and bluetooth low energy beacons to provide a unique user experience where prospective students and families can tour the campus and learn more about important landmarks/buildings. Users are able to choose between pre-made paths or create their own through the campus. When a user gets close enough to a beacon, the app will navigate to a screen containing information about the landmark that the user is close to (as beacons will be placed around Campus associated with specific landmarks). In addition, users are able to interact with Alli, UF's official chatbot, and ask any questions in relation to the University in general.

## How to Run
To run this application, you will need to obtain a Google Maps API key, as well as CogUniversity authorization credentials. The developers can provide this information if authorized.
### Steps
<ol>
<li>Download and Install Android Studio</li>
<li>Enable the latest Android SDK and emulation features</li>
<li>Set up a device emulator in Android Studio, or use your own if you own an android device</li>
<li>Clone this repository into a new directory</li>
<li>Open the project in Android Studio</li>
<li>Sync the project's Gradle files by clicking on the elephant icon in the top right. This will install any needed dependencies</li>
<li>Note that valid API keys are needed to functionally run this project. Contact the developers if you believe you should have access to these keys.</li>
<li>Run the app on your emulator or own device by clicking the green play button.</li>
</ol>

## Documentation 

Guides and additional information about this application can be found [here](https://github.com/ChrisCJFern/AlliGators/blob/main/documentation/productionrelease.md).

Specifically useful documentation when starting out:

- [Gimbal Beacon Docs](https://docs.gimbal.com/android/v4/devguide.html)
- [Google Maps API Docs](https://developers.google.com/maps/documentation)


## Future Work
<ol>
  <li>Extend the application to be cross-compatible with iOS devices.</li>
  <li>Create a backend for tour location information.</li>
  <li>Enhance chatbot responses (hyperlinks, different answers, etc.).</li>
</ol>

