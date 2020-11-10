package com.ecomesbe.dicemongo.service;

import java.util.HashMap;
import java.util.List;

import com.ecomesbe.dicemongo.dto.Player;

public interface IPlayerService {

	//--CRUD
	
	//GET all
	public List<Player> showAllPlayers();
	
	//POST
	public Player saveNewPlayer(String player);
	
	//PUT
	public Player editPlayer(Player player);
	
	
	//--RANKING
	
	//GET ALL SUCCESS
	public HashMap<String, Object> getAllSuccess();
	
	//GET WINNER
	public HashMap<String, Object> getWinner();
	
	//GET LOSER
	public HashMap<String, Object> getLoser();
	
}
