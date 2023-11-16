package com.example.project1gazaelectricity;

public class DateManagement {
    SLinkedList<SLinkedList<SLinkedList<Integer>>> years = new SLinkedList<>();
    SLinkedList<SLinkedList<Integer>> months = new SLinkedList<>();
    SLinkedList<Integer> days = new SLinkedList<>();

    public DateManagement() {
    }

    public void addDay(int year, int month, int day) {
        // Ensure that the year exists
        while (years.length() <= year) {
            years.insertAtLast(new SLinkedList<>());
        }
        SLinkedList<SLinkedList<Integer>> yearList = years.get(year);
        // Ensure that the month exists in the specified year
        while (yearList.length() <= month) {
            yearList.insertAtLast(new SLinkedList<>());
        }
        SLinkedList<Integer> monthList = yearList.get(month);
        // Add the day to the specified month
        monthList.insertAtLast(day);
    }

    // Add a month to the specified year
    public void addMonth(int year, int month) {
        // Ensure that the year exists
        while (years.length() <= year) {
            years.insertAtLast(new SLinkedList<>());
        }
        SLinkedList<SLinkedList<Integer>> yearList = years.get(year);
        // Ensure that the month does not already exist in the specified year
        if (yearList.length() <= month) {
            yearList.insertAtLast(new SLinkedList<>());
        }
    }

    public SLinkedList<Integer> getDaysInMonth(int year, int month) {
        if (years.length() > year) {
            SLinkedList<SLinkedList<Integer>> yearList = years.get(year);
            if (yearList.length() > month) {
                return yearList.get(month);
            }
        }
        return new SLinkedList<>(); // Return an empty list if the specified month or year doesn't exist
    }

    public SLinkedList<Integer> getMonthsInYear(int year) {
        SLinkedList<Integer> result = new SLinkedList<>();
        if (years.length() > year) {
            SLinkedList<SLinkedList<Integer>> yearList = years.get(year);
            for (int i = 0; i < yearList.length(); i++) {
                result.insertAtLast(i + 1); // Months are usually 1-indexed
            }
        }
        return result;
    }
    public void printData() {
        System.out.println("Data Management:");
    
        // Print years, months, and days
        for (int year = 0; year < years.length(); year++) {
            SLinkedList<SLinkedList<Integer>> yearList = years.get(year);
    
            for (int month = 0; month < yearList.length(); month++) {
                SLinkedList<Integer> monthList = yearList.get(month);
    
                for (int day : monthList) {
                    System.out.printf("Year: %d, Month: %d, Day: %d\n", year, month, day);
                }
            }
        }
    }
    
  public void print() {
    Node<SLinkedList<SLinkedList<Integer>>> yearNode = years.getHead().getNext();
    int yearIndex = 0;
    while (yearNode != null) {
        SLinkedList<SLinkedList<Integer>> yearList = yearNode.getData();
        System.out.println("Year " + yearIndex + ":");
        Node<SLinkedList<Integer>> monthNode = yearList.getHead().getNext();
        int monthIndex = 0;
        while (monthNode != null) {
            SLinkedList<Integer> monthList = monthNode.getData();
            System.out.println("  Month " + monthIndex + ":");
            Node<Integer> dayNode = monthList.getHead().getNext();
            while (dayNode != null) {
                System.out.println("    Day: " + dayNode.getData());
                dayNode = dayNode.getNext();
            }
            monthNode = monthNode.getNext();
            monthIndex++;
        }
        yearNode = yearNode.getNext();
        yearIndex++;
    }
}

    public static void main(String[] args) {
        DateManagement dataManagement = new DateManagement();
    
        // Add some data
        dataManagement.addDay(2023, 1, 15);
        dataManagement.addDay(2023, 2, 20);
        dataManagement.addDay(2023, 2, 2);
        dataManagement.addDay(2023, 3, 10);
        
        // Print the data
        dataManagement.print();
    }
    
}