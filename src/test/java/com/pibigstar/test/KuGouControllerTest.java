package com.pibigstar.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KuGouControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testKugouController() throws Exception{
//		RequestBuilder request = null;
//		
//		request = post("/music/seach")
//				.param("type", "1")
//				.param("music", "说散就散");
//		mvc.perform(request).andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}
