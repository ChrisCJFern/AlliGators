# How to Run
To run this application, you will need to obtain a Google Maps API key, as well as CogUniversity authorization credentials. The developers can provide this information if we want to.
## Steps
<ol>
<li>Download and Install Android Studio</li>
<li>Enable the latest Android SDK and emulation features</li>
<li>Set up a device emulator in Android Studio, or use your own if you own an android device</li>
<li>Clone this repository into a new directory</li>
<li>Open the project in Android Studio</li>
<li>Sync the project's Gradle files by clicking on the elephant icon in the top right. This will install any needed dependencies</li>
<li>Run the app by clicking the green play button.</li>
</ol>

# AlliGators Pre-Alpha Build

## Overview
The Pre-Alpha Build currently guides the user through a series of screens and into a map where the majority of app time will be spent. The user can select specific locations to visit on their tour, which are populated as markers on the map. Zoom and gesture controls are enabled on the map, and it will soon be augmented to include step-by-step directions and contextual information.

## External Interface
The app currently displays a series of screens guiding the user through the basic process of starting a tour. Each screen contains buttons which can be used to navigate to other screens.

## Persistent State
The user's selection information is passed from one screen to the next, to be displayed on the map during the tour.

## Internal Systems
The internal system passes data between the screens and fetches components and functionality from different APIs and Cloud services.

## Communication
Communication will be the emphasis of upcoming features such as Beacon and AI ChatBot integration.

## Integrity & Resilience
When processing data, null safety is practiced. The same will go for attempts to connect to external services such as other APIs, Beacons, or ChatBots.


