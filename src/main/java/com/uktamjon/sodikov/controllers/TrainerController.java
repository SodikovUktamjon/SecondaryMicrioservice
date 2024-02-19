package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.services.TrainerWorkloadService;
import com.uktamjon.sodikov.summary.Summary;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TrainerController {

     private final TrainerWorkloadService trainerWorkloadService;


     @PostMapping("/create")
     public ResponseEntity<Trainer> createTrainer(@RequestBody @NotNull Trainer trainer) {
          Trainer trainer1 = trainerWorkloadService.trainer(trainer);
          return ResponseEntity.ok(trainer1);
     }

     @DeleteMapping("/delete")
     public ResponseEntity<Void> deleteTrainer(@RequestBody int id){
          trainerWorkloadService.deleteTrainer(id);
          return ResponseEntity.ok(null);


     }


     @GetMapping("/summary")
     public ResponseEntity<Summary> getReport(@RequestBody int id){
         return ResponseEntity.ok( trainerWorkloadService.getSummary(id));

     }

}
