[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/DOjhUdA7)
# Software Engineering Course Project, Haifa University 2024
## Community Support System - ATIS (All Together Information System)
ATIS system is an emergency management platform that allows users to request help or volunteer for tasks like transportation, childcare, and food delivery. It includes an emergency alert button and a task management system to coordinate aid. Community managers can oversee requests, approve tasks, and track volunteer activity. The system is built with a client-server architecture and a relational database to support efficient and scalable operations.

## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController).
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## How to run the project, via JetBrains IntelliJ
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!

## Examples:
Login Screen
![צילום מסך 2024-09-05 142802](https://github.com/user-attachments/assets/6bed7e65-49d5-40e6-84b7-c42cda932925)

Community Member Screen- New Task Form
![צילום מסך 2024-09-05 142940](https://github.com/user-attachments/assets/ef18f348-7bdc-49e0-96d9-69bb000fd685)

Community Manager Screen- Main Screen
![image](https://github.com/user-attachments/assets/4cd60f6c-bb98-4be9-a0ce-75705fdabe5d)


Community Member Screen- Volunteer Screen
![image](https://github.com/user-attachments/assets/e3b0431c-bce2-45cd-9ec0-e77e35a9f69f)


Community Manager Screen- Statistic Analysis
![צילום מסך 2024-09-05 150549](https://github.com/user-attachments/assets/21ab6c74-8ea4-4452-b128-4e4737c7f6b5)


Notification

![צילום מסך 2024-09-05 152143](https://github.com/user-attachments/assets/a01540b6-ef84-439c-a815-3003967a0ce9)![צילום מסך 2024-09-05 152232](https://github.com/user-attachments/assets/93e0e028-1542-4734-94a0-81c68d7c2893)![צילום מסך 2024-09-05 150524](https://github.com/user-attachments/assets/84d835bd-3f11-4b75-90c4-c38983362dbd)![צילום מסך 2024-09-05 152334](https://github.com/user-attachments/assets/d5605ff9-4b58-4764-b1f8-411dfa2e74f9)



