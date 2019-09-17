package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Arrays;



import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.ESResponse;
import com.example.demo.model.Hit;
import com.example.demo.model.Hits;
import com.example.demo.model.Source;
import com.example.demo.service.ESSearchInterface;
import com.example.demo.util.ESClient;
import com.example.demo.util.HttpClient;
import com.example.demo.util.MockApplication;

//@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = MockApplication.class, initializers = {MockApplication.class})
@SpringBootTest
public class ESSearchTest {
//	@Autowired
	@MockBean
	ESClient esClient;

	@MockBean
	HttpClient httpClient;
	
	@Autowired
	ESSearchInterface esSearchInterfaceImpl;
	@Test
	public void contextLoads() {
	}
	
	/**
	 * Test to check if expected response is matching, this is a positive case 
	 * @throws Exception
	 */
	@Test
	public void searchExistingQuestion() throws Exception {
		ESResponse esResponse = new ESResponse();
		
		Hits hits = Mockito.mock(Hits.class);
		esResponse.setHits(hits);
		Hit hit = new Hit();
		hit.setScore(5.0);
		Source source = new Source();
		source.setAnswer("NEFT is a type of funds transfer with minimum transfer required to be RS. 100");
		source.setQuestion("what is neft");
		source.setFaqId("FAQ-1");
		source.setCategory("General");
		hit.setSource(source);
		
//		esClient = Mockito.mock(ESClient.class);
//		 BDDMockito.given(esClient.search(Mockito.eq("neft"))).willReturn(esResponse);
		BDDMockito.given(hits.getHits()).willReturn(Arrays.asList(hit));
		BDDMockito.given(esClient.search(Mockito.anyString(), Mockito.anyString())).willReturn(esResponse);
		
		String msg = esSearchInterfaceImpl.searchByKeyword("neft");
		System.out.println("Found Answer: " + msg);
		org.junit.Assert.assertTrue(msg.contains("NEFT is a type of "));
		assertEquals(1, esResponse.getHits().getHits().size());
	}
	
	/**
	 * Test to check if expected response is not matching 
	 * @throws Exception
	 */
	@Test
	public void searchNonExistingQuestion() throws Exception {
		ESResponse esResponse = new ESResponse();
		
		Hits hits = Mockito.mock(Hits.class);
		esResponse.setHits(hits);
		Hit hit = new Hit();
		hit.setScore(5.0);
		Source source = new Source();
		source.setAnswer("NEFT is a type of funds transfer with minimum transfer required to be RS. 100");
		source.setQuestion("what is neft");
		source.setFaqId("FAQ-1");
		source.setCategory("General");
		hit.setSource(source);
		
		BDDMockito.given(hits.getHits()).willReturn(Arrays.asList(hit));
		BDDMockito.given(esClient.search(Mockito.anyString(), Mockito.anyString())).willReturn(esResponse);
		
		String msg = esSearchInterfaceImpl.searchByKeyword("rtgs");
		System.out.println("Unmatched Answer: " + msg);
		org.junit.Assert.assertTrue(!msg.contains("RTGS is a type of "));
		assertEquals(1, esResponse.getHits().getHits().size());
	}
	
	
}
