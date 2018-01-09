package com.kata.game.tennis;

import static org.assertj.core.api.Assertions.assertThat;


import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class TennisGameUnitTest {

	private TennisGame tennisGame;

	@Before
	public void setUp() {
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		tennisGame = new TennisGame(player1, player2);

	}

	@Test
	@Parameters({
		"0,0,Player1:0\nPlayer2:0",
		"1,0,Player1:15\nPlayer2:0",
		"2,0,Player1:30\nPlayer2:0",
		"2,1,Player1:30\nPlayer2:15",
		"3,1,Player1:40\nPlayer2:15",
		"3,2,Player1:40\nPlayer2:30", 
		"2,4,Player1:0\nPlayer2:0\nPlayer2 win the game",
		"3,4,Player1:40\nPlayer2:ADV"
	})
	public void should_display_current_score_at_any_moment_of_the_game(
			int pointsWonByPlayer1, int pointsWonByPlayer2, String expectedScore) {
		addPointsForPlayer(tennisGame.getPlayer1(), pointsWonByPlayer1);
		addPointsForPlayer(tennisGame.getPlayer2(), pointsWonByPlayer2);
		assertThat(tennisGame.displayCurrentScore()).isEqualTo(expectedScore);

	}
	
	@Test 
	public void shoud_display_Deuce_when_both_players_reach_forty(){
		addPointsForPlayer(tennisGame.getPlayer1(), 2);
		addPointsForPlayer(tennisGame.getPlayer2(), 3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore="Player1:DEUCE\nPlayer2:DEUCE";
		assertThat(tennisGame.displayCurrentScore()).isEqualTo(expectedScore);
	}
	
	@Test 
	public void shoud_display_player1_has_advantage_when_score_is_Deuce_and_player1_win_point(){
		addPointsForPlayer(tennisGame.getPlayer1(), 3);
		addPointsForPlayer(tennisGame.getPlayer2(), 3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore="Player1:ADV\nPlayer2:40";
		assertThat(tennisGame.displayCurrentScore()).isEqualTo(expectedScore);
	}
	
	@Test
	public void should_display_player1_as_winner_when_he_had_advantage_and_win_point(){
		addPointsForPlayer(tennisGame.getPlayer1(), 4);
		addPointsForPlayer(tennisGame.getPlayer2(), 3);
		tennisGame.getPlayer1().winPoint();
		String expectedScore="Player1:0\nPlayer2:0\nPlayer1 win the game";
		assertThat(tennisGame.displayCurrentScore()).isEqualTo(expectedScore);
	}
	
	@Test
	public void should_display_deuce_when_player1_had_advantage_and_lose_point(){
		addPointsForPlayer(tennisGame.getPlayer1(), 4);
		addPointsForPlayer(tennisGame.getPlayer2(), 3);
		tennisGame.getPlayer1().losePoint();
		String expectedScore="Player1:DEUCE\nPlayer2:DEUCE";
		assertThat(tennisGame.displayCurrentScore()).isEqualTo(expectedScore);
	}

	private void addPointsForPlayer(Player player, int pointsWonByPlayer) {
		for (int i = 0; i < pointsWonByPlayer; i++) {
			player.winPoint();
		}

	}
}
