package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.domains.trainer.Trainer;
import com.uktamjon.sodikov.services.SummaryService;
import com.uktamjon.sodikov.domains.summary.Summary;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SummaryController {

     private final SummaryService summaryService;


     @PostMapping("/modify")
     public ResponseEntity<Void> modify(@RequestBody @NotNull Trainer trainer) {
          summaryService.modifyWorkload(trainer);
          return ResponseEntity.ok().build();
     }


     @GetMapping("/summary")
     public ResponseEntity<Summary> getSummaries(@RequestBody int id){
         return ResponseEntity.ok( summaryService.getSummaryByTrainerId(id));
     }

}
