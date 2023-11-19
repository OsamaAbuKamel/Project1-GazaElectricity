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

    public void add(ElectricityRecord record) {
        if (record == null)
            throw new IllegalArgumentException("Record cannot be null");
        int[] partsOfDate = parseDate(record.getDate());
        validateDate(partsOfDate[0], partsOfDate[1], partsOfDate[2]);
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = getOrCreateYearList(partsOfDate[0]);
        SLinkedList<ElectricityRecord> monthList = getOrCreateMonthList(yearList, partsOfDate[1]);
        ElectricityRecord dayRecord = getOrCreateDayRecord(monthList, partsOfDate[2]);
        setRecord(dayRecord, record);
    }

    public void remove(ElectricityRecord record) {
        if (record == null)
            throw new IllegalArgumentException("Record cannot be null");
        int[] partsOfDate = parseDate(record.getDate());
        validateDate(partsOfDate[0], partsOfDate[1], partsOfDate[2]);
        SLinkedList<ElectricityRecord> monthList = records.get(partsOfDate[0]).get(partsOfDate[1]);
        if (search(record.getDate()) != null)
            monthList.deleteSorted(record);
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

    private void setRecord(ElectricityRecord dayRecord, ElectricityRecord record) {
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

    public ElectricityRecord getElectricityRecord(String date) {
        int[] dateParts = parseDate(date);
        if (records.length() > dateParts[0]) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(dateParts[0]);
            if (yearList.length() > dateParts[1]) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(dateParts[1]);
                if (monthList.length() > dateParts[2]) {
                    return monthList.get(dateParts[2]);
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
                ElectricityRecord record = new ElectricityRecord(date, Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]), Double.parseDouble(parts[6]), Double.parseDouble(parts[7]));
                add(record);
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

    public SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> getRecords() {
        return this.records;
    }

    public void setRecords(SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "{" +
                " recordList='" + getRecords() + "'" +
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

    public double getStatisticForDay(String date, String statisticType) {
        ElectricityRecord record = search(date);
        if (record != null) {
            switch (statisticType.toLowerCase()) {
                case "israeli lines":
                    return record.getIsraeliLines();
                case "gaza power plant":
                    return record.getGazaPowerPlant();
                case "egyptian lines":
                    return record.getEgyptianLines();
                case "total supply":
                    return record.getTotalSupply();
                case "overall demand":
                    return record.getOverallDemand();
                case "power cuts hours day":
                    return record.getPowerCutsHoursDay();
                case "temp":
                    return record.getTemp();
                default:
                    throw new IllegalArgumentException("Invalid statistic type");
            }
        }
        return Double.NaN; // Return NaN for non-existent data
    }

    public double getStatisticForMonth(int year, int month, String statisticType) {
        double total = 0;
        int count = 0;
        // Iterate through records for the given month and accumulate values
        // Adjust the following logic based on your actual record structure
        SLinkedList<ElectricityRecord> monthList = records.get(year).get(month);
        if (monthList != null) {
            for (int day = 0; day < monthList.length(); day++) {
                total += getValueFromRecord(monthList.get(day), statisticType);
                count++;
            }
        }
        return calculateStatistic(total, count, statisticType);
    }

    private double getValueFromRecord(ElectricityRecord record, String statisticType) {
        // Adjust this logic based on your actual record structure and the selected
        // statisticType
        switch (statisticType) {
            case "TOTAL":
                return record.getTotalSupply();
            case "AVERAGE":
                return record.getOverallDemand();
            // Add more cases for other statistic types
            default:
                throw new IllegalArgumentException("Unsupported statistic type: " + statisticType);
        }
    }

    private double calculateStatistic(double total, int count, String statisticType) {
        // Adjust this logic based on the selected statistic type
        switch (statisticType) {
            case "TOTAL":
                return total;
            case "AVERAGE":
                return count == 0 ? 0 : total / count;
            // Add more cases for other statistic types
            default:
                throw new IllegalArgumentException("Unsupported statistic type: " + statisticType);
        }
    }

    public double getTotalForYear(int year) {
        if(year < 0 || year >= records.length()){
            System.out.println("No records found for year " + year);
            return 0;
        }
        
        double total = 0.0;
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.get(year);
        for(int month = 0; month < yearList.length(); month++){
            SLinkedList<ElectricityRecord> monthList = yearList.get(month);
            for(int day = 0; day < monthList.length(); day++){
                ElectricityRecord record = monthList.get(day);
                total += record.getIsraeliLines();
            }
        }
        return total;
    }
}