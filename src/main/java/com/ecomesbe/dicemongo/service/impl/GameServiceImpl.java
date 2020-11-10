package com.ecomesbe.dicemongo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomesbe.dicemongo.dao.IGameDAO;
import com.ecomesbe.dicemongo.dao.IPlayerDAO;
import com.ecomesbe.dicemongo.dto.Game;
import com.ecomesbe.dicemongo.dto.Player;
import com.ecomesbe.dicemongo.service.IGameService;

@Service
public class GameServiceImpl implements IGameService{
	
	@Autowired
	IGameDAO gameDao;
	
	@Autowired
	IPlayerDAO playerDao;

	@Override
	public void deleteAllGamesByPlayer(int player) {
		List<Game> listGames = gameDao.findGamesByPlayerId(player);
		gameDao.deleteAll(listGames);
		
		//The "games" collection is not related to the "players" collection
		//We update the Player directly
		Optional <Player> optionalPlayer = playerDao.findById(player);
		if(optionalPlayer.isPresent()) {
			Player playerToSet = optionalPlayer.get();
			playerToSet.setGameTotal(0);
			playerToSet.setGameWin(0);
			playerToSet.setGameLose(0);
			playerToSet.setGames(listGames = null);
			playerToSet.setSuccess(0);
			playerDao.save(playerToSet);
		}else {
			throw new IllegalArgumentException("This id not exist");
		}
		
	}

	@Override
	public List<Game> findGamesByPlayer(int player) {
		Optional<Player> optionalPlayer = playerDao.findById(player);
		if(optionalPlayer.isPresent()) {
			return gameDao.findGamesByPlayerId(player);
		}else {
			throw new IllegalArgumentException("This id not exist");
		}
	}

	@Override
	public Game newGame(int player) {
		
		int firstDice = ThreadLocalRandom.current().nextInt(1,7);
		int secondDice = ThreadLocalRandom.current().nextInt(1,7);
		boolean success = false;
		int result = firstDice+secondDice;
		if( result == 7 ) {
			success = true;
		}
		
		Game newGame = new Game(firstDice, secondDice, success);
		
		Optional<Player> actualPlayer = playerDao.findById(player);
		
		if(actualPlayer.isPresent()) {
			
			Player playerToSet = actualPlayer.get();
			playerToSet.setGameTotal(actualPlayer.get().getGameTotal() + 1);
			
			if(success) {
				playerToSet.setGameWin(actualPlayer.get().getGameWin() +1);
			}
			else {
				playerToSet.setGameLose(actualPlayer.get().getGameLose() +1);
			}
			
			if(actualPlayer.get().getGameWin() > 0) {
				playerToSet.setSuccess((float)actualPlayer.get().getGameWin() / (float)actualPlayer.get().getGameTotal() * 100);
			}
			
			List<Game> gamesPlayer = gameDao.findGamesByPlayerId(player);
			newGame.setId(gamesPlayer.size()+1);
			newGame.setPlayer(playerToSet);
			playerDao.save(playerToSet);
			
			//because the above conditional does not work well
			if(newGame.getFirstDice() + newGame.getSecondDice() == 7) {
				newGame.setSuccess(true);
			}
			
			return gameDao.save(newGame);
		}else {
			throw new IllegalArgumentException("This id not exist");
		}
		
	}

}
