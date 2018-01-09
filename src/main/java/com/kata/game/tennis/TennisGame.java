package com.kata.game.tennis;

import java.util.Arrays;
import java.util.List;

public class TennisGame implements ITennisGame{
	private static final String ADV = "ADV";
	private static final String WIN_THE_GAME = " win the game";
	private static final String WIN_THE_SET = " win the set";
	private static final String DEUCE = "DEUCE";
	private static final String SCORE_SEP = ":";
	private static final String LIGNE_SEP = "\n";
	private static final Object WIN_THE_SET_AND_THE_MATCH = " win the set and the match";

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
	
	@Override
	public String displayCurrentScore() {

		if (this.hasGameWinner()) {
			Player playerGameWinner = this.getPlayerGameWinner();
			playerGameWinner.winSet();
			initScore();
			StringBuilder score = new StringBuilder(this.displayScore(
					ALL_SCORES.get(this.player1.getGameScore()),
					ALL_SCORES.get(this.player2.getGameScore())));
			score.append(LIGNE_SEP);
			score.append(playerGameWinner.getName());
			score.append(WIN_THE_GAME);
			return score.toString();
		}

		else if (isDeuceActivated()) {
			return this.displayScore(DEUCE, DEUCE);
		}

		else if (this.hasAdvantageAgainstPlayer1()) {
			return this.displayScore(ALL_SCORES.get(this.player1.getGameScore()),
					ADV);
		} else if (hasAdvantageAgainstPlayer2()) {
			return this.displayScore(ADV,
					ALL_SCORES.get(this.player2.getGameScore()));
		}

		return this.displayScore(ALL_SCORES.get(this.player1.getGameScore()),
				ALL_SCORES.get(this.player2.getGameScore()));

	}

	@Override
	public String displaySetScore() {
		String currentSetScore= this.displayScore(String.valueOf(this.player1.getSetScore()),
				String.valueOf(this.player2.getSetScore()));
		if (this.hasSetWinner()){
			Player playerSetWinner = this.getPlayerSetWinner();
			StringBuilder sb = new StringBuilder(currentSetScore);
			sb.append(LIGNE_SEP);
			sb.append(playerSetWinner.getName());
			sb.append(WIN_THE_SET);
			currentSetScore = sb.toString();
			this.initSetScore();
		}
		return currentSetScore;
	}
	
	@Override
	public String displayTieBreakScore() {
		if (this.hasMatchWinner()){
			Player playerMatchWinner = this.getPlayerMatchWinner();
			playerMatchWinner.winSet();
			this.initTieBreakScore();
			StringBuilder score = new StringBuilder(this.displayScore(String.valueOf(this.player1.getTieBreakScore()), String.valueOf(this.player2.getTieBreakScore())));
			score.append(LIGNE_SEP);
			score.append(playerMatchWinner.getName());
			score.append(WIN_THE_SET_AND_THE_MATCH);
			return score.toString();
		}
		
		return this.displayScore(String.valueOf(this.player1.getTieBreakScore()), String.valueOf(this.player2.getTieBreakScore()));
	}

	private Player getPlayerMatchWinner() {
		
		return this.player1.getTieBreakScore() > this.player2.getTieBreakScore() ? this.player1 :this.player2;
	}

	private Player getPlayerSetWinner() {
		
		return this.player1.getSetScore() > this.player2.getSetScore() ? this.player1: this.player2;
	}

	private boolean hasAdvantageAgainstPlayer1() {
		return this.player1.getGameScore() == 3 && this.player2.getGameScore() == 4;
	}

	private boolean hasAdvantageAgainstPlayer2() {
		return this.player1.getGameScore() == 4 && this.player2.getGameScore() == 3;
	}

	private boolean isDeuceActivated() {
		return this.player1.getGameScore() == 3 && this.player2.getGameScore() == 3;
	}

	private Player getPlayerGameWinner() {
		
		return this.player1.getGameScore() > this.player2.getGameScore() ? this.player1 : this.player2;

	}

	private boolean hasGameWinner() {
		int scoreDiff = Math.abs(this.player1.getGameScore()
				- this.player2.getGameScore());
		return scoreDiff >= 2
				&& (this.player1.getGameScore() >= 4 || this.player2.getGameScore() >= 4);
	}

	private boolean hasSetWinner(){
		int scoreSetDiff = Math.abs(this.player1.getSetScore()
				- this.player2.getSetScore());
		return scoreSetDiff >= 2
				&& (this.player1.getSetScore() >= 6 || this.player2.getSetScore() >= 6);
		
	}
	
	private boolean hasMatchWinner(){
		int tieBreakScoreDiff= Math.abs(this.player1.getTieBreakScore()
				- this.player2.getTieBreakScore());
		return tieBreakScoreDiff >= 2
				&& (this.player1.getTieBreakScore() >= 7 || this.player2.getTieBreakScore() >= 7);
	}
	private void initScore() {
		this.player1.setGameScore(0);
		this.player2.setGameScore(0);
	}
	
	private void initSetScore(){
		this.player1.setSetScore(0);
		this.player2.setSetScore(0);
	}
	
	private void initTieBreakScore(){
		this.player1.setTieBreakScore(0);
		this.player2.setTieBreakScore(0);
	}
	private String displayScore(String scorePlayer1, String scorePlayer2) {
		StringBuilder currentScore = new StringBuilder();
		currentScore.append(this.player1.getName());
		currentScore.append(SCORE_SEP);
		currentScore.append(scorePlayer1);
		currentScore.append(LIGNE_SEP);
		currentScore.append(this.player2.getName());
		currentScore.append(SCORE_SEP);
		currentScore.append(scorePlayer2);
		return currentScore.toString();
	}

	

}
