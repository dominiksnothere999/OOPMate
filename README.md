# 🎮 OOPMate Chess

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-5C2D91?style=for-the-badge&logoColor=white)

A fully-featured chess game implemented in Java using object-oriented programming principles. Challenge the AI at different difficulty levels or analyze your gameplay with the built-in move history tracker.

## 🚧 Current Development Status

This project is currently under active development.

## ✨ Features

- Complete chess implementation with all standard rules
- Graphical user interface built with Java Swing
- AI opponent with three difficulty levels:
  - 🟢 Easy: Makes random valid moves
  - 🟡 Medium: Makes smarter moves with some strategy
  - 🔴 Hard: Uses (semi) advanced algorithms to challenge even experienced players
- Special move support:
  - Castling (kingside and queenside)
  - Pawn promotion
  - En passant captures
- Move history with standard chess notation
- Game state tracking (check, checkmate)
- 2D chess piece graphics

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Git (optional, for cloning the repository)

### Installation

1. Clone the repository (or download ZIP):
   ```bash
   git clone https://github.com/dominiksnothere999/OOPMate.git
   ```
2.  Navigate to the project directory:
    ```bash
    cd OOPMate
    ```
3.  Compile the project:
    ```bash
    javac -d bin src/me/dominiksnothere999/oopmate/Main.java
    ```
4.  Run the game:
    ```bash
    java -cp bin me.dominiksnothere999.oopmate.Main
    ```

## 🎯 How to Play

1.  Launch the application
2.  Select "Play vs AI" from the main menu
3.  Choose your preferred AI difficulty level
4.  Play chess! Click and drag pieces to move them
5.  The status panel will show whose turn it is and game state information
6.  The move history panel tracks all moves in the game

## 🏗️ Project Structure

The project follows a clean object-oriented architecture:

-   `board`  - Classes for representing the chess board and squares
-   `pieces`  - Implementation of all chess pieces and their movement rules
-   `controller`  - Game logic and AI implementation
-   `gui`  - User interface components
-   `utils`  - Helper methods and constants

## 🧠 AI Implementation

The AI opponent features three difficulty levels:

-   **Easy**: Makes random valid moves
-   **Medium**: Makes tactical moves with some evaluation
-   **Hard**: Uses advanced board evaluation and deeper search (for real it's quite dumb)

## 🤝 Contributing

This is a school project, so there will be no updates after the final release!