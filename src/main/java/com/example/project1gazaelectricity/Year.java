package com.example.project1gazaelectricity;

public class Year implements Comparable<Year> {
    int year;
   SLinkedList<Month> monthList;

    public Year(int year) {
        this.year = year;
        monthList = new SLinkedList<>();
    }

    public void addMonth(Month month) {
        this.monthList.insertSorted(month);
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
        return year + "";
    }


    @Override
    public int compareTo(Year o) {
      return Integer.compare(year, o.year);
    }
}