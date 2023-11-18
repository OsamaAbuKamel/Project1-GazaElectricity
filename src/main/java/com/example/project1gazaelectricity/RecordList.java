package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class RecordList {
    private SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> records;

    public RecordList() {
        records = new SLinkedList<>();
    }

    public void add(String date, ElectricityRecord record) {
        String[] dateParts = date.split("[-/]");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        validateDate(year, month, day);
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = ensureYearExists(year);
        SLinkedList<ElectricityRecord> monthList = ensureMonthExists(yearList, month);
        ElectricityRecord dayRecord = ensureDayExists(monthList, day);
        updateRecord(dayRecord, record);
    }
    public int[] splitDate(String date) {
        String[] parts = date.split("[-/]");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
    
        return new int[]{year, month, day};
      }
    private SLinkedList<SLinkedList<ElectricityRecord>> ensureYearExists(int year) {
        while (records.length() <= year) {
            records.insertAtLast(new SLinkedList<>());
        }
        return records.get(year);
    }

    private SLinkedList<ElectricityRecord> ensureMonthExists(SLinkedList<SLinkedList<ElectricityRecord>> yearList,
            int month) {
        while (yearList.length() <= month) {
            yearList.insertAtLast(new SLinkedList<>());
        }
        return yearList.get(month);
    }

    private ElectricityRecord ensureDayExists(SLinkedList<ElectricityRecord> monthList, int day) {
        while (monthList.length() <= day) {
            monthList.insertAtLast(new ElectricityRecord());
        }
        return monthList.get(day);
    }

    private void updateRecord(ElectricityRecord dayRecord, ElectricityRecord record) {
        dayRecord.setIsraeliLines(record.getIsraeliLines());
        dayRecord.setGazaPowerPlant(record.getGazaPowerPlant());
        dayRecord.setEgyptianLines(record.getEgyptianLines());
        dayRecord.setTotalSupply(record.getTotalSupply());
        dayRecord.setOverallDemand(record.getOverallDemand());
        dayRecord.setPowerCutsHoursDay(record.getPowerCutsHoursDay());
        dayRecord.setTemp(record.getTemp());
    }

    // Check if the provided date is valid
    private void validateDate(int year, int month, int day) {
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        int maxDays = getMaxDaysForMonth(month, year);
        if (day < 1 || day > maxDays) {
            throw new IllegalArgumentException("Invalid day for this month");
        }
    }

    private static int getMaxDaysForMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void remove(int year, int month, int day, ElectricityRecord record) {
        if (record == null) {
            throw new NullPointerException("Record cannot be null");
        }
        validateDate(year, month, day);
        SLinkedList<ElectricityRecord> monthList = records.get(year).get(month);
        monthList.deleteSorted(record);
    }

    public void print() {
        for (int year = 1; year < records.length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(year);
            for (int month = 1; month < yearList.length(); month++) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                for (int day = 1; day < monthList.length(); day++) {
                    ElectricityRecord dayRecord = monthList.get(day);
                    System.out.println("Date: " + (year) + "/" + (month) + "/" + (day));
                    System.out.println("Israeli Lines: " + dayRecord.getIsraeliLines());
                    System.out.println("Gaza Power Plant: " + dayRecord.getGazaPowerPlant());
                    System.out.println("Egyptian Lines: " + dayRecord.getEgyptianLines());
                    System.out.println("Total Supply: " + dayRecord.getTotalSupply());
                    System.out.println("Overall Demand: " + dayRecord.getOverallDemand());
                    System.out.println("Power Cuts Hours: " + dayRecord.getPowerCutsHoursDay());
                    System.out.println("Temperature: " + dayRecord.getTemp());
                    System.out.println();
                }
            }
        }
    }

    public ElectricityRecord getElectricityRecord(int year, int month, int day) {
        if (records.length() > year) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(year);
            if (yearList.length() > month) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                if (monthList.length() > day) {
                    return monthList.get(day);
                }
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
                String date = parts[0];
                // String[] s = date.split("[/-]");
                ElectricityRecord record = new ElectricityRecord(Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
                add(date, record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, true))) {
            for (int year = 1; year < records.length(); year++) {
                SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(year);
                for (int month = 1; month < yearList.length(); month++) {
                    SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                    for (int day = 1; day < monthList.length(); day++) {
                        ElectricityRecord dayRecord = monthList.get(day);
                        writer.write(year + "/" + day + "/" + month + "," + dayRecord.toString());
                        records.clear();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> getRecordList() {
        return this.records;
    }

    public void setRecordList(SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> recordList) {
        this.records = recordList;
    }

    @Override
    public String toString() {
        return "{" +
                " recordList='" + getRecordList() + "'" +
                "}";
    }

    public static void main(String[] args) {
        RecordList test = new RecordList();
        // Add some sample electricity records for testing
        ElectricityRecord record = new ElectricityRecord(10.0, 5.0, 8.0, 20.0, 15.0, 2.5, 25.0);
        ElectricityRecord record2 = new ElectricityRecord(12.0, 6.0, 9.0, 22.0, 18.0, 3.0, 26.0);
        ElectricityRecord record3 = new ElectricityRecord(12.0, 6.0, 9.0, 22.0, 18.0, 3.0, 26.0);
        test.add("2023-10-01", record);
        test.add("2023-10-02", record2);
        test.add("2023-11-30", record3);
        test.print();
        // test.remove(2023, 10, 3, record3);
        // test.print();
        // String file =
        // "src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
        // String file1 =
        // "src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
        // test.loadFile(file1);
        // test.print();
    }
}