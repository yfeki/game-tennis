package com.kata.game.tennis;

import java.util.Arrays;
import java.util.List;

public class TennisGame {
	public static final List<String> ALL_SCORES =  Arrays.asList("0","15","30","40");
	private Player player1;
	private Player player2;

	public TennisGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getPlayer1() {

		return this.player1;
	}

	public Player getPlayer2() {

		return this.player2;
	}

	public String displayCurrentScore(){
		StringBuilder currentScore = new StringBuilder();
		currentScore.append(this.player1.getName());
		currentScore.append(":");
		currentScore.append(ALL_SCORES.get(this.player1.getScore()));
		currentScore.append("\n");
		currentScore.append(this.player2.getName());
		currentScore.append(":");
		currentScore.append(ALL_SCORES.get(this.player2.getScore()));
		return currentScore.toString();
	}
}
