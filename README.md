gwtjerseyjpaexample

Blublucar - TP TAA/GLI Blublucar

The goal of this project is to build a website from a Java classes model and services, and then to use both GWT and AngularJS, with a tomcat server.

This provide an example for the integration of a GWT web app that call a json restfull webservices developped using jersey. 

It uses autobean to do the marshalling/unmarshalling of JSON Object

To run it

```bash
mvn clean compile gwt:compile  package tomcat7:run-war-only
