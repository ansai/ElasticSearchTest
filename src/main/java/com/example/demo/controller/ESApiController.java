package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ESSearchInterface;
import com.example.demo.util.ESClient;

/**
 * API Controller to perform a search on the request sent / user typed
 *
 * @author 
 * @version 1.0
 */

@RestController
public class ESApiController {
	private static Logger LOG = LoggerFactory.getLogger(ESClient.class);
	private static final String SRICT_NO_CACHE = "no-cache, no-store, max-age=0, must-revalidate";

	@Autowired
	ESSearchInterface esSearchIface = null;

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<String> search(@RequestParam(name = "q", required = true) String utterance) {
		String result = null;
		LOG.debug("ESAPICOntroller invoked");
		try {
			// Sanitize the Input to check if it contains any security related hacks like XSS checks
			// To improve performance, implement caching mechanism (like Redis) and lookup the user entered request in cache (after removing special characters)
			// And fetch from catch if a match found. This will increase the turn around time, and reduce network calls
			
			org.springframework.util.MultiValueMap<String, String> headers = new HttpHeaders();
			headers.set(HttpHeaders.CACHE_CONTROL, SRICT_NO_CACHE);
			result = esSearchIface.searchByKeyword(utterance);
			if (result == null || result.isEmpty()) {
				return new ResponseEntity<>("Sorry, the said utterance is not found in Elastic Search", headers,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(esSearchIface.searchByKeyword(utterance), headers, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
