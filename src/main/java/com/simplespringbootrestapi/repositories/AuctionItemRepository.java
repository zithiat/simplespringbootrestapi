package com.simplespringbootrestapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.simplespringbootrestapi.models.AuctionItem;

public interface AuctionItemRepository extends MongoRepository<AuctionItem, String>{

}
