package com.kata.game.tennis;

import java.util.Arrays;
import java.util.List;

public interface ITennisGame {
	
	 int LIMIT_DIFF_SCORES = 2;
	 int ZERO_SCORE = 0;
	 int MAX_GAME_SCORE = 3;
	 int TIE_BREAK_WINNER_SCORE = 7;
	 int SET_WINNER_SCORE = 6;
	 int GAME_WINNER_SCORE = 4;
	 String ADV = "ADV";
	 String WIN_THE_GAME = " win the game";
	 String WIN_THE_SET = " win the set";
	 String WIN_THE_SET_AND_THE_MATCH = " win the set and the match";
	 String DEUCE = "DEUCE";
	 String SCORE_SEP = ":";
	 String LIGNE_SEP = "\n";
	
	List<String> ALL_SCORES = Arrays.asList("0", "15",
			"30", "40");

	String displayGameScore();

	String displaySetScore();

	String displayTieBreakScore();
}
