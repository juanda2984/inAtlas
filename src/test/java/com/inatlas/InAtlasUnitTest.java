package com.inatlas;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatlas.entities.Product;
import com.inatlas.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InAtlasUnitTest {
	@Autowired
	private ProductRepository productRepository;

	private static final String FRAPUCCINO = "Frapuccino";

	// Insertar un producto
	@Test
	public void testSaleOrderEntity() throws ParseException, JsonProcessingException {
		Product product = new Product();
		product.setProductName(FRAPUCCINO);
		productRepository.save(product);
		assertTrue(FRAPUCCINO.equalsIgnoreCase(productRepository.findByProductName(FRAPUCCINO).getProductName()));
	}
}
