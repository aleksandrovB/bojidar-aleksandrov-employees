package com.company;

public class Main {

    public static void main(String[] args) {

        LongestPeriodFinder longestPeriod = new LongestPeriodFinder();
        CSVReader csvReader = new CSVReader();
        System.out.println("Days :" +longestPeriod.findPairOfEmployeesWithMostHoursSpentTogether(csvReader.readCSV("./ParticipationSheet.csv")));


    }
}
