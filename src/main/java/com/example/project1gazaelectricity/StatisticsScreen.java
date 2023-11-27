package com.example.project1gazaelectricity;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticsScreen extends BorderPane {
    private RecordList list;
    private Button btnBack = new Button();
    private VBox vBox = new VBox();
    private TextField tfInput = new TextField();
    private CheckBox cbTotal = new CheckBox("TOTAL");
    private CheckBox cbAvg = new CheckBox("AVERAGE");
    private CheckBox cbMax = new CheckBox("MAXIMUM");
    private CheckBox cbMin = new CheckBox("MINIMUM");
    private ComboBox<ElectricityType> comboBox = new ComboBox<>();
    private ComboBox<String> comboBox1 = new ComboBox<>();
    private HBox hBox = new HBox();
    private HBox hBox1 = new HBox();
    private VBox box = new VBox();
    private TextArea area = new TextArea();
    private Button btnResult = new Button("Result");
    private Statistics statistics;
    private NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    private LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();

    public StatisticsScreen(RecordList list) {
        this.list = list;
        statistics = new Statistics(list);
        initialize();
        style();
        handle();
        setTop(btnBack);
        setLeft(vBox);
        setCenter(box);
    }

    private void initialize() {
        hBox.getChildren().addAll(cbTotal, cbAvg);
        hBox1.getChildren().addAll(cbMax, cbMin);
        comboBox.getItems().addAll(ElectricityType.values());
        comboBox1.getItems().addAll("Day", "Month", "Year");
        vBox.getChildren().addAll(comboBox1, tfInput, comboBox, hBox, hBox1, btnResult);
        box.getChildren().addAll(area, createChart());
        box.setPrefHeight(300);
        box.setPrefWidth(300);
    }

    private LineChart<Number, Number> createChart() {
        // Creating the X and Y axes

        // Set X-axis label based on the selected time unit
        String timeUnit = comboBox1.getValue();
        xAxis.setLabel(timeUnit);
        yAxis.setLabel("Statistic Value");
        // Creating the line chart
        lineChart.setTitle("Statistics for " + timeUnit);
        // Creating the data series for the chart
        series.setName("Statistic Values");
        // Check if tfInput is not empty before parsing as an integer

        return lineChart;
    }

    private void handle() {
        btnBack.setOnAction(e -> {
            SceneChanger.changeScene(new MainScreen(list));
        });
        btnResult.setOnAction(e -> {
            double value = 0;
            String timeUnit = comboBox1.getValue();
            int input = Integer.parseInt(tfInput.getText());
            if (comboBox1.getValue() == timeUnit) {
                value = statistics.getStatisticForDay(Integer.parseInt(tfInput.getText()),
                        comboBox.getValue(),
                        getType());
            } else if (comboBox1.getValue() == timeUnit) {
                value = statistics.getStatisticForMonth(Integer.parseInt(tfInput.getText()),
                        comboBox.getValue(),
                        getType());
            } else if (comboBox1.getValue() == timeUnit) {
                value = statistics.getStatisticForYear(Integer.parseInt(tfInput.getText()),
                        comboBox.getValue(),
                        getType());
            }
            area.setText(String.valueOf(value));
            series.getData().add(new XYChart.Data<>(input, value));
            lineChart.getData().add(series);
        });
    }

    private StatisticType getType() {
        if (cbTotal.isSelected()) {
            return StatisticType.TOTAL;
        }
        if (cbAvg.isSelected()) {
            return StatisticType.AVERAGE;
        }
        if (cbMax.isSelected()) {
            return StatisticType.MAX;
        }
        if (cbMin.isSelected()) {
            return StatisticType.MIN;
        }
        return null;
    }

    private void style() {
        Image image = new Image(
                "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\left-arrow.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        btnBack.setGraphic(imageView);
        btnBack.setStyle("-fx-background-color: transparent;");
    }
}