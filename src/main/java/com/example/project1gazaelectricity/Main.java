package com.example.project1gazaelectricity;

import java.time.LocalDate;

public class Main {
    // A main method to test the code
    public static void main(String[] args) {
        RecordList recordList = new RecordList();
        Statistics statistics = new Statistics(recordList);
        String file = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
        String file1 = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
        // recordList.loadFile(file);
        // Create a few ElectricityRecord objects
        LocalDate date = LocalDate.of(2022, 5, 1);
        LocalDate date1 = LocalDate.of(2023, 9, 1);
        LocalDate date2 = LocalDate.of(2023, 6, 1);
        LocalDate date3 = LocalDate.of(2022, 6, 2);
        LocalDate date4 = LocalDate.now();
        ElectricityRecord record1 = new ElectricityRecord(date, 100.0, 200.0,
                300.0, 600.0, 500.0, 4.0, 25.0);
        ElectricityRecord record2 = new ElectricityRecord(date1, 110.0, 210.0,
                310.0, 630.0, 520.0, 3.0, 26.0);
        ElectricityRecord record3 = new ElectricityRecord(date2, 120.0, 220.0,
                320.0, 660.0, 540.0, 2.0, 27.0);
        ElectricityRecord record4 = new ElectricityRecord(date4, 130.0, 220.0,
                320.0, 660.0, 540.0, 2.0, 27.0);
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        recordList.add(record4);
        System.out.println(recordList);
                System.out.println("------------------------------");

        recordList.print();
        System.out.println("------------------------------");
        System.out.println(recordList.search(date1));
        // System.out.println(statistics.getStatisticForDay(1,
        // ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
        // System.out.println(statistics.getStatisticForMonth(6,
        // ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
        // System.out.println("YEAR: "+statistics.getStatisticForYear(2023,
        // ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
    }
}
