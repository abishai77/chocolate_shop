package com.abi.chocolate_shop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChocolateRepository extends JpaRepository<Chocolate, Long > {
    List<Chocolate> findByNameContainingIgnoreCase(String name);
}
