package com.example.project1gazaelectricity;

public class Main {

  // A main method to test the code
  public static void main(String[] args) {
    RecordList list = new RecordList();
    ElectricityRecord record3 = new ElectricityRecord("2022-10-02", 74.0, 6.0, 10.0, 2.0, 1.0, 3.0, 5.0);
    ElectricityRecord record1 = new ElectricityRecord("2023-10-01", 10.0, 5.0, 8.0, 20.0, 15.0, 2.5, 25.0);
    ElectricityRecord record2 = new ElectricityRecord("2023-11-01", 12.0, 6.0, 9.0, 22.0, 18.0, 3.0, 26.0);
    ElectricityRecord record4 = new ElectricityRecord("2023-12-01", 100.0, 6.0, 9.0, 22.0, 18.0, 3.0, 26.0);
    list.add(record1);
    list.add(record2);
    list.add(record3);
    list.add(record4);
    // System.out.println(test.getTotalForYear(2023));
    // test.print();
    Statistics statistics = new Statistics(list);
   
    // System.out.println( "year 2023: "+statistics.getTotalForYear(2023,ElectricityType.ISRAELI_LINES,StatisticType.TOTAL));
    // System.out.println("month 10: "+ statistics.getStatisticForMonth(10,ElectricityType.ISRAELI_LINES,StatisticType.MIN));
    System.out.println("day 1: "+ statistics.getStatisticForDay(1,ElectricityType.ISRAELI_LINES,StatisticType.MIN));
    // System.out.println("day 1: "+ statistics.getStatisticForDay(1,ElectricityType.GAZA_POWER,StatisticType.AVERAGE));

  }
}