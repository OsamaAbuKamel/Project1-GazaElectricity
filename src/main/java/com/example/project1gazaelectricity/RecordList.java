package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class RecordList {
    // list of years
    private SLinkedList<Year> records;

    // constructor
    public RecordList() {
        records = new SLinkedList<>();
    }

    // setter and getter
    public SLinkedList<Year> getRecords() {
        return this.records;
    }

    public void setRecords(SLinkedList<Year> records) {
        this.records = records;
    }

    public void add(ElectricityRecord record) {
        LocalDate date = record.getDate();
        // get year, month and day
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        // check if year exists
        Year yearList = getYear(year);
        if (yearList == null) {
            // create a new object of year
            yearList = new Year(year);
            // add the year in the list
            records.insertSorted(yearList);
        }
        // check if month exists
        Month monthList = yearList.get(month);
        if (monthList == null) {
            // create a new object of month
            monthList = new Month(month);
            // add the month th the yearList
            yearList.addMonth(monthList);
        }
        // check if days exists
        Day dayList = monthList.get(day);
        if (dayList == null) {
            // create a new object of day
            dayList = new Day(day, record);
            // add the day in the monthList
            monthList.addDay(dayList);
        } else {
            // if day already added
            throw new IllegalArgumentException("Day already exists");
        }
    }

    public void remove(ElectricityRecord record) {
        LocalDate date = record.getDate();
        // get year, month and day
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        // search record in list
        ElectricityRecord record2 = search(date);
        // check if record exist
        if (record2 != null) {
            // create a new objects of year, month and day
            Day dayList = new Day(day, record);
            Month monthList = new Month(month);
            Year yearList = new Year(year);
            // remove day fro month
            monthList.removeDay(dayList);
            // remove month from year
            yearList.removeMonth(monthList);
            // remove year from list
            records.deleteSorted(yearList);
        } else
            // if record not found
            throw new IllegalArgumentException("Record does not exist");
    }

    public void update(ElectricityRecord newRecord) {
        // search record in list
        ElectricityRecord record = search(newRecord.getDate());
        // check if record exist
        if (record != null) {
            // update record
            record.setIsraeliLines(newRecord.getIsraeliLines());
            record.setGazaPowerPlant(newRecord.getGazaPowerPlant());
            record.setEgyptianLines(newRecord.getEgyptianLines());
            record.setTotalSupply(newRecord.getTotalSupply());
            record.setOverallDemand(newRecord.getOverallDemand());
            record.setPowerCutsHoursDay(newRecord.getPowerCutsHoursDay());
            record.setTemp(newRecord.getTemp());
        } else {
            // if record not found
            throw new IllegalArgumentException("Record does not exist");
        }
    }

    public ElectricityRecord search(LocalDate date) {
        // get year, month and day
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        // get year from list
        Year years = getYear(year);
        // check if years exist
        if (years != null) {
            // get month from year
            Month months = years.get(month);
            // check if month exist
            if (months != null) {
                // get day from month
                for (Day record : months.getDays()) {
                    if (record.getDay() == day) {
                        // return record
                        return record.getRecord();
                    }
                }
            }
        }
        // if record not found
        return null;
    }

    public Year getYear(int year) {
        // search year in list
        for (Year yearList : records) {
            if (yearList.getYear() == year) {
                return yearList;
            }
        }
        return null;
    }

    public void loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = scanner.nextLine();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String parts[] = line.split(",");
                String date = parts[0].trim();
                ElectricityRecord record = new ElectricityRecord(
                        LocalDate.parse(date),
                        Double.parseDouble(parts[1].trim()),
                        Double.parseDouble(parts[2].trim()),
                        Double.parseDouble(parts[3].trim()),
                        Double.parseDouble(parts[4].trim()),
                        Double.parseDouble(parts[5].trim()),
                        Double.parseDouble(parts[6].trim()),
                        Double.parseDouble(parts[7].trim()));
                add(record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String fileName) {
        try (
                PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, true))) {
            for (Year year : records) {
                for (Month month : year.getMonthList()) {
                    for (Day record : month.getDays()) {
                        writer.write(record.toString());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        System.out.println("Printing the list of records");
        for (Year record : records) {
            for (Month month : record.getMonthList()) {
                for (Day day : month.getDays()) {
                    System.out.print(day);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "{" + " records='" + getRecords() + "'" + "}";
    }
}
