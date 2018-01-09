package com.kata.game.tennis;

public class Player {

	private String name;
	private int gameScore;
	private int setScore;
	
	public Player(String name) {
		this.name = name; 
	}

	public String getName() {
		return this.name;
	}

	public void winPoint() {
	
		this.gameScore++;
	}

	public int getGameScore() {
		return this.gameScore;
	}

	public void losePoint() {
		this.gameScore--;
		
	}

	public void winSet() {
		this.setScore++;
	}

	public void setGameScore(int score) {
		this.gameScore =score;
		
	}

	public void setSetScore(int setScore) {
		this.setScore= setScore;
		
	}

	public int getSetScore() {
		return this.setScore;
	}

}
