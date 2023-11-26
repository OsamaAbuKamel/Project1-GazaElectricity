package com.example.project1gazaelectricity;

public class Month implements Comparable<Month> {
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
    public Day get(int index){
        return days.get(index);
    }
    public int getMonth() {
        return month;
    }
    public SLinkedList<Day> getDays() {
        return days;
    }
    public String toString() {
        return String.format("%02d", month);
    }

    @Override
    public int compareTo(Month o) {
        return Integer.compare(month, o.month);

    }

    
}