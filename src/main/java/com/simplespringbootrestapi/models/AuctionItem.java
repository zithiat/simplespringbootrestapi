package com.simplespringbootrestapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "auctionItem")
public class AuctionItem {
	@Id
	private @NonNull String auctionItemId;
	private Item item;
	private double currentBid;
	private double reservePrice;
	private String bidderName;
}
