package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {


    @Transactional
    @Modifying
    @Query("delete from trainer_workload t where t.id = ?1")
    void deleteById(int id);
}
