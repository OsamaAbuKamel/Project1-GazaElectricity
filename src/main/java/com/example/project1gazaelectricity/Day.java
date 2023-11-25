package com.example.project1gazaelectricity;

public class Day implements Comparable<Day> {
    int day;
    ElectricityRecord record;

    public Day(int day, ElectricityRecord record) {
        this.day = day;
        this.record = record;
    }

    public int getDay() {
        return day;
    }

    public ElectricityRecord getRecord() {
        return record;
    }

    @Override
    public int compareTo(Day o) {
        return Integer.compare(day, o.day);

    }

    @Override
    public String toString() {
        return ""+getDay() + getRecord();
    }

}