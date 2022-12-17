Feature: Mercado libre search and filter
@123
    Scenario: Search product by condition
        Given the user is in mercado libre and selects "Bolivia"
        When the user searches for "iphone"
        When the user filters by condition "Nuevo"
        Then the user validates at least 3 products