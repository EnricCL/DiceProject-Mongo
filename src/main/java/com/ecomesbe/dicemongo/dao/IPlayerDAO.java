package com.ecomesbe.dicemongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecomesbe.dicemongo.dto.Player;

public interface IPlayerDAO extends MongoRepository<Player, Integer> {

	Player findByName(String name);
}
