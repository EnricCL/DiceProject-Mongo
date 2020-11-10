package com.ecomesbe.dicemongo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Document(collection="players")
public class Player {
	
	@Id
	private Integer id;
	
	private String name;
	
	private LocalDate date = LocalDate.now();
		
	private float success;
	
	private int gameWin;
	
	private int gameLose;
	
	private int gameTotal;
	
	@JsonIgnore
	List<Game> games = new ArrayList<>();
	
	public Player() {
	}

	public Player(Integer id, String name, List<Game> games, LocalDate date, float success) {
		this.id = id;
		this.name = name;
		this.games = games;
		this.date = date;
		this.success = success;
	}
	
	
	public Player(Integer id, String name, List<Game> games, LocalDate date) {
		this.id = id;
		this.name = name;
		this.games = games;
		this.date = date;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public float getSuccess() {
		return success;
	}

	public void setSuccess(float success) {
		this.success = success;
	}

	public int getGameWin() {
		return gameWin;
	}

	public void setGameWin(int gameWin) {
		this.gameWin = gameWin;
	}

	public int getGameLose() {
		return gameLose;
	}

	public void setGameLose(int gameLose) {
		this.gameLose = gameLose;
	}

	public int getGameTotal() {
		return gameTotal;
	}

	public void setGameTotal(int gameTotal) {
		this.gameTotal = gameTotal;
	}

}
