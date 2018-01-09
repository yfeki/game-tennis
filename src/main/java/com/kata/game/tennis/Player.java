package com.kata.game.tennis;

public class Player {

	private String name;
	private int score;
	
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

	public void setScore(int score) {
		this.score = score;
		
	}

	public void losePoint() {
		this.score--;
		
	}

}
