# Census Management System
A system for managing and searching the data released by Word Bank.

Welcome to the Census-Management-System wiki!

#How to use

The system currently features these commands:

##Get the Population Data
`get` command is used to fetch the population data. After entering the `get` command, you will be prompted to specify the name of the country and a year via the command line interface.


##Update the Population Data
`update` command is used to update the population data. After entering the `update` command, you will be prompted to specify the name of the country, a year and the new male and female populations via the command line 

**Note:** `update` is only possible if the country is not protected. Read about `setPermission` command.

##Set Update Permission
Using the `setPermission` command you can lock-down the population data, so it cannot be modified via the `update` command. After entering the `update` command, you will be prompted to enter the name of a country and and the permission status (`0` or `1`).

##Display as a Chart
`populationChart` command serves to display a visual representation of the population data of a country in different years. You should specify the name of the country as an argument for this command. 
Example: `populationChart Bulgaria`


###Save Chart as a PDF
There are several options to store the chart displayed by `populationChart` command. You can select a format via the UI displayed after entering the `populationChart` command then right clicking on the chart and selecting the desired option.

##Get a Sorted List
you can use `sort` command to see a sorted list of the countries based on the male or female population or the MedianAge, RateofNaturalIncrease and PopulationGrowthRate factors in a specific year. After entering the `sort` command, you will be prompted to enter a year and Male or Female or as your criterion.
