package com.ecomesbe.dicemongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="games")
public class Game {
	
	@Id
	private Integer id;
	
	private int firstDice;
	
	private int secondDice;
	
	@JsonIgnore
	@DBRef(db = "players")
	private Player player;
	
	private boolean success;
	
	
	public Game() {
	}
	
	public Game(int first, int second, boolean succes) {
		firstDice = first;
		secondDice = second;
		this.success = success;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getFirstDice() {
		return firstDice;
	}

	public void setFirstDice(int firstDice) {
		this.firstDice = firstDice;
	}

	public int getSecondDice() {
		return secondDice;
	}

	public void setSecondDice(int secondDice) {
		this.secondDice = secondDice;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
