package com.inatlas;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InAtlasApplicationTests {

	private MockMvc mockMvc;

	private static final String FREE_ESPRESSO_PROMOTION = "You get free espresso for buying more than one latte!";
	private static final String PROMOTION_MORE_EIGHT_PRODUCTS = "You have more than 8 products, we give you a 5% discount on your total purchase!";
	private static final String PROMOTION_MORE_FIFTY = "Your order has food and drinks with a total value of more than $50, each Latte you have ordered is reduced to $3.";

	private static final String LATTE = "Latte";

	private static final Object TEA = "Tea";

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// Obtener Menú
	@Test
	public void testGetMenu() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/product/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value(LATTE))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].productPrice").value(5.3))
				.andExpect(MockMvcResultMatchers.jsonPath("$[6].productName").value(TEA))
				.andExpect(MockMvcResultMatchers.jsonPath("$[6].productPrice").value(6.1)).andExpect(status().isOk());
	}

	// Orden de 3 lattes
	@Test
	public void testPromotionFreeEspresso() throws Exception {
		String requestBody = "[{\"product\":{\"productName\":\"Latte\"}},{\"product\":{\"productName\":\"Latte\"}}]";

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/saleOrder/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descriptionDiscount").value(FREE_ESPRESSO_PROMOTION))
				.andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.saleOrderDiscountTotal").value(4))
				.andExpect(status().isOk());
	}

	// Orden de 8 prodcutos
	@Test
	public void testPromotionMoreEightProducts() throws Exception {
		String requestBody = "[{\"product\":{\"productName\":\"Milk\"}},{\"product\":{\"productName\":\"Milk\"}},"
				+ "{\"product\":{\"productName\":\"Milk\"}},{\"product\":{\"productName\":\"Milk\"}},"
				+ "{\"product\":{\"productName\":\"Milk\"}},{\"product\":{\"productName\":\"Milk\"}},"
				+ "{\"product\":{\"productName\":\"Milk\"}},{\"product\":{\"productName\":\"Milk\"}},"
				+ "{\"product\":{\"productName\":\"Milk\"}},{\"product\":{\"productName\":\"Milk\"}}]";

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/saleOrder/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descriptionDiscount").value(PROMOTION_MORE_EIGHT_PRODUCTS))
				.andExpect(MockMvcResultMatchers.jsonPath("$.saleOrderTotalFull").value(10))
				.andExpect(MockMvcResultMatchers.jsonPath("$.saleOrderDiscountTotal").value(10*0.05))
				.andExpect(status().isOk());
	}

	// Orden mayor o igual a $50
	@Test
	public void testPromotionMoreFifty() throws Exception {
		String requestBody = "[{\"product\":{\"productName\":\"Latte\"}},{\"product\":{\"productName\":\"Latte\"}},"
				+ "{\"product\":{\"productName\":\"Latte\"}},{\"product\":{\"productName\":\"Latte\"}},"
				+ "{\"product\":{\"productName\":\"Latte\"}},{\"product\":{\"productName\":\"Sandwich\"}},"
				+ "{\"product\":{\"productName\":\"Latte\"}},{\"product\":{\"productName\":\"Latte\"}},"
				+ "{\"product\":{\"productName\":\"Cake Slice\"}}]";

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/saleOrder/").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(requestBody))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.descriptionDiscount").value(PROMOTION_MORE_FIFTY))
				.andExpect(status().isOk());
	}
}
