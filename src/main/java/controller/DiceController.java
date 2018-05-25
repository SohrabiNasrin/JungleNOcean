package controller;

import model.Dice;

//DiceController is only responsible for creating and controlling the Dice model which shows the Single Responsibility and High cohision design patterns

public class DiceController {

    private Dice dice;
    private int rolledNumber = 0;

    public DiceController(){
        dice = Dice.getSingletone();
    }

    public int rollDice(){
        rolledNumber = dice.roll();
        return rolledNumber;
    }

    public int getRolledNumber(){
        return rolledNumber;
    }
}
