package com.example.demo.service;

/**
 * Interface layer exposing the methods to interact with Elastic Search Client
 * 
 * @author anagasai
 *
 */

public interface ESSearchInterface {

	public String searchByKeyword(String utterance) throws Exception;

}
