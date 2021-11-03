package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaSpringStarterApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void isHealthy() throws Exception {
		mockMvc.perform(get("/health"))
				.andExpect(status().isOk());
	}


	@Test
	void addBookToLibrary() throws Exception {


		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(turnToJson((new Book("Douglas Adams", "The guide", 1979))))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Douglas Adams"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The guide"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.yearPublished").value(1979));

		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(turnToJson((new Book("Austin Barrett", "Another book", 2021))))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Austin Barrett"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Another book"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.yearPublished").value(2021));

		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(turnToJson((new Book("Austin Barrett", "Middle book", 2021))))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Austin Barrett"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Middle book"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.yearPublished").value(2021));
	}

	@Test
	void getBooksFromLibrary() throws Exception {
		mockMvc.perform(get("/api/books")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void deleteAllBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/books"))
				.andExpect(status().isNoContent());
	}

	private static String turnToJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

	}
}
