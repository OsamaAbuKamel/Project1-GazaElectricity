package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class RecordList {
    private SLinkedList<Year> records;

    public RecordList() {
        records = new SLinkedList<>();
    }

    public SLinkedList<Year> getRecords() {
        return this.records;
    }

    public void setRecords(SLinkedList<Year> records) {
        this.records = records;
    }

    public void add(ElectricityRecord record) {
        LocalDate date = record.getDate();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        Year yearList = getYear(year);
        if (yearList == null) {
            yearList = new Year(year);
            records.insertSorted(yearList);
        }
        Month monthList = yearList.get(month);
        if (monthList == null) {
            monthList = new Month(month);
            yearList.addMonth(monthList);
        }
        Day dayList = monthList.get(day);
        if (dayList == null) {
            dayList = new Day(day, record);
            monthList.addDay(dayList);
        } else {
            throw new IllegalArgumentException("Day already exists");
        }
    }

    public Year getYear(int year) {
        for (Year yearList : records) {
            if (yearList.getYear() == year) {
                return yearList;
            }
        }
        return null;
    }

    public void remove(ElectricityRecord record) {
        LocalDate date = record.getDate();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        ElectricityRecord record2 = search(date);
        if (record2 != null) {
            Day dayList = new Day(day, record);
            Month monthList = new Month(month);
            monthList.removeDay(dayList);
            Year yearList = new Year(year);
            yearList.removeMonth(monthList);
            records.deleteSorted(yearList);
        } else
            throw new IllegalArgumentException("Record does not exist");
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
        } else {
            throw new IllegalArgumentException("Record does not exist");
        }
    }

    public ElectricityRecord search(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        Year years = getYear(year);
        if (years != null) {
            Month months = years.get(month);
            if (months != null) {
                for (Day record : months.getDays()) {
                    if (record.getDay() == day) {
                        return record.getRecord();
                    }
                }
            }
        }

        // Year yearObj = new Year(year);
        // Month monthObj = new Month(year);
        // Day dayObj = new Day(day, null);
        // for (Year years : records) {
        // if (years.compareTo(yearObj) == 0) {
        // for (Month months : years.getMonthList()) {
        // if (months.compareTo(monthObj) == 0) {
        // for (Day days : months.getDays()) {
        // if (days.compareTo(dayObj) == 0) {
        // return days.getRecord();
        // }
        // }
        // }
        // }
        // }
        // }
        return null;
    }

    public void loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = scanner.nextLine();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String parts[] = line.split(",");
                String date = parts[0].trim();
                ElectricityRecord record = new ElectricityRecord(LocalDate.parse(date),
                        Double.parseDouble(parts[1].trim()),
                        Double.parseDouble(parts[2].trim()), Double.parseDouble(parts[3].trim()),
                        Double.parseDouble(parts[4].trim()),
                        Double.parseDouble(parts[5].trim()), Double.parseDouble(parts[6].trim()),
                        Double.parseDouble(parts[7].trim()));
                add(record);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, true))) {
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
        return "{" +
                " records='" + getRecords() + "'" +
                "}";
    }
}
