package com.example.firstteskspongebob.logic;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameLogic {
    private int actorPlace;
    private int gameBoard [][];
    private int actorBoard [] ;
    private int life;
    private int rowsGameBoard;
    private int colsGameBoard;
    public static final int NONE = 0;
    public static final int JELLYFISH = 1;
    public static final int BUBBLE = 2;
    private int score;
    public GameLogic() {}
    public GameLogic(int rows, int cols ,int life) {
        this.gameBoard = new int[rows][cols];
        this.actorBoard = new int[cols];
        this.life = life;
        this.actorPlace = this.actorBoard.length/2;
    }
    public int[][] getGameBoard() {
        return gameBoard;
    }
    public int[] getActorBoard() {
        return actorBoard;
    }
    public int getLife() {
        return life;
    }
    public int getRowsGameBoard() {
        return rowsGameBoard;
    }
    public int getColsGameBoard() {
        return colsGameBoard;
    }
    public GameLogic setGameBoard(@NonNull int[][] gameBoard) {
        this.gameBoard = gameBoard;
        for (int i = 0; i < gameBoard.length ; i++){
            for (int j = 0; j < gameBoard[i].length ; j++) {
                gameBoard[i][j] = NONE;
            }
        }
        return this;
    }
    public GameLogic setActorBoard(int[] actorBoard) {
        this.actorBoard = actorBoard;
        return this;
    }
    public GameLogic setLife(int life) {
        this.life = life;
        return this;
    }
    public GameLogic setRowsGameBoard(int rowsGameBoard) {
        this.rowsGameBoard = rowsGameBoard;
        return this;
    }
    public GameLogic setColsGameBoard(int colsGameBoard) {
        this.colsGameBoard = colsGameBoard;
        return this;
    }
    public int getActorPlace() {
        return actorPlace;
    }
    public GameLogic setActorPlace(int actorPlace) {
        this.actorPlace = actorPlace;
        this.actorBoard[actorPlace] = 1;
        return this;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void turnRightActor(){
        if(actorPlace < actorBoard.length-1){
            actorBoard[actorPlace] = 0;
            actorPlace++;
            actorBoard[actorPlace] = 1;
        }
    }
    public void turnLeftActor() {
        if(actorPlace > 0){
            actorBoard[actorPlace] = 0;
            actorPlace--;
            actorBoard[actorPlace] = 1;
        }
    }
    public void refreshGameBoard(){

        for (int i = rowsGameBoard-1; i > 0 ; i--) {
            System.arraycopy(gameBoard[i-1], 0,gameBoard[i] , 0, colsGameBoard);
        }
        for (int i = 0; i < colsGameBoard; i++) {
            gameBoard[0][i] = NONE;
        }
        int rand = new Random().nextInt(colsGameBoard);
        boolean jellyOrBubble = true;
        if (score%4 == 0)
            jellyOrBubble = new Random().nextBoolean();
        gameBoard[0][rand] = jellyOrBubble == true ? JELLYFISH : BUBBLE;
    }
    public boolean collisionTest(@NonNull int[] arrayToTest){
        if(arrayToTest[actorPlace] == JELLYFISH){
            reduceLife();
            return true;
        }
        if(arrayToTest[actorPlace] == BUBBLE){
            score+=3;
        }
        score++;
        return false;
    }
    public void reduceLife(){
        if (life > 0){
            life--;
        }
    }
}
