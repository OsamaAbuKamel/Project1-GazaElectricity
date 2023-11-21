package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class RecordList {
    private SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> yearList;

    RecordList() {
        yearList = new SLinkedList<>();
    }

    public void add(ElectricityRecord record) {
        Calendar calendar = record.getDate();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        // Ensure the yearList is large enough to hold this year
        while (yearList.length() <= year) {
            yearList.insertAtLast(new SLinkedList<SLinkedList<ElectricityRecord>>());
        }
        // Get the list of months for this year
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        // Ensure the months list is large enough to hold this month
        while (months.length() <= month) {
            months.insertAtLast(new SLinkedList<ElectricityRecord>());
        }
        // Get the list of records for this month
        SLinkedList<ElectricityRecord> records = months.get(month);
        // Add the record to the list
        records.insertAtLast(record);
    }
    

    public void remove(ElectricityRecord record) {
        Calendar calendar = record.getDate();
        // Get the year and month from the calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        // Check if the months list has this month
        SLinkedList<ElectricityRecord> records = months.get(month);
        if (search(calendar) != null) {
            records.deleteSorted(record);

        }

    }

    public void update(ElectricityRecord newRecord) {
        ElectricityRecord record = search(newRecord.getDate());
        if (record != null) {
            record.setIsraeliLines(newRecord.getIsraeliLines());
            record.setGazaPowerPlant(newRecord.getGazaPowerPlant());
            record.setEgyptianLines(newRecord.getEgyptianLines());
            record.setTotalSupply(newRecord.getTotalSupply());
            record.setOverallDemand(newRecord.getOverallDemand());
            record.setPowerCutsHoursDay(newRecord.getPowerCutsHoursDay());
            record.setTemp(newRecord.getTemp());

        }

    }

    public ElectricityRecord search(Calendar calendar) {
        // Get the year and month from the calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // Check if the yearList has this year
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        if (months == null) {
            throw new IllegalArgumentException("No records for this year");
        }
        // Check if the months list has this month
        SLinkedList<ElectricityRecord> records = months.get(month);
        if (records == null) {
            throw new IllegalArgumentException("No records for this month");
        }
        // Iterate over each record in the month
        for (ElectricityRecord record : records) {
            // Get the date of the record
            // Get the date of the record
            Calendar recordCalendar = record.getDate();
            // Check if the day of the record matches the day we're searching for
            if (recordCalendar.get(Calendar.DAY_OF_MONTH) == day) {
                return record;
            }
        }
        // If no matching record was found, return null
        return null;
    }

    public void loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = scanner.nextLine();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String parts[] = line.split(",");
                String date = parts[0];
                ElectricityRecord record = new ElectricityRecord(stringToCalendar(date), Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
                add(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

  public void saveFile(String filename) throws IOException {

    PrintWriter writer = new PrintWriter(new FileWriter(filename));

    for(SLinkedList<SLinkedList<ElectricityRecord>> year : yearList) {
        
        for(SLinkedList<ElectricityRecord> month : year) {

            for(ElectricityRecord record : month) {

                Calendar calendar = record.getDate();
                
                writer.print(calendar.get(Calendar.YEAR));
                writer.print("-");
                
                writer.print(calendar.get(Calendar.MONTH) + 1); 
                writer.print("-");
                
                writer.print(calendar.get(Calendar.DAY_OF_MONTH));
                writer.print(",");
                
                writer.print(record.getIsraeliLines());
                writer.print(",");
                
                writer.print(record.getGazaPowerPlant()); 
                writer.print(",");
                
                writer.print(record.getEgyptianLines());
                writer.print(",");
                
                writer.print(record.getOverallDemand());
                writer.print(",");
                
                writer.print(record.getTotalSupply());
                writer.print(",");
                
                writer.print(record.getPowerCutsHoursDay()); 
                writer.print(",");
                
                writer.print(record.getTemp());
                
                writer.println();
            }
        }
    }

    writer.close();
}
    private Calendar stringToCalendar(String dateString) {
        String[] dateComponents = dateString.split("[-/]");
        int year = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1])-1; // Months are zero-indexed in Java
        int day = Integer.parseInt(dateComponents[2]);
        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar;
    }

    public void print() {
        // Iterate over each year in the yearList
        for (int year = 0; year < yearList.length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
            // Iterate over each month in the year
            for (int month = 0; month < months.length(); month++) {
                SLinkedList<ElectricityRecord> records = months.get(month);
                // Iterate over each record in the month
                for (ElectricityRecord record : records) {
                    System.out.println(record);
                }
            }
        }
    }

    public SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> getRecords() {
        return this.yearList;
    }

    public void setYearList(SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> yearList) {
        this.yearList = yearList;
    }

    public void printDataStructureWithData() {
        for (int year = 1; year < yearList.length(); year++) {
            System.out.println("Year " + year);
            SLinkedList<SLinkedList<ElectricityRecord>> yearLists = yearList.get(year);
            for (int month = 1; month < yearLists.length(); month++) {
                System.out.println("\tMonth " + month);
                SLinkedList<ElectricityRecord> monthList = yearLists.get(month);
                for (int day = 1; day < monthList.length(); day++) {
                    System.out.println("\t\tDay " + day);
                    ElectricityRecord dayRecord = monthList.get(day);
                    System.out.println("\t\t\tData: " + dayRecord.toString());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Create a new RecordList
        RecordList recordList = new RecordList();
        String file = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
        String file1 = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
       
        // Create a few ElectricityRecord objects
        // Calendar calendar1 = new GregorianCalendar(2023, 11, 1);
        // ElectricityRecord record1 = new ElectricityRecord(calendar1, 100.0, 200.0,
        // 300.0, 600.0, 500.0, 4.0, 25.0);
        // Calendar calendar2 = new GregorianCalendar(2022, 11, 1);
        // ElectricityRecord record2 = new ElectricityRecord(calendar2, 110.0, 210.0,
        // 310.0, 630.0, 520.0, 3.0, 26.0);
        // Calendar calendar3 = new GregorianCalendar(2023, 11, 2);
        // ElectricityRecord record3 = new ElectricityRecord(calendar3, 120.0, 220.0,
        // 320.0, 660.0, 540.0, 2.0, 27.0);
        // Calendar calendar4 = new GregorianCalendar(2023, 11, 3);
        // ElectricityRecord record4 = new ElectricityRecord(calendar4, 120.0, 220.0,
        // 320.0, 660.0, 540.0, 2.0, 27.0);

        // Add the records to the recordList
        // recordList.add(record1);
        // recordList.add(record2);
        // recordList.add(record3);
        // recordList.add(record4);
        // recordList.print();
        // recordList.printDataStructureWithData();
        // Statistics statistics = new Statistics(recordList);
        // // System.out.println(statistics.getTotalForYear(2024,
        // ElectricityType.ISRAELI_LINES, StatisticType.MIN));
        // System.out.println(statistics.getStatisticForDay(0,
        // ElectricityType.ISRAELI_LINES, StatisticType.TOTAL));

        // System.out.println("----------------------------------");
        // recordList.update(new ElectricityRecord(calendar3, 130.0, 230.0, 330.0,
        // 690.0, 570.0, 1.0, 28.0));
        // Print the records
        // recordList.print();
        // System.out.println("--------------------------------");
        // System.out.println(recordList.search(calendar2));
        // System.out.println("--------------------------------");

        // recordList.remove(record3);
        // recordList.print();
    }
}