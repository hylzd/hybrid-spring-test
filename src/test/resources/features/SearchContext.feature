@HappySearch
  Feature: search context on toutiao
    I want to search context by free text on toutiao

  Scenario Outline: input free text to search
    Given input "<searchText>" on welcome page
    Then click on the search button
    Then related context is listed

    Examples:
    |searchText|
    |test|