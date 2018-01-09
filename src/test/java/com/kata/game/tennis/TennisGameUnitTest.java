package com.kata.game.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TennisGameUnitTest {

	private TennisGameImpl tennisGame;

	@Before
	public void setUp() {
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		tennisGame = new TennisGameImpl(player1, player2);

	}

	@Test
	@Parameters({ "0,0,Player1:0\nPlayer2:0", "1,0,Player1:15\nPlayer2:0",
			"2,0,Player1:30\nPlayer2:0", "2,1,Player1:30\nPlayer2:15",
			"3,1,Player1:40\nPlayer2:15", "3,2,Player1:40\nPlayer2:30",
			"2,4,Player1:0\nPlayer2:0\nPlayer2 win the game",
			"3,4,Player1:40\nPlayer2:ADV" })
	public void should_display_current_score_at_any_moment_of_the_game(
			int pointsWonByPlayer1, int pointsWonByPlayer2, String expectedScore) {
		tennisGame.getPlayer1().setGameScore(pointsWonByPlayer1);
		tennisGame.getPlayer2().setGameScore(pointsWonByPlayer2);
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);

	}

	@Test
	public void shoud_display_Deuce_when_both_players_reach_forty() {

		tennisGame.getPlayer1().setGameScore(2);
		tennisGame.getPlayer2().setGameScore(3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore = "Player1:DEUCE\nPlayer2:DEUCE";
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);
	}

	@Test
	public void shoud_display_player1_has_advantage_when_score_is_Deuce_and_player1_win_point() {
		tennisGame.getPlayer1().setGameScore(3);
		tennisGame.getPlayer2().setGameScore(3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore = "Player1:ADV\nPlayer2:40";
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);
	}

	@Test
	public void should_display_player1_as_winner_when_he_had_advantage_and_win_point() {
		tennisGame.getPlayer1().setGameScore(4);
		tennisGame.getPlayer2().setGameScore(3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore = "Player1:0\nPlayer2:0\nPlayer1 win the game";
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);
	}

	@Test
	public void should_display_deuce_when_player1_had_advantage_and_lose_point() {
		tennisGame.getPlayer1().setGameScore(4);
		tennisGame.getPlayer2().setGameScore(3);
		tennisGame.getPlayer1().losePoint();
		String expectedScore = "Player1:DEUCE\nPlayer2:DEUCE";
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);
	}

	@Test
	public void should_display_set_score_as_player1_one_player2_zero_when_player1_win_first_game() {
		tennisGame.getPlayer1().setGameScore(4);
		String expectedScore = "Player1:0\nPlayer2:0\nPlayer1 win the game";
		String expectedSetScore = "Player1:1\nPlayer2:0";
		assertThat(tennisGame.displayGameScore()).isEqualTo(expectedScore);
		assertThat(tennisGame.displaySetScore()).isEqualTo(expectedSetScore);
	}

	@Test
	public void should_display_player2_as_set_winner_when_he_has_six_as_score_set() {
		tennisGame.getPlayer2().setSetScore(6);
		String expectedSetScore = "Player1:0\nPlayer2:6\nPlayer2 win the set";
		assertThat(tennisGame.displaySetScore()).isEqualTo(expectedSetScore);

	}

	@Test
	public void should_display_player2_as_set_winner_when_he_win_game_and_he_has_six_as_score_set_and_player2_five() {
		tennisGame.getPlayer2().setSetScore(6);
		tennisGame.getPlayer1().setSetScore(5);
		tennisGame.getPlayer2().winSet();
		String expectedSetScore = "Player1:5\nPlayer2:7\nPlayer2 win the set";
		assertThat(tennisGame.displaySetScore()).isEqualTo(expectedSetScore);
	}

	@Test
	public void should_display_tie_break_one_for_player1_when_both_score_set_are_six_and_player1_win_point() {
		tennisGame.getPlayer1().setSetScore(6);
		tennisGame.getPlayer2().setSetScore(6);
		tennisGame.getPlayer1().winTieBreak();
		String expectedTieBreak = "Player1:1\nPlayer2:0";
		assertThat(tennisGame.displayTieBreakScore()).isEqualTo(
				expectedTieBreak);
	}

	@Test
	public void should_display_tie_break_as_six_for_player1_and_five_for_player2() {
		tennisGame.getPlayer1().setSetScore(6);
		tennisGame.getPlayer2().setSetScore(6);
		tennisGame.getPlayer1().setTieBreakScore(6);
		tennisGame.getPlayer2().setTieBreakScore(5);
		String expectedTieBreak = "Player1:6\nPlayer2:5";
		assertThat(tennisGame.displayTieBreakScore()).isEqualTo(
				expectedTieBreak);
	}

	@Test
	public void should_display_set_and_match_winner_as_player1_when_his_tie_break_is_seven() {
		tennisGame.getPlayer1().setSetScore(6);
		tennisGame.getPlayer2().setSetScore(6);
		tennisGame.getPlayer1().setTieBreakScore(6);
		tennisGame.getPlayer2().setTieBreakScore(2);
		tennisGame.getPlayer1().winTieBreak();
		String expectedTieBreak = "Player1:0\nPlayer2:0\nPlayer1 win the set and the match";
		String expectedSetScore = "Player1:7\nPlayer2:6";
		assertThat(tennisGame.displayTieBreakScore()).isEqualTo(
				expectedTieBreak);
		assertThat(tennisGame.displaySetScore()).isEqualTo(
				expectedSetScore);
	}

}
