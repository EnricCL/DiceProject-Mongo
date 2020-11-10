package com.ecomesbe.dicemongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecomesbe.dicemongo.dto.Game;

public interface IGameDAO  extends MongoRepository<Game, Integer>{

	List<Game> findGamesByPlayerId (int playerId);
}
