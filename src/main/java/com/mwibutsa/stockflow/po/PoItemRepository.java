package com.mwibutsa.stockflow.po;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PoItemRepository extends JpaRepository<PoItem, UUID> {
}