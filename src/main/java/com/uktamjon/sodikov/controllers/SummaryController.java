package com.uktamjon.sodikov.controllers;


import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.services.SummaryService;
import com.uktamjon.sodikov.domains.summary.Summary;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SummaryController {

     private final SummaryService summaryService;


     @PostMapping("/modify")
     public ResponseEntity<Void> modify(@RequestBody @NonNull Trainer trainer) {
          log.info("Modifying workload with trainerId: {}", trainer.getId());
          summaryService.modifyWorkload(trainer);
          return ResponseEntity.ok().build();
     }


          @GetMapping("/summary")
          public ResponseEntity<Summary> getSummaries(@RequestBody int id){
               log.info("Getting workload of trainer by trainerId: {}", id);
               return ResponseEntity.ok( summaryService.getSummaryByTrainerId(id));
     }

}
