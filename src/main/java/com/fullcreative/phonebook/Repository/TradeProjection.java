package com.fullcreative.phonebook.Repository;


import org.springframework.beans.factory.annotation.Value;


public interface TradeProjection {

	String getAction();

	@Value("#{target.value}")
	String getSymbolAndAction();


}

