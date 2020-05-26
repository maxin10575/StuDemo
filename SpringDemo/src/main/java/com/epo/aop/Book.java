package com.epo.aop;

import org.springframework.stereotype.Service;

@Service("book1111")
public class Book {

	public void add() {
		System.out.println("add111........");
	}
}
