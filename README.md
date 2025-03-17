# Internship Elevator Project
This simple elevator project written in Java will simulate using an elevator for you. It can be run both interactively or with command line arguments. While not requested in the instructions document, a top floor limit has been added because no building has infinite floors in real life. In addition, the elevator cannot go beneath floor zero.

## Features
- Can be run both interactively or with command line arguments.
- Automatically ensures the elevator can only go to a defined range of floors, like in real life.
- The elevator is its own class with multiple constructors, functions, and instance variables.
- A custom error was written if the elevator class received a floor value that was out of bounds.
- Travel time is recorded and printed out at the end of the program.

## Assumptions
- It takes the elevator ten seconds to traverse one floor.
- The elevator can go anywhere from the top floor to floor zero, which may or may not represent the basement.
- You cannot go to the same floor twice, because that's pointless.
- The elevator will not skip floors.
- You are the only one using this elevator.

## Unimplemented Features
- No UI. Currently everything is just text.
- No emergency button in case everything goes haywire.
- Elevator music is still missing. :musical_note::musical_note:

## Build Instructions
### Prerequisites
- **Java Development Kit (JDK) 8 or higher**  
- **Apache Maven** installed  
- **Git** (if cloning from GitHub)

### Cloning the Repository
To download the project, run:
```sh
git clone https://github.com/enderzhangpro/Elevator.git
cd Elevator