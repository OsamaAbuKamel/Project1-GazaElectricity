package com.example.project1gazaelectricity;

import java.time.LocalDate;

public class ElectricityRecord implements Comparable<ElectricityRecord> {
    private LocalDate date;
    // the number of lines in Israel
    private double israeliLines;
    // the power of the power plant in Gaza
    private double gazaPowerPlant;
    // the number of lines in Egypt
    private double egyptianLines;
    // the total supply of electricity
    private double totalSupply;
    // the overall demand of electricity
    private double overallDemand;
    // the number of power cuts in a day
    private double powerCutsHoursDay;
    // temporary variable
    private double temp;
    // Constructor
    public ElectricityRecord() {
    }

    public ElectricityRecord(LocalDate date, double israeliLines, double gazaPowerPlant, double egyptianLines,
            double totalSupply,
            double overallDemand,
            double powerCutsHoursDay, double temp) {
        this.date = date;
        this.setIsraeliLines(israeliLines);
        this.setGazaPowerPlant(gazaPowerPlant);
        this.setEgyptianLines(egyptianLines);
        this.setTotalSupply(totalSupply);
        this.setOverallDemand(overallDemand);
        this.setPowerCutsHoursDay(powerCutsHoursDay);
        this.setTemp(temp);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter & Setter
    public double getIsraeliLines() {
        return this.israeliLines;
    }

    public void setIsraeliLines(double israeliLines) {
        if (israeliLines >= 0)
            this.israeliLines = israeliLines;
        else
            throw new IllegalArgumentException("Israeli Lines cannot be negative");
    }

    public double getGazaPowerPlant() {
        return this.gazaPowerPlant;
    }

    public void setGazaPowerPlant(double gazaPowerPlant) {
        if (gazaPowerPlant >= 0) {
            this.gazaPowerPlant = gazaPowerPlant;
        } else {
            throw new IllegalArgumentException("Gaza Power Plant cannot be negative");
        }
    }

    public double getEgyptianLines() {
        return this.egyptianLines;
    }

    public void setEgyptianLines(double egyptianLines) {
        if (egyptianLines >= 0)
            this.egyptianLines = egyptianLines;
        else
            throw new IllegalArgumentException("Egyptian Lines cannot be negative");
    }

    public double getTotalSupply() {
        return this.totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        if (totalSupply >= 0)
            this.totalSupply = totalSupply;
        else
            throw new IllegalArgumentException("Total Supply cannot be negative");
    }

    public double getOverallDemand() {
        return this.overallDemand;
    }

    public void setOverallDemand(double overallDemand) {
        if (overallDemand >= 0)
            this.overallDemand = overallDemand;
        else
            throw new IllegalArgumentException("Overall demand cannot be negative");
    }

    public double getPowerCutsHoursDay() {
        return this.powerCutsHoursDay;
    }

    public void setPowerCutsHoursDay(double powerCutsHoursDay) {
        if (powerCutsHoursDay >= 0 && powerCutsHoursDay <= 24)
            this.powerCutsHoursDay = powerCutsHoursDay;
        else
            throw new IllegalArgumentException("Power Cuts Hours cannot be negative or greater than 24");
    }

    public double getTemp() {
        return this.temp;
    }

    public void setTemp(double temp) {
        if (temp <= 50)
            this.temp = temp;
        else
            throw new IllegalArgumentException("Temperature cannot be greater than 50");
    }

    @Override
    public String toString() {
        return getDate() + "," + israeliLines + "," + gazaPowerPlant + "," + egyptianLines + "," + totalSupply + ","
                + overallDemand
                + "," + powerCutsHoursDay + "," + temp + "\n";
    }

    @Override
    public int compareTo(ElectricityRecord o) {
        return Double.compare(israeliLines, o.israeliLines);
    }
}