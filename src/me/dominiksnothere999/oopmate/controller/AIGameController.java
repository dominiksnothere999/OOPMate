package me.dominiksnothere999.oopmate.controller;

public class AIGameController extends GameController {
    private final Difficulty difficulty;

    public AIGameController(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
    }
    
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

}