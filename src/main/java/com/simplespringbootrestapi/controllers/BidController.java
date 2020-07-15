package com.simplespringbootrestapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplespringbootrestapi.models.AuctionItem;
import com.simplespringbootrestapi.models.Bid;
import com.simplespringbootrestapi.repositories.AuctionItemRepository;
import com.simplespringbootrestapi.repositories.BidRepository;
import com.simplespringbootrestapi.services.Logger;

@RestController
public class BidController {

	@Autowired
	BidRepository bidRepository;
	@Autowired
	AuctionItemRepository auctionRepository;
	
	private Logger logger = Logger.getInstance();
	
	@PostMapping("/bid")
	public Map<String, String> addBid(@RequestBody Bid bid) {
		HashMap<String, String> resp = new HashMap<>();
		String id = bid.getAuctionItemId();
		double maxBid = bid.getMaxAutoBidAmount();
		String bidName = bid.getBidderName();
		Optional<AuctionItem> ai = auctionRepository.findById(id);
		// debug purpose only
//		logger.log("ai.isPresent(): " + ai.isPresent());
		if (ai.isPresent()) {
			Bid b = new Bid(id, maxBid, bidName);
			AuctionItem aucItem = ai.get();
			aucItem.setBidderName(bidName);
			aucItem.setBidderName(bid.getBidderName());
			if (aucItem.getReservePrice() == 0) {
				aucItem.setReservePrice(bid.getMaxAutoBidAmount());
				resp.put("Exception", "ReservePrice not met");
			} else {
				bidRepository.insert(b);
				resp.put("auctionItemId", id);
				resp.put("maxAutoBidAmount", maxBid + "");
				resp.put("bidderName", bidName);
			}
			logger.log("Adding auctionItem: " + aucItem);
			logger.log("Adding bid: " + b);
		}
		return resp;
	}
	
	@PostMapping("/bids")
	public List<Bid> addBids(@RequestBody List<Bid> bids) {
		List<Bid> bidList = new ArrayList<>();
		for (Bid b : bids)
			bidList.add(new Bid(b.getAuctionItemId(), b.getMaxAutoBidAmount(), b.getBidderName()));
		return bidList;
	}
	
	@GetMapping("/getbids")
	public List<Bid> getBids() {
		List<Bid> bidList = bidRepository.findAll();
		return bidList;
	}
}
