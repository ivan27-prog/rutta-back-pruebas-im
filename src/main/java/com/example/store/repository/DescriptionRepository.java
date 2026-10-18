package com.example.store.repository;

import com.example.store.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}