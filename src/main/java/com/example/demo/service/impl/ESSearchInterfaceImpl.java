package com.example.demo.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.ESResponse;
import com.example.demo.service.ESSearchInterface;
import com.example.demo.util.AppConstants;
import com.example.demo.util.ESClient;

/**
 * Implementation class to interact with Elastic Search Client
 * We make a call to Elastic client to perform the search on user entered utterance
 * @author anagasai
 *
 */
// @Component
@Service
public class ESSearchInterfaceImpl implements ESSearchInterface {
//	String esURL = "http://localhost:9200/";
	private static Logger LOG = LoggerFactory.getLogger(ESSearchInterfaceImpl.class);
	@Autowired
	ESClient esClient;

	@Override
	public String searchByKeyword(String utterance) throws Exception {
		ESResponse esResponse = null;
		StringBuffer foundAnswers = new StringBuffer();
		try {
			esResponse = esClient.search(utterance, AppConstants.ELASTIC_URL);
			if (esResponse != null) {
				List<String> list = esResponse.getHits().getHits().stream().filter(s -> s.getScore() > 0.4)
						.map(s -> s.getSource().getAnswer()).collect(Collectors.toList());
				if (list.isEmpty()) {
					LOG.debug("There is no matching query found in ES for the input utterance");
					return foundAnswers.toString();
				} else {
					Iterator<String> itrList = list.iterator();
					while (itrList.hasNext()) {
						foundAnswers.append(itrList.next().toString());
					}
				}
			} else {
				LOG.debug("Index is not found in Elastic Search");
				return foundAnswers.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return foundAnswers.toString();

	}

	boolean checkScore(int score) {
		return score > AppConstants.minScore;
	}

}
