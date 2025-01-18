package ch.bbw.snusShop.productservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import ch.bbw.snusShop.productservice.model.Product;
import ch.bbw.snusShop.productservice.repository.ProductRepository;

@Configuration
@RequiredArgsConstructor
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product product2 = new Product("Velo x freeze", 10);
            Product product3 = new Product("Velo hugo", 10);
            Product product4 = new Product("Velo crispy peppermint", 10);
            Product product5 = new Product("Pablo", 10);
            Product product6 = new Product("Siberia white pouches", 10);
            Product product7 = new Product("Siberia white pouches slim", 10);
            Product product8 = new Product("Eledwis strong", 10);
            Product product9 = new Product("Odens", 8);
            Product product10 = new Product("Odens slim", 8);
            productRepository.saveAll(
                    List.of(product2, product3, product4, product5, product6, product7, product8, product9, product10)
            );
        };
    }
}
