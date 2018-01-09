package com.kata.game.tennis;

public class Player {

	private String name;
	private int score;
	private int setScore;
	
	public Player(String name) {
		this.name = name; 
	}

	public String getName() {
		return this.name;
	}

	public void winPoint() {
	
		this.score++;
	}

	public int getScore() {
		return this.score;
	}

	public void losePoint() {
		this.score--;
		
	}

	public void winSet() {
		this.setScore++;
	}

	public void setScore(int score) {
		this.score =score;
		
	}

	public void setSetScore(int setScore) {
		this.setScore= setScore;
		
	}

	public int getSetScore() {
		return this.setScore;
	}

}
