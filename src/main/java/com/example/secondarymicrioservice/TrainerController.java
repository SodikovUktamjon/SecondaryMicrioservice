package com.example.secondarymicrioservice;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
