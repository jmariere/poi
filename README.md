Happn test
===

## Description
The purpose of this application is to compute population density from an input file

## How is work ?
Before compiling the application you have to put your file in the resources directory

This application is a spring boot application so tomcat is embedded in the compiled jar file.

## Api routing
Actually two URI are available : 
* http://localhost:8080/numberByZone?min_lat=6.5&min_lon=-7

NOTE: min_lat and min_lon are mandatories fields

* http://localhost:8080/betterZone?nb_zone=2

NOTE: nb_zone is mandatory field

