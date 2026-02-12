package com.abi.chocolate_shop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ChocolateRepository extends JpaRepository<Chocolate, Long> {
}
