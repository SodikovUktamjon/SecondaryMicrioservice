package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.summary.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer> {
     Summary findByTrainerId(int id);

}