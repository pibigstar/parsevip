package com.pibigstar.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pibigstar.web.MusicController;

@RunWith(SpringJUnit4ClassRunner.class)
public class KuGouControllerTest {
	
	private MockMvc mvc;
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new MusicController()).build();
	}
	
	@Test
	public void testKugouController() throws Exception{
		RequestBuilder request = null;
		
		request = post("/music/seach")
				.param("type", "0")
				.param("music", "说散就散");
		mvc.perform(request).andExpect(status().isOk());
		
	}
	

}
