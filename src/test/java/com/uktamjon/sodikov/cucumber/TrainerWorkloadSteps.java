package com.uktamjon.sodikov.cucumber;

import com.uktamjon.sodikov.domains.summary.Summary;
import com.uktamjon.sodikov.domains.trainer.Trainer;
import com.uktamjon.sodikov.enums.ActionType;
import com.uktamjon.sodikov.services.SummaryService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
public class TrainerWorkloadSteps {
    private Trainer trainer;
    @Autowired
    private SummaryService summaryService;
    private Summary summary;

    @Given("a trainer with ID {int} exists in the system")
    public void a_trainer_with_ID_exists_in_the_system(int trainerId) {
        trainer= Trainer.builder()
                .id(trainerId)
                .username("SomeUser")
                .startDate(LocalDateTime.parse("2021-01-01T00:00:00"))
                .duration(2000)
                .firstName("Some")
                .lastName("User")
                .actionType(ActionType.ADD)
                .build();
    }

    @When("I modify the workload for the trainer")
    public void i_modify_the_workload_for_the_trainer() {
          summaryService.modifyWorkload(trainer);
    }

    @Then("^the workload should be updated in the summary$")
    public void the_workload_should_be_updated_in_the_summary() {
        assertNotNull(summaryService.getSummaryByTrainerUserName(trainer.getUsername()));
    }
}
