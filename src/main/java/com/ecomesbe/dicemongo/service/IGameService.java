package com.ecomesbe.dicemongo.service;

import java.util.List;

import com.ecomesbe.dicemongo.dto.Game;

public interface IGameService {

	//Delete all games of a player
	void deleteAllGamesByPlayer(int player);
	
	//View all games of a player
	List<Game> findGamesByPlayer(int player);
	
	//New Game
	Game newGame(int player); 
}
