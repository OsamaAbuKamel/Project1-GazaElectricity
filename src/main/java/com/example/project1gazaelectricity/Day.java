package com.example.project1gazaelectricity;

public class Day implements Comparable<Day> {
    private int day;
    private ElectricityRecord record;

    public Day() {
    }

    public Day(int day, ElectricityRecord record) {
        this.day = day;
        this.record = record;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ElectricityRecord getRecord() {
        return this.record;
    }

    public void setRecord(ElectricityRecord record) {
        this.record = record;
    }

    @Override
    public int compareTo(Day o) {
        return Integer.compare(day, o.day);
    }

    @Override
    public String toString() {
        return  "" + getRecord();
    }
}