package com.simplespringbootrestapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simplespringbootrestapi.models.Bid;

public interface BidRepository extends MongoRepository<Bid, String> {

}
