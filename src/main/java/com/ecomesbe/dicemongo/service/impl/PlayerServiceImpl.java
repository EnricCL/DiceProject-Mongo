package com.ecomesbe.dicemongo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomesbe.dicemongo.dao.IPlayerDAO;
import com.ecomesbe.dicemongo.dto.Player;
import com.ecomesbe.dicemongo.service.IPlayerService;

@Service
public class PlayerServiceImpl implements IPlayerService {

	@Autowired
	private IPlayerDAO iPlayerDao;
	
	@Autowired
	private PlayerServiceImpl playerService;
	
	//GET all players
	@Override
	public List<Player> showAllPlayers(){
		return iPlayerDao.findAll();
	}

	//POST save new player
	@Override
	public Player saveNewPlayer(String name) {
		Player player = new Player(name);
		
		List<Player> allPlayers = new ArrayList<Player>();
		allPlayers = playerService.showAllPlayers();
		if(name=="") player.setName("ANÒNIM");
		else {
			int total = 0;
			for(int i=0; i<allPlayers.size(); i++) {
				if(name.equals(allPlayers.get(i).getName())) {
					throw new IllegalArgumentException("This player already exists in the database!");
				}else {
					total++;
				}
			}
			if(total == allPlayers.size()) player.setName(name);
			player.setId(allPlayers.size()+1);
		}
		
		return iPlayerDao.save(player);
	}

	
	@Override
	public Player editPlayer(Player player) {
		return null;
	}
	
	//PUT change a player of the database
	//@Override
	public Player editPlayer(String name, int id) {
		
		Optional<Player> playerOptional = iPlayerDao.findById(id);
		
		if(playerOptional.isPresent()) {
			
			Player playerEdit = playerOptional.get();
			
			if(name=="") playerEdit.setName("ANÒNIM");
			else playerEdit.setName(name);
			
			return iPlayerDao.save(playerEdit);
			
		}else {
			throw new IllegalArgumentException("This player not exist!");
		}
		
	}

	@Override
	public HashMap<String, Object> getAllSuccess() {
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			List<Player> playersList = iPlayerDao.findAll();
			float average = (float)0;
			int count = 0;
			for(Player playerFor : playersList) {
				average+=playerFor.getSuccess();
				count++;
			}
			map.put("success", true);
			map.put("Success average: ", average/count);
		}catch(Exception e) {
			map.put("success", false);
			map.put("message: ", e.getMessage());
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getWinner() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Player> playersList = iPlayerDao.findAll();
			Player playerMostWinner = new Player();
			boolean first = true;
			for(Player playerFor : playersList) {
				if(first) {
					playerMostWinner = playerFor;
					first = false;
				}else {
					if(playerMostWinner.getSuccess() < playerFor.getSuccess()) {
						playerMostWinner = playerFor;
					}
				}
			}
			map.put("success", true);
			map.put("Player most successful: ", playerMostWinner);
		}catch(Exception e) {
			map.put("success", false);
			map.put("message: ", e.getMessage());
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getLoser() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Player> playersList = iPlayerDao.findAll();
			Player playerMostLoser = new Player();
			boolean first = true;
			for(Player playerFor : playersList) {
				if(first) {
					playerMostLoser = playerFor;
					first = false;
				}else {
					if(playerMostLoser.getSuccess() > playerFor.getSuccess()) {
						playerMostLoser = playerFor;
					}
				}
			}
			map.put("success", true);
			map.put("Player most loser: ", playerMostLoser);
		}catch(Exception e) {
			map.put("success", false);
			map.put("message: ", e.getMessage());
		}
		return map;
	}
}
