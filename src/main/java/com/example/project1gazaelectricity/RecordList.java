package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileOutputStream;
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
        int month = calendar.get(Calendar.MONTH);
        // Ensure the yearList is large enough to hold this year
        while (yearList.length() <= year) {
            yearList.insertSorted(new SLinkedList<SLinkedList<ElectricityRecord>>());
        }
        // Get the list of months for this year
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        // Ensure the months list is large enough to hold this month
        while (months.length() <= month) {
            months.insertSorted(new SLinkedList<ElectricityRecord>());
        }
        // Get the list of records for this month
        SLinkedList<ElectricityRecord> records = months.get(month);
        // Add the record to the list
        records.insertSorted(record);
    }

    public void remove(ElectricityRecord record) {
        Calendar calendar = record.getDate();
        // Get the year and month from the calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        // Get the list of month for this year
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        // Get the list of records for this month
        SLinkedList<ElectricityRecord> records = months.get(month);
        // Check if the records list has this record
        if (search(calendar) != null) {
            // remove the record
            records.deleteSorted(record);
            // If the records list is empty, remove the month
            if (records.isEmpty()) {
                months.clear();
            }
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
        // Get year,month and day from calender
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Get the list of months for the given year
        SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        // If there are no records for this year, throw an exception
        if (months == null) {
            throw new IllegalArgumentException("No records for this year");
        }
        // Get the list of records for the given month
        SLinkedList<ElectricityRecord> records = months.get(month);
        // If there are no records for this month, throw an exception
        if (records == null) {
            throw new IllegalArgumentException("No records for this month");
        }
        // Iterate over each record in the month
        for (ElectricityRecord record : records) {
            // Get day from record
            Calendar recordCalendar = record.getDate();
            // check if the day is the same as the given day
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

    public void saveFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, true))) {
            for (SLinkedList<SLinkedList<ElectricityRecord>> year : yearList) {
                for (SLinkedList<ElectricityRecord> month : year) {
                    for (ElectricityRecord record : month) {
                        writer.write(fileName);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private Calendar stringToCalendar(String dateString) {
        String[] dateComponents = dateString.split("[-/]");
        int year = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]) - 1; // Months are zero-indexed in Java
        int day = Integer.parseInt(dateComponents[2]);
        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar;
    }

    public void print() {
        for (SLinkedList<SLinkedList<ElectricityRecord>> year : yearList) {
          for (SLinkedList<ElectricityRecord> month : year) {  
            for (ElectricityRecord record : month) {
              if (record!=null) { 
                System.out.print(record);
              }
            }
          }
        }
      
        // // Iterate over each year in the yearList
        // for (int year = 0; year < yearList.length(); year++) {
        //     SLinkedList<SLinkedList<ElectricityRecord>> months = yearList.get(year);
        //     // Iterate over each month in the year
        //     for (int month = 0; month < months.length(); month++) {
        //         SLinkedList<ElectricityRecord> records = months.get(month);
        //         // Iterate over each record in the month
        //         for (ElectricityRecord record : records) {
        //             System.out.print(record);
        //         }
        //     }
        // }
    }

    public SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> getRecords() {
        return this.yearList;
    }

    public void setYearList(SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> yearList) {
        this.yearList = yearList;
    }

    public void printDataStructureWithData() {
        for (int year = 0; year < yearList.length(); year++) {
            System.out.println("Year " + year);
            SLinkedList<SLinkedList<ElectricityRecord>> yearLists = yearList.get(year);
            for (int month = 0; month < yearLists.length(); month++) {
                System.out.println("\tMonth " + month);
                SLinkedList<ElectricityRecord> monthList = yearLists.get(month);
                for (int day = 0; day < monthList.length(); day++) {
                    System.out.println("\t\tDay " + day);
                    ElectricityRecord dayRecord = monthList.get(day);
                    System.out.println("\t\t\tData: " + dayRecord.toString());
                }
            }
        }
    }
}