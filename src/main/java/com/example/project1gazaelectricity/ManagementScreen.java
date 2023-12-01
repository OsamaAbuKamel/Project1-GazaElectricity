package com.example.project1gazaelectricity;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private HBox buttonBox = new HBox();
    private VBox vLeft = new VBox();
    private TableView<ElectricityRecord> tableView = new TableView<>();
    private RecordList list;

    public ManagementScreen(RecordList list) {
        String fileName = "style.css";
        if (fileName != null) {
            getStylesheets().add(getClass().getResource(fileName).toExternalForm());
        }
        this.list = list;
        style();
        handle();
        setRight(initializeTable());
        vLeft.getChildren().addAll(backBtn, initializeGridPane());
        setLeft(vLeft);
    }

    private GridPane initializeGridPane() {
        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("Date:"), 0, 0);
        inputGrid.add(datePicker, 1, 0);
        inputGrid.add(new Label("Israel Lines:"), 0, 1);
        inputGrid.add(israelInput, 1, 1);
        inputGrid.add(new Label("Gaza Lines:"), 0, 2);
        inputGrid.add(egyptInput, 1, 2);
        inputGrid.add(new Label("Egypt Lines:"), 0, 3);
        inputGrid.add(gazaInput, 1, 3);
        inputGrid.add(new Label("Total Supply:"), 0, 4);
        inputGrid.add(totalInput, 1, 4);
        inputGrid.add(new Label("Over All Demand:"), 0, 5);
        inputGrid.add(overallDemandInput, 1, 5);
        inputGrid.add(new Label("Power Cuts:"), 0, 6);
        inputGrid.add(powerCutsHoursDayInput, 1, 6);
        inputGrid.add(new Label("Temp:"), 0, 7);
        inputGrid.add(tempInput, 1, 7);
        buttonBox.getChildren().addAll(addBtn, updateBtn, deleteBtn, searchBtn);
        inputGrid.add(buttonBox, 1, 8);
        inputGrid.setPadding(new Insets(16));
        inputGrid.setVgap(8);
        inputGrid.setHgap(8);
        return inputGrid;
    }

    private void handle() {
        addBtn.setOnAction(e -> {
            try {
                ElectricityRecord newRecord = getRecord();
                list.add(newRecord);
                updateTableView();
                clear();
            } catch (IllegalArgumentException ex) {
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        updateBtn.setOnAction(e -> {
            try {
                ElectricityRecord updatedRecord = getRecord();
                list.update(updatedRecord);
                updateTableView();
                clear();
            } catch (IllegalArgumentException ex) {
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        deleteBtn.setOnAction(e -> {
            try {
                list.remove(getRecord());
                updateTableView();
                clear();
            } catch (IllegalArgumentException ex) {
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        searchBtn.setOnAction(e -> {
            try {
                ElectricityRecord record = list.search(getDate());
                if (record != null) {
                    ObservableList<ElectricityRecord> list1 = FXCollections.observableArrayList(record);
                    tableView.setItems(list1);
                } else
                    alert(AlertType.WARNING, "Warning", "No record found for the given date");
                clear();
            } catch (NullPointerException ex) {
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        backBtn.setOnAction(e -> {
            clear();
            SceneChanger.changeScene(new MainScreen(list));
            updateTableView();
        });
    }

    private TableView<ElectricityRecord> initializeTable() {
        TableColumn<ElectricityRecord, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
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
        dateCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        israeliLinesCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        gazaPlantCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        egyptianLinesCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        totalSupplyCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        overallDemandCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        powerCutsCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        tempCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
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

    private LocalDate getDate() {
        return datePicker.getValue();
    }

    private void style() {
        setStyle("-fx-background-color:#ffffff");
        Image image = new Image(
                "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\left-arrow.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        backBtn.setGraphic(imageView);
        backBtn.setStyle("-fx-background-color: transparent;");
        buttonBox.setPadding(new Insets(16));
        buttonBox.setSpacing(16);
        vLeft.setMaxWidth(520);
        vLeft.setMinWidth(520);
        tableView.setMaxWidth(880);
        tableView.setMinWidth(880);
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
        ElectricityRecord record = new ElectricityRecord(
                getDate(),
                israeliLines,
                gazaPowerPlant,
                egyptianLines,
                totalSupply,
                overallDemand,
                powerCutsHoursDay,
                temp);
        return record;
    }

    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}