# scenario-quality-checker
<img align="right" src="https://travis-ci.com/adrianmisko/scenario-quality-checker.svg?branch=master" />

**Academic project for Software Engineering**


This application is made with Java and Spring MVC. It exposes RESTful API that uses JSON as data-interchange format.
Full documentation will be avaiable soon.

Structure of scenario:
* header:
   * title
   * actors
   * system actors
* list of steps:
   * keyword (IF, ELSE, FOR EACH)
   * actor
   * system actor
   * text
   * another scenario (*subscenario*)

