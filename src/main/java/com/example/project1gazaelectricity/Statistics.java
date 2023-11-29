package com.example.project1gazaelectricity;

public class Statistics {
    private RecordList records;

    public Statistics(RecordList records) {
        this.records = records;
    }

    public double getStatisticForDay(int day, ElectricityType electricityType, StatisticType type) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        } else {
            double total = 0;
            double count = 0;
            double max = 0;
            double min = Double.MAX_VALUE;
                        SLinkedList<Year> yearList = records.getRecords();

            for (Year year : yearList) {
                for (Month month : year.getMonthList()) {
                    for (Day days : month.getDays()) {
                        if (days.getRecord().getDate().getDayOfMonth() == day) {
                            double recordValue = getRecord(electricityType, days.getRecord());
                            total += recordValue;
                            count++;
                            max = Math.max(max, recordValue);
                            min = Math.min(min, recordValue);
                        }
                    }
                }
            }
            return calcStatistic(type, total, count, max, min);
        }
    }

    public double getStatisticForMonth(int month, ElectricityType electricityType, StatisticType type) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        } else {
            double total = 0;
            double count = 0;
            double max = 0;
            double min = Double.MAX_VALUE;
            SLinkedList<Year> yearList = records.getRecords();
            for (Year year : yearList) {
                for (Month months : year.getMonthList()) {
                    if (months.getMonth() == month) {
                        for (Day days : months.getDays()) {
                            double recordValue = getRecord(electricityType, days.getRecord());
                            total += recordValue;
                            count++;
                            max = Math.max(max, recordValue);
                            min = Math.min(min, recordValue);
                        }
                    }
                }
            }
            return calcStatistic(type, total, count, max, min);
        }
    }

    public double getStatisticForYear(int year, ElectricityType electricityType, StatisticType type) {
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        } else {
            double total = 0;
            double count = 0;
            double max = 0;
            double min = Double.MAX_VALUE;
            Year yearList = records.getYear(year);
            SLinkedList<Month> monthList = yearList.getMonthList();
            for (Month months : monthList) {
                for (Day dayList : months.getDays()) {
                    double recordValue = getRecord(electricityType, dayList.getRecord());
                    total += recordValue;
                    count++;
                    max = Math.max(max, recordValue);
                    min = Math.min(min, recordValue);
                }
            }
            return calcStatistic(type, total, count, max, min);
        }
    }

    private double calcStatistic(StatisticType type, double total, double count, double max, double min) {
        switch (type) {
            case TOTAL:
                return total;
            case AVERAGE:
                double avg = count == 0 ? 0 : total / count;
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
