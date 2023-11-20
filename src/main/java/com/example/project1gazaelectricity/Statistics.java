package com.example.project1gazaelectricity;

public class Statistics {
    private RecordList records;

    public Statistics(RecordList records) {
        this.records = records;
    }

    public double getStatisticForDay(int day, ElectricityType electricityType, StatisticType type) {
        if (day < 1 || day > 31) {
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
                    total += getRecord(electricityType, record);
                    count++;
                    avg = total / count;
                    max = Math.max(max, getRecord(electricityType, record));
                    min = Math.min(min, getRecord(electricityType, record));
                }
            }
        }
        return calcStatistic(type, total, avg, max, min);
    }

    public double getStatisticForMonth(int month, ElectricityType electricityType, StatisticType type) {
        if (month < 0 || month > 11) {
            System.out.println(
                    "Invalid month index: " + month + ". Month index must be between 0 (January) and 11 (December).");
            return 0;
        }
        double count = 0;
        double total = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double avg = 0;
        for (int year = 1; year < records.getRecords().length(); year++) {
            SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
            if (month < yearList.length()) {
                SLinkedList<ElectricityRecord> monthList = yearList.get(month);
                for (int day = 1; day < monthList.length(); day++) {
                    ElectricityRecord record = monthList.get(day);
                    double value = getRecord(electricityType, record);
                    total += value;
                    count++;
                    avg = total / count;
                    max = Math.max(max,value);
                    min = Math.min(min, value);
                }
            }
        }
        return calcStatistic(type, total, avg, max, min);
    }

    public double getTotalForYear(int year, ElectricityType electricityType, StatisticType type) {
        if (year < 0 || year >= records.getRecords().length()) {
            System.out.println("No records found for year " + year);
            return 0;
        }
        double count = 0;
        double total = 0;
        double max = 0;
        double min = Double.MAX_VALUE;
        double avg = 0;
        SLinkedList<SLinkedList<ElectricityRecord>> yearList = records.getRecords().get(year);
        for (int month = 0; month < yearList.length(); month++) {
            SLinkedList<ElectricityRecord> monthList = yearList.get(month);
            for (int day = 0; day < monthList.length(); day++) {
                ElectricityRecord record = monthList.get(day);
                total += getRecord(electricityType, record);
                count++;
                avg = total / count;
                max = Math.max(max, getRecord(electricityType, record));
                min = Math.min(min, getRecord(electricityType, record));
            }
        }
        return calcStatistic(type, total, avg, max, min);
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
