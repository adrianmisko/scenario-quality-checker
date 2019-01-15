# scenario-quality-checker
<img align="right" src="https://travis-ci.com/adrianmisko/scenario-quality-checker.svg?branch=master" />

**Academic project for Software Engineering**


This application is made with Java and Spring MVC. It exposes RESTful API that uses JSON as data-interchange format.

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
   
Link to product backlog on Trello: https://trello.com/b/zO5wBOFS

Example of graph created by GraphBuilder:


![graph](https://github.com/adrianmisko/scenario-quality-checker/blob/master/graph.png)


To create your own graph from dot file make GET request at /scenarios/{id}/graph,
save this to new file graph.dot and enter to your console: dot -Tpng graph.dot -o outfile.png | eog outfile.png.

