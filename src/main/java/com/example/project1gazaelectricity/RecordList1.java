package com.example.project1gazaelectricity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class RecordList1 {
    SLinkedList<ElectricityRecord> list;

    public RecordList1() {
        list = new SLinkedList<>();
    }

    public SLinkedList<ElectricityRecord> getList() {
        return this.list;
    }

    public void setList(SLinkedList<ElectricityRecord> list) {
        this.list = list;
    }

    public void add(ElectricityRecord record) {
        list.insertAtHead(record);
    }

    public void remove(ElectricityRecord record) {
        list.deleteSorted(record);
    }

    // public void update(ElectricityRecord record) {
    // }
    // public boolean search(Date date){
    // }
    public void loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = scanner.nextLine();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String parts[] = line.split(",");
                String date = parts[0];
                String[] s = date.split("[/-]");
                System.out.println(s[2] + " " + s[1] + " " + s[0]);
                ElectricityRecord record = new ElectricityRecord(Double.parseDouble(parts[1]),
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
            writer.write(list.toString());
            list.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public SLinkedList<Integer> createDays(int month, int year) {
        SLinkedList<Integer> days = new SLinkedList<>();
        int numDays = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                // These months have 31 days
                numDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                // These months have 30 days
                numDays = 30;
                break;
            case 2:
                // February has 28 or 29 days depending on the year
                if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                    // Leap year
                    numDays = 29;
                } else {
                    // Non-leap year
                    numDays = 28;
                }
                break;
            default:
                // Invalid month
                System.out.println("Invalid month: " + month);
                return null;
        }
        // A loop to add the days to the linked list
        for (int i = 1; i <= numDays; i++) {
            days.insertSorted(i);
        }
        return days;
    }

    public SLinkedList<SLinkedList<Integer>> createMonth(int year) {
        SLinkedList<SLinkedList<Integer>> months = new SLinkedList<>();
        for (int i = 1; i < 13; i++) {
            SLinkedList<Integer> days = createDays(i, year);
            months.insertSorted(days);
        }
        return months;
    }

    public SLinkedList<SLinkedList<SLinkedList<Integer>>> createYears(int start,
            int end) {
        SLinkedList<SLinkedList<SLinkedList<Integer>>> years = new SLinkedList<>();
        for (int i = start; i <= end; i++) {
            SLinkedList<SLinkedList<Integer>> months = createMonth(i);
            years.insertSorted(months);
        }
        return years;
    }

    public static void main(String[] args) {
        RecordList1 list = new RecordList1();
        ElectricityRecord record = new ElectricityRecord(4, 75, 55, 58, 18, 70, 4);
        ElectricityRecord record1 = new ElectricityRecord(14, 52, 35, 8, 48, 74, 44);
        ElectricityRecord record2 = new ElectricityRecord(54, 54, 52, 1, 48, 57, 34);
        ElectricityRecord record3 = new ElectricityRecord(46, 55, 25, 85, 41, 87,
                24);
        list.add(record);
        list.add(record1);
        list.add(record2);
        list.add(record3);

        // SLinkedList<SLinkedList<SLinkedList<Integer>>> years =
        // list.createYears(2020,2020);
        // System.out.println(years.get(0));
        // // list.printYears(years);
        // String name =
        // "C:\\Users\\osama\\DataStructure\\Data-Structure-\\Project1\\src\\main\\resources\\com\\example\\project1\\Electricity.csv";
        // String fileName = "src\\main\\resources\\com\\example\\project1\\data.txt";
        // list.loadFile(name);
        // System.out.println(list);
    }
}