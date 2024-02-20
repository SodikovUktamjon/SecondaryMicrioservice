package com.example.secondarymicrioservice.repository;

import com.example.secondarymicrioservice.model.summary.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
}
