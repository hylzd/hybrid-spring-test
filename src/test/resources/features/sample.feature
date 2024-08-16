Feature: check about summary page
  go to welcome homepage, navigate to about summary page

  Scenario: check about summary
    Given go to welcome homepage
    When navigate to about summary
    Then the summary details should be shown