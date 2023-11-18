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
        int[] partsOfDate = parseDate(date);
        validateDate(partsOfDate[0], partsOfDate[1], partsOfDate[2]);
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = getOrCreateYearList(partsOfDate[0]);
        SLinkedList<ElectricityRecord> monthList = getOrCreateMonthList(yearList, partsOfDate[1]);
        ElectricityRecord dayRecord = getOrCreateDayRecord(monthList, partsOfDate[2]);
        updateRecord(dayRecord, record);
    }

    public void remove(String date, ElectricityRecord record) {
        int[] partsOfDate = parseDate(date);
        validateDate(partsOfDate[0], partsOfDate[1], partsOfDate[2]);
        SLinkedList<ElectricityRecord> monthList = records.get(partsOfDate[0]).get(partsOfDate[1]);
        if (record == null) {
            throw new IllegalArgumentException("Record cannot be null");
        } else if (search(date) != null) {
            monthList.deleteSorted(record);
        }
    }

    public void update(String date, ElectricityRecord newRecord) {
        ElectricityRecord record = search(date);
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

    public ElectricityRecord search(String date) {
        int[] partsOfDate = parseDate(date);
        if (records.length() > partsOfDate[0]) {
            SLinkedList<ElectricityRecord> monthList = records.get(partsOfDate[0]).get(partsOfDate[1]);
            if (monthList != null && monthList.length() > partsOfDate[2]) {
                return monthList.get(partsOfDate[2]);
            }
        }
        return null;
    }

    private int[] parseDate(String date) {
        String[] dateParts = date.split("[-/]");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        validateDate(year, month, day);
        return new int[] { year, month, day };
    }

    private SLinkedList<SLinkedList<ElectricityRecord>> getOrCreateYearList(int year) {
        while (records.length() <= year) {
            records.insertAtLast(new SLinkedList<>());
        }
        return records.get(year);
    }

    private SLinkedList<ElectricityRecord> getOrCreateMonthList(SLinkedList<SLinkedList<ElectricityRecord>> yearList,
            int month) {
        while (yearList.length() <= month) {
            yearList.insertAtLast(new SLinkedList<>());
        }
        return yearList.get(month);
    }

    private ElectricityRecord getOrCreateDayRecord(SLinkedList<ElectricityRecord> monthList, int day) {
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

    public void printDataStructureWithData() {
        for (int year = 1; year < records.length(); year++) {
            System.out.println("Year " + year);
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(year);
            for (int month = 1; month < yearList.length(); month++) {
                System.out.println("\tMonth " + month);
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                for (int day = 1; day < monthList.length(); day++) {
                    System.out.println("\t\tDay " + day);
                    ElectricityRecord dayRecord = monthList.get(day);
                    System.out.println("\t\t\tData: " + dayRecord.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        RecordList test = new RecordList();
        // Add some sample electricity records for testing
        ElectricityRecord record = new ElectricityRecord(10.0, 5.0, 8.0, 20.0, 15.0, 2.5, 25.0);
        ElectricityRecord record2 = new ElectricityRecord(12.0, 6.0, 9.0, 22.0, 18.0, 3.0, 26.0);
        ElectricityRecord record3 = new ElectricityRecord(74.0, 06.0, 10.0, 2.0, 1.0, 3.0, 5.0);
        test.add("2023-10-01", record);
        test.add("2023-10-02", record2);
        test.add("2023-10-03", record3);
        test.print();
        // test.printDataStructureWithData();
        System.out.println("---------------------------");
        test.remove("2023-10-03", record3);
        test.print();
        // test.printDataStructureWithData();
        // System.out.println(test.search("2023-10-02"));
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