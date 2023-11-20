package com.example.project1gazaelectricity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManagementScreen extends HBox {
    private TableView<ElectricityRecord> tableView = new TableView();
    private TableColumn<ElectricityRecord, String> colDate = new TableColumn("Date");
    private TableColumn<ElectricityRecord, Double> colIsrael = new TableColumn("Israel Lines");
    private TableColumn<ElectricityRecord, Double> colEgypt = new TableColumn("Egypt Lines");
    private TableColumn<ElectricityRecord, Double> colGazaPower = new TableColumn("Gaza Power");
    private TableColumn<ElectricityRecord, Double> colTotalSupply = new TableColumn("Total Supply");
    private TableColumn<ElectricityRecord, Double> colTemp = new TableColumn("Temp");
    private TableColumn<ElectricityRecord, Double> colPowerCuts = new TableColumn("Power Cuts");
    private GridPane inputGrid = new GridPane();

    // Input Fields
    private DatePicker datePicker = new DatePicker();
    private TextField israelInput = new TextField();
    private TextField egyptInput = new TextField();
    private TextField gazaInput = new TextField();
    private TextField overallDemandInput = new TextField();
    private TextField totalInput = new TextField();
    private TextField powerCutsHoursDayInput = new TextField();
    private TextField tempInput = new TextField();

    // Buttons
    private Button addBtn = new Button("Add");
    private Button updateBtn = new Button("Update");
    private Button deleteBtn = new Button("Delete");
    private Button backBtn = new Button("Back");
    private Button searchBtn = new Button("Search");

    private RecordList list = new RecordList(); // Constructor

    public ManagementScreen(RecordList list) {
        this.list = list;
        handle();
        // Add columns to TableView
        tableView.getColumns().addAll(
                colDate,
                colIsrael,
                colEgypt,
                colGazaPower,
                colPowerCuts,
                colTotalSupply,
                colTemp);

        // Add inputs to GridPane
        inputGrid.add(new Label("Date:"), 0, 0);
        inputGrid.add(datePicker, 1, 0);

        inputGrid.add(new Label("Israel:"), 0, 1);
        inputGrid.add(israelInput, 1, 1);

        inputGrid.add(new Label("Egypt:"), 0, 2);
        inputGrid.add(egyptInput, 1, 2);

        inputGrid.add(new Label("Gaza:"), 0, 3);
        inputGrid.add(gazaInput, 1, 3);

        inputGrid.add(new Label("Total:"), 0, 4);
        inputGrid.add(totalInput, 1, 4);

        inputGrid.add(new Label("Over All Demand:"), 0, 5);
        inputGrid.add(overallDemandInput, 1, 5);

        inputGrid.add(new Label("Power Cuts:"), 0, 6);
        inputGrid.add(powerCutsHoursDayInput, 1, 6);

        inputGrid.add(new Label("Temp:"), 0, 7);
        inputGrid.add(tempInput, 1, 7);

        // Add buttons to HBox
        HBox buttonBox = new HBox(
                addBtn,
                updateBtn,
                deleteBtn,
                backBtn,
                searchBtn);

        // Add components to VBox
        getChildren().addAll(
                tableView,
                inputGrid,
                buttonBox);
    }

    private void handle() {
        addBtn.setOnAction(e -> {
            list.add(new ElectricityRecord(formatDate(LocalDate.now()), Double.parseDouble(israelInput.getText()),
                    Double.parseDouble(gazaInput.getText()), Double.parseDouble(egyptInput.getText()),
                    Double.parseDouble(totalInput.getText()), Double.parseDouble(overallDemandInput.getText()),
                    Double.parseDouble(powerCutsHoursDayInput.getText()), Double.parseDouble(tempInput.getText())));
        });
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            String formattedDate = formatDate(selectedDate);
            System.out.println("Selected Date: " + formattedDate);
        });
    }

    private String formatDate(LocalDate date) {
        // Use DateTimeFormatter to format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(formatter);
    }
}