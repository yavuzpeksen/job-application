package com.kodgemisi.assignment.components;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomStringGenerator {
	
	Random random = new Random();
	
	public String generateRandomString(int length){
    return random.ints(48,122)
            .filter(i-> (i<57 || i>65) && (i <90 || i>97))
            .mapToObj(i -> (char) i)
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
	}
}
