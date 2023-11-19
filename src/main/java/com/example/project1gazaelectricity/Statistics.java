package com.example.project1gazaelectricity;

public class Statistics {
    private RecordList records;

    public Statistics(RecordList records) {
        this.records = records;
    }

    public double getStatisticForDay(int day) {
        if (day < 1 || day > 31) {
            System.out.println("Invalid Day");
            return 0;
        }
        double total = 0;
        for (int year = 1; year < records.getRecords().length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
            for (int month = 1; month < yearList.length(); month++) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                if (day < monthList.length()) {
                    ElectricityRecord record = monthList.get(day);
                    total += record.getIsraeliLines();
                }
            }
        }
        return total;
    }

    public double getStatisticForMonth(int month) {
        if (month < 0 || month > 11) {
            System.out.println(
                    "Invalid month index: " + month + ". Month index must be between 0 (January) and 11 (December).");
            return 0;
        }
        double total = 0.0;
        for (int year = 1; year < records.getRecords().length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
            if (month < yearList.length()) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                for (int day = 1; day < monthList.length(); day++) {
                    ElectricityRecord record = monthList.get(day);
                    total += record.getIsraeliLines();
                }
            }
        }
        return total;
    }

    public double getTotalForYear(int year) {
        if (year < 0 || year >= records.getRecords().length()) {
            System.out.println("No records found for year " + year);
            return 0;
        }
        double total = 0.0;
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
        for (int month = 0; month < yearList.length(); month++) {
            SLinkedList<ElectricityRecord> monthList = yearList.get(month);
            for (int day = 0; day < monthList.length(); day++) {
                ElectricityRecord record = monthList.get(day);
                total += record.getIsraeliLines();
            }
        }
        return total;
    }
}
