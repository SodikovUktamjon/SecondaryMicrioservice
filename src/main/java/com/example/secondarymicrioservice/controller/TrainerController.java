package com.example.secondarymicrioservice.controller;

import com.example.secondarymicrioservice.model.trainer.Trainer;
import com.example.secondarymicrioservice.service.TrainerWorkloadService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TrainerController {

     private final TrainerWorkloadService trainerWorkloadService;


     @PostMapping("/modifyworkload")
     public ResponseEntity<Void> createTrainer(@RequestBody @NotNull Trainer trainer) {
          trainerWorkloadService.modifyWorkload(trainer);
          return ResponseEntity.ok().build();
     }
}
