package com.simplespringbootrestapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.simplespringbootrestapi.models.AuctionItem;
import com.simplespringbootrestapi.repositories.AuctionItemRepository;

@RestController
public class AuctionItemController {
	@Autowired
	AuctionItemRepository auctionRepository;
	
	@PostMapping("/auctionItem")
	public Map<String, String> addAuctionItem(@RequestBody AuctionItem item) {
		String id = String.valueOf(new Random().nextInt());
		System.out.println(item);
		AuctionItem ai = new AuctionItem(id, item.getItem(), item.getCurrentBid(), item.getReservePrice(), item.getBidderName());
		auctionRepository.insert(ai);
		// For custom response
		HashMap<String, String> responseMap = new HashMap<>();
		responseMap.put("auctionItemId", id);
		return responseMap;
	}
		
	@GetMapping("/auctionItems")
	public List<AuctionItem> getAuctionItems() {
		List<AuctionItem> auctionItems = auctionRepository.findAll();
		return auctionItems;
	}
	
	@GetMapping("/auctionItem/{id}")
	public Optional<AuctionItem> getAuctionItem(@PathVariable String id) {
		Optional<AuctionItem> auctionItem = auctionRepository.findById(id);
		return auctionItem;
	}
	
	@DeleteMapping("/deleteAuctionItems")
	public void deleteAll() {
		auctionRepository.deleteAll();
	}
}
