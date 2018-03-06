package com.kodgemisi.assignment.services;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
	
	public TestBean(){
		System.out.println("Test Bean instance was created");
	}
	
	public void doNothing(){
		System.out.println(this);

	}
}
