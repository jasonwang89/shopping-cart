package com.shopping.cart;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(final String[] args) {
		setupBuilder(new SpringApplicationBuilder()).run(args);
	}

	private static SpringApplicationBuilder setupBuilder(final SpringApplicationBuilder builder) {
		return builder.sources(ShoppingCartApplication.class).initializers(context -> {
		});
	}
}
