package com.example.project1gazaelectricity;

public class Test {
    private SLinkedList<SLinkedList<SLinkedList<ElectricityRecord>>> yearList;
    private SLinkedList<SLinkedList<ElectricityRecord>> monthList;
    private SLinkedList<ElectricityRecord> records;

    Test() {
        yearList = new SLinkedList<>();
        monthList = new SLinkedList<>();
        records = new SLinkedList<>();
    }

}