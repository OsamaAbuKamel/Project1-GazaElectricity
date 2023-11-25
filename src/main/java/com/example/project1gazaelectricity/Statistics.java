package com.example.project1gazaelectricity;

public class Statistics {
    private RecordList records;

    public Statistics(RecordList records) {
        this.records = records;
    }

    public double getStatisticForDay(int day, ElectricityType electricityType, StatisticType type) {
        if (day < 0 || day > 30) {
            System.out.println("Invalid Day");
            return 0;
        }
        double count = 0;
        double total = 0;
        double max = 0;
        double min = Double.MAX_VALUE;
        double avg = 0;
        for (int year = 1; year < records.getRecords().length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
            for (int month = 1; month < yearList.length(); month++) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                if (day < monthList.length()) {
                    ElectricityRecord record = monthList.get(day);
                    double recordValue = getRecord(electricityType, record);
                    total += recordValue;
                    count++;
                    avg = total / count;
                    max = Math.max(max, recordValue);
                    min = Math.min(min, recordValue);
                }
            }
        }
        return calcStatistic(type, total, avg, max, min);
    }

    public double getMonth(int month, ElectricityType electricityType, StatisticType type) {
        double result = 0;
        for (SLinkedList<SLinkedList<ElectricityRecord>> monthList : records.getRecords()) {
        }
        return result;
    }

    public double getStatisticForMonth(int month, ElectricityType electricityType, StatisticType type) {
        double count = 0;
        double total = 0;
        double max = 0;
        double min = 0;
        double avg = 0;
        for (int year = 1; year < records.getRecords().length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
            if (month < yearList.length()) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                for (int i = 0; i < monthList.length(); i++) {
                    ElectricityRecord record = monthList.get(i);
                    double value = getRecord(electricityType, record);
                    total += value;
                    count++;
                    avg = total / count;
                    max = Math.max(max, value);
                    min = Math.max(min, value);
                }
            }
        }
        return calcStatistic(type, total, avg, max, min);
    }

    public double getYear(int year, ElectricityType electricityType, StatisticType type) {
        double result = 0;
        SLinkedList<SLinkedList<ElectricityRecord>> monthList = records.getRecords().get(year);
        for (SLinkedList<ElectricityRecord> sLinkedList : monthList) {
            result = calc(type, sLinkedList, electricityType);
        }
        return result;
    }

    private double calc(StatisticType type, SLinkedList<ElectricityRecord> records, ElectricityType type1) {
        double count = 0;
        double total = 0;
        double max = 0;
        double min = 0;
        for (ElectricityRecord record : records) {
            double value = getRecord(type1, record);
            total += value;
            count++;
            max = Math.max(max, value);
            min = Math.max(min, value);
        }
        switch (type) {
            case TOTAL:
                return total;
            case AVERAGE:
                return total / count;
            case MAX:
                return max;
            case MIN:
                return min;
            default:
                return 0;
        }
    }

    private double calcStatistic(StatisticType type, double total, double avg, double max, double min) {
        switch (type) {
            case TOTAL:
                return total;
            case AVERAGE:
                return avg;
            case MAX:
                return max;
            case MIN:
                return min;
            default:
                return 0;
        }
    }

    private double getRecord(ElectricityType type, ElectricityRecord record) {
        switch (type) {
            case ISRAELI_LINES:
                return record.getIsraeliLines();
            case EGYPTIAN_LINES:
                return record.getEgyptianLines();
            case GAZA_POWER:
                return record.getGazaPowerPlant();
            case POWER_CUTS:
                return record.getPowerCutsHoursDay();
            case TOTAL_SUPPLY:
                return record.getTotalSupply();
            case TEMP:
                return record.getTemp();
            default:
                return Double.NaN;
        }
    }
}
