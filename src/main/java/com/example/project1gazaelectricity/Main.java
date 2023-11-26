package com.example.project1gazaelectricity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
  // A main method to test the code
  public static void main(String[] args) {
    RecordList recordList = new RecordList();
    Statistics statistics = new Statistics(recordList);
    String file = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
    String file1 = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
    // recordList.loadFile(file);
    // Create a few ElectricityRecord objects
    Calendar calendar1 = new GregorianCalendar(2023, 5, 3);
    ElectricityRecord record1 = new ElectricityRecord(calendar1, 100.0, 200.0,
        300.0, 600.0, 500.0, 4.0, 25.0);
    Calendar calendar2 = new GregorianCalendar(2023, 9,1);
    ElectricityRecord record2 = new ElectricityRecord(calendar2, 110.0, 210.0,
        310.0, 630.0, 520.0, 3.0, 26.0);
    Calendar calendar3 = new GregorianCalendar(2023, 5, 2);
    ElectricityRecord record3 = new ElectricityRecord(calendar3, 120.0, 220.0,
        320.0, 660.0, 540.0, 2.0, 27.0);
    Calendar calendar4 = new GregorianCalendar(2022, 5, 4);
    ElectricityRecord record4 = new ElectricityRecord(calendar4, 130.0, 220.0,
        320.0, 660.0, 540.0, 2.0, 27.0);
    recordList.add(record1);
    recordList.add(record2);
    recordList.add(record3);
    recordList.add(record4);
    recordList.print();
    // SLinkedList<ElectricityRecord> ss = recordList.getRecords().get(2023).get(10);
    // SLinkedList<SLinkedList<ElectricityRecord>> s = recordList.getRecords().get(2023);
    // // System.out.println(ss);
    // System.out.println(recordList.getRecords().get(2023).get(10));
    // // System.out
    //     // .println("Day 1: " + statistics.getStatisticForDay(1, ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
    // System.out.println("Month 11: " + statistics.getStatisticForDay(5, ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
    // System.out.println("Year 2023: " + statistics.getYear(2023, ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));
  }
}