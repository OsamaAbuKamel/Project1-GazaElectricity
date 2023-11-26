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
    private HBox hBox = new HBox();
    private HBox hBox1 = new HBox();
    // private LineChart<Number, Number> lineChart;
    private Statistics statistics;

    public StatisticsScreen(RecordList list) {
        this.list = list;
        statistics = new Statistics(list);
        hBox.getChildren().addAll(cbTotal, cbAvg);
        hBox1.getChildren().addAll(cbMax, cbMin);
        comboBox.getItems().addAll(ElectricityType.values());
        style();
        handle();
        vBox.getChildren().addAll(tfInput, comboBox, hBox, hBox1);
        setTop(btnBack);
        setLeft(vBox);
        TextArea area = new TextArea();
        area.setEditable(false);
        // int day = Integer.parseInt(tfInput.getText());
        area.setPrefSize(60, 60);
        double value = statistics.getStatisticForDay(12, ElectricityType.ISRAELI_LINES, StatisticType.AVERAGE);
        area.setText(String.valueOf(value));
        setCenter(createChart());
    }

    private LineChart<Number, Number> createChart() {
        // Creating the X and Y axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day");
        yAxis.setLabel("Statistic Value");

        // Creating the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistics for Day");

        // Creating the data series for the chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Statistic Values");

        // Populating the data series with values from your getStatisticForDay method
        for (int day = 1; day <= 30; day++) {
            double statisticValue = statistics.getStatisticForDay(day, comboBox.getValue(),
                    StatisticType.TOTAL);
            series.getData().add(new XYChart.Data<>(day, statisticValue));
        }

        // Adding the data series to the chart
        lineChart.getData().add(series);
        return lineChart;
    }

    private void handle() {
        btnBack.setOnAction(e -> {
            SceneChanger.changeScene(new MainScreen(list));
        });
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