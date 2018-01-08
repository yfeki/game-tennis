package com.kata.game.tennis;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TennisGameUnitTest {

	
	private TennisGame tennisGame; 
	
	@Before
	public void setUp(){
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		tennisGame= new TennisGame(player1,player2);
		
	}
	
	@Test
	public void should_display_fifteen_zero_when_player1_wins(){
		String score = "Player1:15 | Player2:0";
		tennisGame.getPlayer1().winPoint();
		assertThat(tennisGame.displayCurrentScore().equals(score));
		
	}
	
	@Test
	public void should_display_Thirty_zero_when_player1_wins_two_points(){
		String score = "Player1:30 | Player2:0";
		tennisGame.getPlayer1().winPoint();
		tennisGame.getPlayer1().winPoint();
		assertThat(tennisGame.displayCurrentScore().equals(score));
		
	}
	
	@Test
	public void should_display_Thirty_fifteen_when_player1_won_two_points_and_palyer2_wins_one_point(){
		String score = "Player1:30 | Player2:15";
		tennisGame.getPlayer1().winPoint();
		tennisGame.getPlayer1().winPoint();
		tennisGame.getPlayer2().winPoint();
		assertThat(tennisGame.displayCurrentScore().equals(score));
		
	}
}
