package com.example.project1gazaelectricity;

public class Month implements Comparable<Month>, Cloneable {
    private int month;
    private SLinkedList<Day> days;

    public Month(int month) {
        this.month = month;
        days = new SLinkedList<Day>();
    }

    public void addDay(Day day) {
        days.insertSorted(day);
    }

    public void removeDay(Day day) {
        days.deleteSorted(day);
    }

    public Day search(Day day) {
        return days.search(day);
    }

    public Day get(int index) {
        for (Day day : days) {
            if (day.getDay() == index) {
                return day;
            }
        }
        return null;
    }

    public int getMonth() {
        return month;
    }

    public SLinkedList<Day> getDays() {
        return days;
    }

    public String toString() {
        return month + " " + days.toString();
    }

    @Override
    public int compareTo(Month o) {
        return Integer.compare(month, o.month);
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Month month) {
            return month.getMonth() == this.getMonth();
        }
        return false;
    }
}