Feature: Modify Trainer Workload

  Scenario: Modify workload for a trainer
    Given a trainer with ID 1 exists in the system
    When I modify the workload for the trainer
    Then the workload should be updated in the summary
