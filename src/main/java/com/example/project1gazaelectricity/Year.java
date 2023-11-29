package com.example.project1gazaelectricity;

public class Year implements Comparable<Year> {
    private int year;
    private SLinkedList<Month> monthList;

    public Year() {
        monthList = new SLinkedList<>();
    }

    public Year(int year) {
        this.year = year;
        monthList = new SLinkedList<>();
    }

    public void addMonth(Month month) {
        this.monthList.insertSorted(month);
    }

    public void removeMonth(Month month) {
        this.monthList.deleteSorted(month);
    }

    public Month search(Month month) {
        return monthList.search(month);
    }

    public Month get(int index) {
        for (Month month : monthList) {
            if (month.getMonth() == index) {
                return month;
            }
        }
        return null;
    }

    public SLinkedList<Month> getMonthList() {
        return monthList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return year + " " + monthList.toString();
    }

    @Override
    public int compareTo(Year o) {
        return Integer.compare(year, o.year);
    }
}