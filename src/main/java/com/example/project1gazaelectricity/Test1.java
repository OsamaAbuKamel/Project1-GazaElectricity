package com.example.project1gazaelectricity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test1 {
    SLinkedList<Year> records;

    public Test1() {
        records = new SLinkedList<>();
    }

    public void add(ElectricityRecord record) {
        int year = record.getDate().get(Calendar.YEAR);
        int month = record.getDate().get(Calendar.MONTH);
        int day = record.getDate().get(Calendar.DAY_OF_MONTH);
        Day day2 = new Day(day, record);
        Month month2 = new Month(month);
        month2.addDay(day2);
        Year year2 = new Year(year);
        year2.addMonth(month2);
        records.insertSorted(year2);
    }
    
    public static void main(String[] args) {
        Test1 recordList = new Test1();
        // Statistics statistics = new Statistics(recordList);
        String file = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
        String file1 = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
        // recordList.loadFile(file);
        // Create a few ElectricityRecord objects
        Calendar calendar1 = new GregorianCalendar(2023, 10, 3);
        ElectricityRecord record1 = new ElectricityRecord(calendar1, 100.0, 200.0,
                300.0, 600.0, 500.0, 4.0, 25.0);
        Calendar calendar2 = new GregorianCalendar(2022, 10, 1);
        ElectricityRecord record2 = new ElectricityRecord(calendar2, 110.0, 210.0,
                310.0, 630.0, 520.0, 3.0, 26.0);
        Calendar calendar3 = new GregorianCalendar(2023, 10, 2);
        ElectricityRecord record3 = new ElectricityRecord(calendar3, 120.0, 220.0,
                320.0, 660.0, 540.0, 2.0, 27.0);
        Calendar calendar4 = new GregorianCalendar(2023, 10, 4);
        ElectricityRecord record4 = new ElectricityRecord(calendar4, 130.0, 220.0,
                320.0, 660.0, 540.0, 2.0, 27.0);
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        recordList.add(record4);
        recordList.print();
    }

    private void print() {
        System.out.println("Printing the list of records");
        for (Year record : records) {
            System.out.print(record+"/");
           for (Month month : record.getMonthList()) {
            System.out.print(month+"/");
            for (Day day : month.getDays()) {
                System.out.print(day);
                
            }
           }
        }
    }
}
