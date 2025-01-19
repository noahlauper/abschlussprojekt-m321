package ch.bbw.snusshop.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bbw.snusshop.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
