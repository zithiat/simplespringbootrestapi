package com.simplespringbootrestapi.models;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "bid")
public class Bid {
	private String auctionItemId;
	private double maxAutoBidAmount;
	private String bidderName;
}
