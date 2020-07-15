package com.simplespringbootrestapi.services;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.simplespringbootrestapi.models.Bid;

@Aspect
@Configuration
public class LoggerAspect {
	@After("execution(* com.example.demo.controllers.BidController.*(..)) &&args(bid)")
	public void logAdvice(JoinPoint jp, Bid bid) throws InterruptedException {
		System.out.println(LocalDateTime.now() + ", method=" + jp.getSignature().getName() + ", auctionItemId=" + bid.getAuctionItemId() + ", bidderName=" + bid.getBidderName());
	}
}
