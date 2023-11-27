package com.example.project1gazaelectricity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ManagementScreen extends BorderPane {
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
    private Button backBtn = new Button();
    private Button searchBtn = new Button("Search");
    private  HBox buttonBox = new HBox();
    private TableView<ElectricityRecord> tableView = new TableView<>();
    private RecordList list;
    public ManagementScreen(RecordList list) {
        getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.list = list;
        style();
        handle();
        setRight(initializeTable());
        setLeft(initializeGridPane());
        setTop(backBtn);
    }

    private GridPane initializeGridPane() {
        GridPane inputGrid = new GridPane();
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
        buttonBox.getChildren().addAll(addBtn,updateBtn,deleteBtn,searchBtn);
        inputGrid.add(buttonBox, 1, 8);
        inputGrid.setPadding(new Insets(16));
        inputGrid.setVgap(8);
        inputGrid.setHgap(8);
        return inputGrid;
    }

    private void handle() {
        addBtn.setOnAction(e -> {
            ElectricityRecord newRecord = getRecord();
            list.add(newRecord);
            updateTableView();
            clear();
        });
        updateBtn.setOnAction(e -> {
            ElectricityRecord updatedRecord = getRecord();
            list.update(updatedRecord);
            updateTableView();
            clear();
        });
        deleteBtn.setOnAction(e -> {
            ObservableList<ElectricityRecord> searchResults = FXCollections.observableArrayList(list.search(getRecord().getDate()));
            if (!searchResults.isEmpty()) {
                tableView.setItems(searchResults);
            }
        });
        searchBtn.setOnAction(e -> {
            ObservableList<ElectricityRecord> list1 = FXCollections.observableArrayList(list.search(getCalender()));
            if (list1.isEmpty()) {
                return;
            }
            tableView.setItems(list1); // Set the table view items to the search results
            clear();
        });
        backBtn.setOnAction(e -> {
            SceneChanger.changeScene(new MainScreen(list));
        });
    }

    private TableView<ElectricityRecord> initializeTable() {
        TableColumn<ElectricityRecord, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDateInfo());
        });
        TableColumn<ElectricityRecord, Double> israeliLinesCol = new TableColumn<>("Israeli Lines");
        israeliLinesCol.setCellValueFactory(new PropertyValueFactory<>("israeliLines"));
        TableColumn<ElectricityRecord, Double> gazaPlantCol = new TableColumn<>("Gaza Plant");
        gazaPlantCol.setCellValueFactory(new PropertyValueFactory<>("gazaPowerPlant"));
        TableColumn<ElectricityRecord, Double> egyptianLinesCol = new TableColumn<>("Egyptian Lines");
        egyptianLinesCol.setCellValueFactory(new PropertyValueFactory<>("egyptianLines"));
        TableColumn<ElectricityRecord, Double> totalSupplyCol = new TableColumn<>("Total Supply");
        totalSupplyCol.setCellValueFactory(new PropertyValueFactory<>("totalSupply"));
        TableColumn<ElectricityRecord, Double> overallDemandCol = new TableColumn<>("Overall Demand");
        overallDemandCol.setCellValueFactory(new PropertyValueFactory<>("overallDemand"));
        TableColumn<ElectricityRecord, Double> powerCutsCol = new TableColumn<>("Power Cuts Hours Day");
        powerCutsCol.setCellValueFactory(new PropertyValueFactory<>("powerCutsHoursDay"));
        TableColumn<ElectricityRecord, Double> tempCol = new TableColumn<>("Temp");
        tempCol.setCellValueFactory(new PropertyValueFactory<>("temp"));
        tableView.getColumns().addAll(dateCol, israeliLinesCol, gazaPlantCol,
                egyptianLinesCol, totalSupplyCol, overallDemandCol, powerCutsCol, tempCol);
        updateTableView();
        return tableView;
    }
    private void updateTableView() {
        if (tableView != null) {
            tableView.getItems().clear();
        }
        for (Year year : list.getRecords()) {
            for (Month month : year.getMonthList()) {
                for (Day day : month.getDays()) {
                    tableView.getItems().addAll(day.getRecord());
                }
            }
        }

    }
    private Calendar getCalender() {
        Calendar calendar = new GregorianCalendar();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            calendar.set(newValue.getYear(), newValue.getMonthValue(), newValue.getDayOfMonth());
        });
        return calendar;
    }

    private void style() {
        
        Image image = new Image(
                "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\left-arrow.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        backBtn.setGraphic(imageView);
        backBtn.setStyle("-fx-background-color: transparent;");
        buttonBox.setPadding(new Insets(16));
        buttonBox.setSpacing(16);
    }

    private void clear() {
        datePicker.setValue(null);
        israelInput.clear();
        gazaInput.clear();
        egyptInput.clear();
        totalInput.clear();
        overallDemandInput.clear();
        powerCutsHoursDayInput.clear();
        tempInput.clear();
    }

    private ElectricityRecord getRecord() {
        double israeliLines = Double.parseDouble(israelInput.getText());
        double gazaPowerPlant = Double.parseDouble(gazaInput.getText());
        double egyptianLines = Double.parseDouble(egyptInput.getText());
        double totalSupply = Double.parseDouble(totalInput.getText());
        double overallDemand = Double.parseDouble(overallDemandInput.getText());
        double powerCutsHoursDay = Double.parseDouble(powerCutsHoursDayInput.getText());
        double temp = Double.parseDouble(tempInput.getText());
        // Create ElectricityRecord object
        ElectricityRecord record = new ElectricityRecord(
                getCalender(),
                israeliLines,
                gazaPowerPlant,
                egyptianLines,
                totalSupply,
                overallDemand,
                powerCutsHoursDay,
                temp);
        return record;
    }
}