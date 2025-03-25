package me.dominiksnothere999.oopmate.controller;

// This is the AIGameController class, which is used to control the game against an AI.
public class AIGameController extends GameController {
    // The difficulty of the AI.
    private final Difficulty difficulty;

    // Constructor for the AIGameController class.
    public AIGameController(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
    }
    
    // Get the difficulty of the AI.
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }
}