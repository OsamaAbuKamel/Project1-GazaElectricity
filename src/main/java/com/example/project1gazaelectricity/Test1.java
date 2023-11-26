package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test1 {
    SLinkedList<Year> records;

    public Test1() {
        records = new SLinkedList<>();
    }

    public SLinkedList<Year> getRecords() {
        return this.records;
    }

    public void setRecords(SLinkedList<Year> records) {
        this.records = records;
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

    public void remove(ElectricityRecord record) {
        int year = record.getDate().get(Calendar.YEAR);
        int month = record.getDate().get(Calendar.MONTH);
        int day = record.getDate().get(Calendar.DAY_OF_MONTH);
        Day day2 = new Day(day, record);
        Month month2 = new Month(month);
        month2.removeDay(day2);
        Year year2 = new Year(year);
        year2.removeMonth(month2);
        records.deleteSorted(year2);
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

        int targetYear = calendar.get(Calendar.YEAR);
        int targetMonth = calendar.get(Calendar.MONTH);
        int targetDay = calendar.get(Calendar.DAY_OF_MONTH);

        Year target = new Year(targetYear);
        Month targetMonthObj = new Month(targetMonth);
        Day targetDayObj = new Day(targetDay, null);

        for (Year year : records) {
            if (year.compareTo(target) == 0) {
                for (Month month : year.getMonthList()) {
                    if (month.compareTo(targetMonthObj) == 0) {
                        for (Day day : month.getDays()) {
                            if (day.compareTo(targetDayObj) == 0) {
                                return day.getRecord();
                            }
                        }
                    }
                }
            }
        }

        return null; // record not found
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
            for (Year year : records) {
                for (Month month : year.getMonthList()) {
                    for (Day record : month.getDays()) {
                        writer.write(record.toString());
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

    public static void main(String[] args) throws IOException {
        Test1 recordList = new Test1();
        // Statistics statistics = new Statistics(recordList);
        String file = "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\Electricity.csv";
        String file1 = "src\\main\\resources\\com\\example\\project1gazaelectricity\\data.txt";
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
        Calendar calendar4 = new GregorianCalendar(2023, 10, 1);
        ElectricityRecord record4 = new ElectricityRecord(calendar4, 130.0, 220.0,
                320.0, 660.0, 540.0, 2.0, 27.0);

        // recordList.loadFile(file);
        recordList.add(record1);
        recordList.add(record2);
        recordList.add(record3);
        recordList.add(record4);
        recordList.print();
        System.out.println("------------------------------");
        recordList.update(record2);
        // recordList.print();
        // System.out.println(recordList.search(calendar2));
        // recordList.saveFile(file1);

    }

    private void print() {
        System.out.println("Printing the list of records");
        for (Year record : records) {
            // System.out.print(record + "/");
            for (Month month : record.getMonthList()) {
                // System.out.print(month + "/");
                for (Day day : month.getDays()) {
                    System.out.print(day);
                }
            }
        }
    }
}
