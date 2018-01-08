package com.kata.game.tennis;

import java.util.Arrays;
import java.util.List;

public class TennisGame {
	private static final String WIN_THE_GAME = "win the game";
	private static final String SCORE_SEP = ":";
	private static final String LIGNE_SEP = "\n";
	public static final List<String> ALL_SCORES = Arrays.asList("0", "15",
			"30", "40");
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

	public String displayCurrentScore() {
		if (this.hasWinner()) {
			initScore();
		}

		StringBuilder currentScore = new StringBuilder();
		currentScore.append(this.player1.getName());
		currentScore.append(SCORE_SEP);
		currentScore.append(ALL_SCORES.get(this.player1.getScore()));
		currentScore.append(LIGNE_SEP);
		currentScore.append(this.player2.getName());
		currentScore.append(SCORE_SEP);
		currentScore.append(ALL_SCORES.get(this.player2.getScore()));
		if (this.hasWinner()) {
			currentScore.append(LIGNE_SEP);
			currentScore.append(this.displayGameWinner());
			currentScore.append(WIN_THE_GAME);
		}
		return currentScore.toString();
	}

	private String displayGameWinner() {
		return this.player1.getScore() > this.player2.getScore() ? 
				this.getPlayer1().getName() : this.getPlayer2().getName();

	}

	private boolean hasWinner() {
		return this.player1.getScore() > 3 || this.player2.getScore() > 3;
	}

	private void initScore() {
		this.player1.setScore(0);
		this.player2.setScore(0);

	}
}
