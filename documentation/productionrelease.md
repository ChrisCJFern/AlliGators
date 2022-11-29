# AlliGators Production Release

## Overview
The Production Release currently guides the user through a series of screens and into a map where the majority of app time will be spent. The users can select between participating in one of the default tour routes or create their own. If the users decide to customize a tour they are prompted to select origin, destination, and waypoints of the route. After choosing a tour, the route locations are populated with markers in a map with enabled zoom and gesture controls. Additionally, the user’s live location and a polyline (connected series of line segments) is displayed to show the walking route the user should take. Also, the user can tap on the location markers to display a custom info window with relevant information on the location; if the info window itself is tapped, then the user is redirected to a screen that displays further detailed information of the place. Moreover, the users have access to a dynamic chat with the CogBot (AI agent) Alli Gator to ask questions related to the tour or general questions they have in relation to the University of Florida. In addition, the application is connected to Bluetooth Low Energy beacons in that when a user is close to a Gimbal beacon, it notifies the app.

## Usability

### Interface
The app currently displays a series of screens guiding the user through the basic process of starting a tour. Each screen contains buttons which can be used to navigate to other screens. The user is able to select the origin and destination of the tour as well as different campus locations to visit in between, and this is carried over to the Google Maps screen which contains pins on the selected locations with showable/hideable info windows. In addition, the chatbot user interface allows a user to send text messages or speech-to-text messages in a similar way as text messaging a friend. As more questions are asked, messages scroll down the screen and can be selected to be removed if need be. 

### Navigation
The navigation provides for a straightforward user experience. Users are able to choose two options: browse common tours or make their own paths. They are also able to choose to speak to the chatbot. In the “Browse Common Tours” option, a user can choose between set routes (i.e. General Campus Tour, Historical Tour, College of Engineering, Liberal Arts and Sciences). In “Make My Own Path”, a user chooses specific landmarks and these landmarks appear on a map. Also, users can select the chatbot option to ask general questions about the University of Florida.

### Perception
The buttons are intuitive with descriptions of what they do. Checkboxes are used to indicate which landmarks have already been selected. Speech-to-text is enabled on the chatbot. All interactive gestures produce a visual response such that the users are aware of changes in the app. 

### Responsiveness
The API calls (Google Maps API and Chatbot API) are carried out asynchronously and do not contain any busy wait loops; therefore, any unexpected delay in response does not block the users from interacting with the application. If calls fail, an exception is thrown with an error message. 

## Build Quality

### Robustness
* Tour functionality - The tour map's robustness is dependable. When the user starts the map activity, the Google Maps API is called to return the map with markers for the relevant locations. Additionally, when pressing the "Update Directions" button the Google Maps Directions API is called to return the polyline (route) to be displayed connecting the relevant locations. On rare cases, the API call is not successful but these isolated bugs are usually attributable to internet connection loss. Moreover, when the user taps on a marker, its respective info window is displayed without errors (information is correct for tapped marker, and no bugs or crashes have been produced when showing/hiding them).
* Integration with BLE beacons - When the user allows location and bluetooth permissions, the app begins scanning for the Gimbal Bluetooth Low Energy beacons. It detects them when they are within certain range.
* Chatbot integration with Alli - When a user asks a general UF question, the chatbot API is called and a response is generated. When using the chatbot, crashes have occured, but they are very rare. I believe these crashes are more on the chatbot API side however rather than them being created through our application. The answers the chatbot gives still need to be parsed so that links are generated as links rather than just text. Users are also able to use speech-to-text which works as long as the user speaks clearly.
* When processing data, null safety is practiced. The same will go for attempts to connect to external services such as other APIs, Beacons, or ChatBots.

### Consistency
* Tour functionality - The tour feature's UI and API response is consistent. The API calls always produce the same map, markers, and polyline response; therefore, the users will not encounter unpredictability of behavior while interacting with the map. The information for each location marker (info windows) is also consistent and there never occurs a wrong display of data.
* Integration with BLE beacons - Whenever the user is within range of the beacon, the app successfully detects it consistently.
* Chatbot integration with Alli - The answers given by the Alli chatbot are consistent. If a user asks the same question, the same answer is given (it depends on the specific wording given). For training the chatbot, answers can be reviewed in Cogability's website to rate the answers and give one better suited to the question. In addition, when a user clicks on a message, the messages are deleted without fail.

