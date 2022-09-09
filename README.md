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

# AlliGators Alpha Build

## Overview
The Alpha Build currently guides the user through a series of screens and into a map where the majority of app time will be spent. The user can select specific locations to visit on their tour, which are populated as markers on the map. Zoom and gesture controls are enabled on the map, and it will soon be augmented to include step-by-step directions and contextual information.

## Usability

### Interface
The app currently displays a series of screens guiding the user through the basic process of starting a tour. Each screen contains buttons which can be used to navigate to other screens. The user is able to select different campus locations and this is carried over to the Google Maps screen which contains pins on the selected locations.

### Navigation
The navigation provides for a straightforward user experience. Users are able to choose two options: browse common tours or make their own paths. They are also able to choose to speak to the chatbot. In the “Browse Common Tours” option, a user can choose between set routes (i.e. General Campus Tour, Historical Tour, College of Engineering, Liberal Arts and Sciences). In “Make My Own Path”, a user chooses specific landmarks and these landmarks appear on a map.

### Perception
The buttons are intuitive with descriptions of what they do. Checkboxes are used to indicate which landmarks have already been selected. Speech-to-text is enabled on the chatbot.

### Responsiveness
The API calls (Google Maps API and Chatbot API) do not contain any busy wait loops. If calls fail, an exception is thrown with an error message.

## Build Quality

### Robustness
No known glitches/crashes yet. 

### Consistency
The application consistently does what it is programmed to do.

### Aesthetic Rigor
No major cosmetic software issues present. 

## Vertical Features

### Major Use-Cases
Google Maps SDK and API integration for custom map display with relevant elements for a campus tour: location markers, info windows, and directions between markers.
Interaction with low energy bluetooth beacons for refined location of the user in the map.
Dynamic chat interface with “Alli Gator”, the CogAbility AI agent CogBot, to ask questions and resemble the interactions the user would have with a real tour guide.


### External Interface
Tour functionality - users are able to choose which tour they want to take. If they make their own path, pins indicate which landmarks they have chosen.
Integration with BLE beacons - when users go within a certain distance, the app indicates that a user has crossed paths with a beacon or a geofenced location (a location that should contain a landmark, i.e. Marston). Note that this hasn’t been fully implemented yet into our repo yet, but is currently being tested and created in a separate project file which will be integrated once complete.
Chatbot integration with Alli - Users can view an Alli response from the Alli API. Users also have the option of using speech instead of typing.

### Persistent State
Tour functionality - the user's map selection information is passed from one screen to the next, to be displayed on the map during the tour.
Integration with BLE beacons - users location is constantly monitored with app use. They must give permission to do this first.
Chatbot integration with Alli - saves history of things that have been asked on the backend (used for training).

### Internal Systems
Tour functionality - integration with the Google Maps API to display map information.
Integration with BLE beacons - integration with the Gimbal beacons API to utilize the BLE beacons.
Chatbot integration with Alli - integration with the Alli chatbot API.
Integrity & Resilience
When processing data, null safety is practiced. The same will go for attempts to connect to external services such as other APIs, Beacons, or ChatBots.
