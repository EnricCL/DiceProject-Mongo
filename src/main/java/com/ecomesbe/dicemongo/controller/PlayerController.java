package com.ecomesbe.dicemongo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomesbe.dicemongo.service.impl.PlayerServiceImpl;

@RestController
@RequestMapping("/dicegame")
public class PlayerController {
	
	@Autowired
	PlayerServiceImpl playerService;
	
	//POST: /players : crear un jugador
	@PostMapping("/players")
	public ResponseEntity<Object> createNewPlayer(@RequestBody HashMap<String, String> newPlayer){
		if(newPlayer.containsKey("name")) {
			return ResponseEntity.ok().body(playerService.saveNewPlayer(newPlayer.get("name")));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data no accepted");
		}
	}
	
	//PUT /players : modifica el nom del jugador
	@PutMapping("/players")
	public ResponseEntity<Object> editPlayer(@RequestBody HashMap<String, String> modPlayer){
		if(modPlayer.containsKey("name")) {
			return ResponseEntity.ok().body(playerService.editPlayer(modPlayer.get("name"),
																	Integer.parseInt(modPlayer.get("id"))
					));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data no accepted");
		}
	}
	
	//GET /players/: retorna el llistat de tots els jugadors del sistema amb el seu % mig d’èxits
	@GetMapping("/players")
	public ResponseEntity<Object> showAllPlayers(){
		return ResponseEntity.ok().body(playerService.showAllPlayers());
	}
	
	//GET /players/ranking: retorna el ranking mig de tots els jugadors del sistema. El % mig d’èxits.
	@GetMapping("/players/ranking")
	public ResponseEntity<Object> showSuccessAll(){
		return ResponseEntity.ok().body(playerService.getAllSuccess());
	}
		
	//GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<Object> showLoser(){
		return ResponseEntity.ok().body(playerService.getLoser());
	}
	
	//GET /players/ranking/winner: retorna el jugador amb millor percentatge d’èxit
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<Object> showWinner(){
		return ResponseEntity.ok().body(playerService.getWinner());
	}

}
