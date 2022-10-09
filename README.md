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
<li>Note that valid API keys are needed to functionally run this project. Contact the creators if you believe you should have access to these keys.</li>
<li>Run the app by clicking the green play button.</li>
</ol>

# AlliGators Beta Build

## Overview
The Beta Build currently guides the user through a series of screens and into a map where the majority of app time will be spent. The users can select between participating in one of the default tour routes or create their own. If the users decide to customize a tour they are prompted to select origin, destination, and waypoints of the route. After choosing a tour, the route locations are populated with markers in a map with enabled zoom and gesture controls. Additionally, the user’s live location and a polyline (connected series of line segments) is displayed to show the walking route the user should take. Moreover, the users have access to a dynamic chat with the CogBot (AI agent) Alli Gator to ask questions related to the tour or general questions they have in relation to the University of Florida.

## Usability

### Interface
The app currently displays a series of screens guiding the user through the basic process of starting a tour. Each screen contains buttons which can be used to navigate to other screens. The user is able to select the origin and destination of the tour as well as different campus locations to visit in between, and this is carried over to the Google Maps screen which contains pins on the selected locations. In addition, the chatbot user interface allows a user to send text messages or speech-to-text messages in a similar way as text messaging a friend. As more questions are asked, messages scroll down the screen and can be selected to be removed if need be.

### Navigation
The navigation provides for a straightforward user experience. Users are able to choose two options: browse common tours or make their own paths. They are also able to choose to speak to the chatbot. In the “Browse Common Tours” option, a user can choose between set routes (i.e. General Campus Tour, Historical Tour, College of Engineering, Liberal Arts and Sciences). In “Make My Own Path”, a user chooses specific landmarks and these landmarks appear on a map. Also, users can select the chatbot option to ask general questions about the University of Florida.

### Perception
The buttons are intuitive with descriptions of what they do. Checkboxes are used to indicate which landmarks have already been selected. Speech-to-text is enabled on the chatbot. All interactive gestures produce a visual response such that the users are aware of changes in the app. 

### Responsiveness
The API calls (Google Maps API and Chatbot API) are carried out asynchronously and do not contain any busy wait loops; therefore, any unexpected delay in response does not block the users from interacting with the application. If calls fail, an exception is thrown with an error message. 

## Build Quality

### Robustness
* Tour functionality - TODO 
* Integration with BLE beacons - Still a work in progress. Currently, we have a sample app that can detect when the phone is within a certain geofence (or location), but this has not been fully integrated into the app yet.
* Chatbot integration with Alli - When a user asks a general UF question, the chatbot API is called and a response is generated. When using the chatbot, crashes have occured, but they are very rare. I believe these crashes are more on the chatbot API side however rather than them being created through our application. The answers the chatbot gives still need to be parsed so that links are generated as links rather than just text. Users are also able to use speech-to-text which works as long as the user speaks clearly.
* When processing data, null safety is practiced. The same will go for attempts to connect to external services such as other APIs, Beacons, or ChatBots.

### Consistency
* Tour functionality - TODO
* Integration with BLE beacons - Still a work in progress. Once this is implemented, a user should be notified when they are within a certain distance of a tour landmark.
* Chatbot integration with Alli - The answers given by the Alli chatbot are consistent. If a user asks the same question, the same answer is given (it depends on the specific wording given). For training the chatbot, answers can be reviewed in Cogability's website to rate the answers and give one better suited to the question. In addition, when a user clicks on a message, the messages are deleted without fail.

### Aesthetic Rigor
* Tour functionality - TODO
* Integration with BLE beacons - Still a work in progress. The plan is that this will be implemented as both a notification and a screen that describes the landmark the user has come across. 
* Chatbot integration with Alli - There is a small wait time between when the user sends the question as the API is generating a response, but we plan on integrating a small waiting symbol to make this a little more seemless (so the user doesn't just think the app has crashed). The screen is implemented as a texting application in which the user's messages are displayed in a bubble on the right and the chatbot's messages are displayed on the left. As the user asks more questions and the chatbot answers, the messages scroll. The user can choose to scroll to previous messages or delete messages by clicking on the specific message. 

## Vertical Features

### Major Use-Cases
* Tour functionality - users should be able to select a tour and be guided to the different locations. Google Maps SDK and API integration for custom map display with relevant elements for a campus tour: user live location, destination markers, info windows, and directions between markers.
* Interaction with bluetooth low energy (BLE) beacons for refined location of the user in the map.
* Dynamic chat interface with “Alli Gator”, the CogAbility AI agent CogBot, to ask questions and resemble the interactions the user would have with a real tour guide.



### External Interface
* Tour functionality - users are able to choose which tour they want to take. If they make their own path, pins indicate which landmarks they have chosen. The map’s markers (pins) are connected to display the route the user should follow. Finally, the user’s live location is actively displayed on the map.
* Integration with BLE beacons - when users go within a certain distance, the app indicates that a user has crossed paths with a beacon or a geofenced location (a location that should contain a landmark, i.e. Marston). Note that this hasn’t been fully implemented yet into our repo, but we are working to get this in ASAP.
* Chatbot integration with Alli - Users can hold a chat conversation with Alli Gator, the chatbot. The chat is dynamic to simulate a casual conversation with a tour guide. Users can view Alli’s responses in chat bubbles from calling the CogBot API. Users also have the option of using speech instead of typing. They can delete previous messages by tapping/clicking on them and scroll through past messages.

### Persistent State
* Tour functionality - the user's map selection information is passed from one screen to the next, to be displayed on the map during the tour. Moreover, the user’s most recent location is stored to display the most accurate live location.
* Integration with BLE beacons - users location is constantly monitored with app use. They must give permission to do this first.
* Chatbot integration with Alli - saves history of things that have been asked on the backend (used for training) as well as in the UI (chat interface) so the user can view the conversation history. Users can choose to erase previous messages if need be as well.

### Internal Systems
* Tour functionality - integration with the Google Maps API to display map information. Direction polyline between origin and destination is optimized by the API along the waypoints (stopping places in the route).
* Integration with BLE beacons - integration with the Gimbal beacons API to utilize the BLE beacons. 
* Chatbot integration with Alli - integration with the Alli chatbot API.

## Link to Repo
https://github.com/ChrisCJFern/AlliGators
