package com.kata.game.tennis;


/**
 * @author yfeki
 *
 */
public class TennisGameImpl implements ITennisGame{

	private Player player1;
	private Player player2;

	public TennisGameImpl(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getPlayer1() {

		return this.player1;
	}

	public Player getPlayer2() {

		return this.player2;
	}
	
	
	/**
     * method to display the current players game score
     *
     * @return display current game score
     */
	@Override
	public String displayCurrentScore() {

		if (this.hasGameWinner()) {
			Player playerGameWinner = this.getPlayerGameWinner();
			playerGameWinner.winSet();
			resetGameScores();
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
	
	/**
     * method to display the current players set score
     *
     * @return display current set score
     */
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
			this.resetSetScores();
		}
		return currentSetScore;
	}
	
	/**
     * method to display the current players tie break score
     *
     * @return display current tie break score
     */
	@Override
	public String displayTieBreakScore() {
		if (!this.isTieBreakActivated()){
			return null;
		}
		if (this.hasTieBreakWinner()){
			Player playerTieBreakWinner = this.getPlayerTieBreakWinner();
			playerTieBreakWinner.winSet();
			this.resetTieBreakScores();
			StringBuilder score = new StringBuilder(this.displayScore(String.valueOf(this.player1.getTieBreakScore()), String.valueOf(this.player2.getTieBreakScore())));
			score.append(LIGNE_SEP);
			score.append(playerTieBreakWinner.getName());
			score.append(WIN_THE_SET_AND_THE_MATCH);
			return score.toString();
		}
		
		return this.displayScore(String.valueOf(this.player1.getTieBreakScore()), String.valueOf(this.player2.getTieBreakScore()));
	}
	
	/**
     * method to get game winner player
     *
     * @return Player
     */
	private Player getPlayerGameWinner() {
		return this.player1.getGameScore() > this.player2.getGameScore() ? this.player1 : this.player2;

	}
	
	/**
     * method to get set winner player
     *
     * @return Player
     */
	private Player getPlayerSetWinner() {
		
		return this.player1.getSetScore() > this.player2.getSetScore() ? this.player1: this.player2;
	}

	/**
     * method to get tie break  winner player
     *
     * @return Player
     */
	private Player getPlayerTieBreakWinner() {
		return this.player1.getTieBreakScore() > this.player2.getTieBreakScore() ? this.player1 :this.player2;
	}


	/**
     * method to check if player2 has advantage against player1
     *
     * @return boolean
     */
	private boolean hasAdvantageAgainstPlayer1() {
		return this.player1.getGameScore() == MAX_GAME_SCORE && this.player2.getGameScore() == GAME_WINNER_SCORE;
	}

	/**
     * method to check if player1 has advantage against player2
     *
     * @return boolean
     */
	private boolean hasAdvantageAgainstPlayer2() {
		return this.player1.getGameScore() == GAME_WINNER_SCORE && this.player2.getGameScore() == MAX_GAME_SCORE;
	}

	/**
     * method to check if Deuce rule is activated
     *
     * @return boolean
     */
	private boolean isDeuceActivated() {
		return this.player1.getGameScore() == MAX_GAME_SCORE && this.player2.getGameScore() == MAX_GAME_SCORE;
	}
	
	/**
     * method to check if tie break rule is activated
     *
     * @return boolean
     */
	private boolean isTieBreakActivated(){
		return this.player1.getSetScore()==SET_WINNER_SCORE && this.player2.getSetScore()==SET_WINNER_SCORE;
	}


	/**
     * method to check if there is a game winner
     *
     * @return boolean
     */
	private boolean hasGameWinner() {
		int scoreDiff = Math.abs(this.player1.getGameScore()
				- this.player2.getGameScore());
		return scoreDiff >= LIMIT_DIFF_SCORES
				&& (this.player1.getGameScore() >= GAME_WINNER_SCORE || this.player2.getGameScore() >= GAME_WINNER_SCORE);
	}

	/**
     * method to check if there is a set winner
     *
     * @return boolean
     */
	private boolean hasSetWinner(){
		int scoreSetDiff = Math.abs(this.player1.getSetScore()
				- this.player2.getSetScore());
		return scoreSetDiff >= LIMIT_DIFF_SCORES
				&& (this.player1.getSetScore() >= SET_WINNER_SCORE || this.player2.getSetScore() >= SET_WINNER_SCORE);
		
	}
	
	/**
     * method to check if there is a tie break winner
     *
     * @return boolean
     */
	private boolean hasTieBreakWinner(){
		int tieBreakScoreDiff= Math.abs(this.player1.getTieBreakScore()
				- this.player2.getTieBreakScore());
		return tieBreakScoreDiff >= LIMIT_DIFF_SCORES
				&& (this.player1.getTieBreakScore() >= TIE_BREAK_WINNER_SCORE || this.player2.getTieBreakScore() >= TIE_BREAK_WINNER_SCORE);
	}
	
	/**
     * method to reset game scores for both players
     *
     */
	private void resetGameScores() {
		this.player1.setGameScore(ZERO_SCORE);
		this.player2.setGameScore(ZERO_SCORE);
	}
	
	
	/**
     * method to reset set scores for both players
     *
     */
	private void resetSetScores(){
		this.player1.setSetScore(ZERO_SCORE);
		this.player2.setSetScore(ZERO_SCORE);
	}
	
	
	/**
     * method to reset tie break scores for both players
     *
     */
	private void resetTieBreakScores(){
		this.player1.setTieBreakScore(ZERO_SCORE);
		this.player2.setTieBreakScore(ZERO_SCORE);
	}
	
	/**
     * Generic method to format generic scores of both players with their names
     *
     *@return current score
     */
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
