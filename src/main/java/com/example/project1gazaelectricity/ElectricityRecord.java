package com.example.project1gazaelectricity;

import java.util.Calendar;

public class ElectricityRecord implements Comparable<ElectricityRecord> {
    // Represents the date
    private Calendar date;
    // Represents the electricity supply from Israeli lines
    private double israeliLines;
    // Represents the electricity supply from the Gaza power plant
    private double gazaPowerPlant;
    // Represents the electricity supply from Egyptian lines
    private double egyptianLines;
    // Represents the total electricity supply
    private double totalSupply;
    // Represents the overall electricity demand
    private double overallDemand;
    // Represents the number of hours of power cuts per day
    private double powerCutsHoursDay;
    // Represents the temperature
    private double temp;

    // Constructor
    public ElectricityRecord() {
    }

    public ElectricityRecord(Calendar date, double israeliLines, double gazaPowerPlant, double egyptianLines,
            double totalSupply,
            double overallDemand,
            double powerCutsHoursDay, double temp) {
        this.date=date;
        this.setIsraeliLines(israeliLines);
        this.setGazaPowerPlant(gazaPowerPlant);
        this.setEgyptianLines(egyptianLines);
        this.setTotalSupply(totalSupply);
        this.setOverallDemand(overallDemand);
        this.setPowerCutsHoursDay(powerCutsHoursDay);
        this.setTemp(temp);
    }


    public Calendar getDate() {
        return this.date;
    }

    public void setDate(Calendar date) {
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
    return  "," + israeliLines + "," + gazaPowerPlant + "," + egyptianLines + "," + totalSupply + "," + overallDemand + "," + powerCutsHoursDay + "," + temp+"\n";
}
//   @Override
// public String toString() {
//     return getDate().get(Calendar.YEAR)+"/"+getDate().get(Calendar.MONTH)+"/"+getDate().get(Calendar.DAY_OF_MONTH)+ "," + israeliLines + "," + gazaPowerPlant + "," + egyptianLines + "," + totalSupply + "," + overallDemand + "," + powerCutsHoursDay + "," + temp+"\n";
// }

    @Override
    public int compareTo(ElectricityRecord o) {
        return Double.compare(israeliLines, o.israeliLines);
    }
}