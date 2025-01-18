package ch.bbw.snusShop.productservice.repository;

import ch.bbw.snusShop.productservice.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