### Aesthetic Rigor
* Tour functionality - In regard to aesthetic design, the interactive UI to customize tours is easy to understand, even when using the app for the first time. Also, the layout of options in the map is straightforward to understand since it follows the conventions of most main stream map applications. However, for aesthetics, the "Update Directions" button will be made smaller to avoid a slight overlap with corner buttons in smaller displays. The custom layout of the info windows is simple and organized for quick reading from the users. For more in-depth reading the info window can be expanded to a full scrollable screen with information and more visible images.
* Integration with BLE beacons - Still a work in progress. This will be implemented as both a notification and a screen that describes the landmark the user has come across. Now that the BLE beacons are connected, we will implement the app so that when a beacon is encountered a screen will pop up with the Cicerone tour script about the location the beacon is located in. This should be implemented very soon
* Chatbot integration with Alli - There is a small wait time between when the user sends the question as the API is generating a response, but we plan on integrating a small waiting symbol to make this a little more seemless (so the user doesn't just think the app has crashed). The screen is implemented as a texting application in which the user's messages are displayed in a bubble on the right and the chatbot's messages are displayed on the left. As the user asks more questions and the chatbot answers, the messages scroll. The user can choose to scroll to previous messages or delete messages by clicking on the specific message. 

## Vertical Features

### Major Use-Cases
* Tour functionality - users should be able to select a tour and be guided to the different locations. Google Maps SDK and API integration for custom map display with relevant elements for a campus tour: user live location, destination markers, info windows, and directions between markers. Info windows can be tapped to expand the size and access more information on the relevant location. 
* Interaction with bluetooth low energy (BLE) beacons for refined location of the user in the map. When close to a Gimbal beacon, the app will naviagate to a screen that details the location that the user is in to emulate how a tour guide would describe certain landmarks of the tour. For example, if a user gets close to Century Tower, a screen will give information about Century Tower using the script that the Cicerones (official UF tour guides) use.
* Dynamic chat interface with “Alli Gator”, the CogAbility AI agent CogBot, to ask questions and resemble the interactions the user would have with a real tour guide.



### External Interface
* Tour functionality - users are able to choose which tour they want to take. If they make their own path, pins indicate which landmarks they have chosen. The map’s markers (pins) are connected to display the route the user should follow. Finally, the user’s live location is actively displayed on the map.
* Integration with BLE beacons - when users go within a certain distance, the app indicates that a user has crossed paths with a beacon or a geofenced location (a location that should contain a landmark, i.e. Marston) and will give information about this place. This is implemented as our "More Info" screen. Note that this hasn’t been fully implemented yet into our repo, but we are working to get this in ASAP.
* Chatbot integration with Alli - Users can hold a chat conversation with Alli Gator, the chatbot. The chat is dynamic to simulate a casual conversation with a tour guide. Users can view Alli’s responses in chat bubbles from calling the CogBot API. Users also have the option of using speech instead of typing. They can delete previous messages by tapping/clicking on them and scroll through past messages.

### Persistent State
* Tour functionality - the user's map selection information is passed from one screen to the next, to be displayed on the map during the tour. Moreover, the user’s most recent location is stored to display the most accurate live location. Finally, the relevant information of the locations (Cicerone script) is stored within the app.
* Integration with BLE beacons - users location is constantly monitored with app use. They must give permission to do this first. Beacon names are stored within a Gimbal Beacon manager which will then be used to connect the beacon to the specific location they are placed in.
* Chatbot integration with Alli - saves history of things that have been asked on the backend (used for training) as well as in the UI (chat interface) so the user can view the conversation history. Users can choose to erase previous messages if need be as well.

### Internal Systems
* Tour functionality - integration with the Google Maps API to display map information. Direction polyline between origin and destination is optimized by the API along the waypoints (stopping places in the route).
* Integration with BLE beacons - integration with the Gimbal beacons API to utilize the BLE beacons. 
* Chatbot integration with Alli - integration with the Alli chatbot API.

## Communication
* The application uses several APIs in order to be fully functional. 
* For the tours, it uses the Google Maps API to display map information. Note that the user must enable location services in order to effectively use this feature. 
* For the beacons, it communicates with the Gimbal Android Version 4 API to connect to Gimbal beacons that are within a certain range. This also communicates with a Gimbal Manager which have set up Places, Geofences, and Beacons. This manager is an external feature of the Gimbal API which must be correctly set up before the API can be used.
* For the chatbot, Alli Tours communicates with the Cogability UF chatbot API. Calls to the API are made when a user asks a question and it responds with answers generated through Cogability's AI corpus.
* The screens are routed so that the interface is easy to use and intuitive. Android's back button can be used to go to previous screens.

## Integrity and Resilience
* Null safety is practiced to prevent null errors. In order to ensure that the application runs correctly, an Android testing suite called Monkey is employed to generate pseudo-random streams of user events such as clicks, touches, or gestures, as well as a number of system-level events. We use this to stress-test the application and to ensure that crashes are rare and far in between. So far, the application rarely crashes and runs consistently.

## Link to Repo
https://github.com/ChrisCJFern/AlliGators


