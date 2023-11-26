package com.example.project1gazaelectricity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatePicker datePicker = new DatePicker();

        // Add a change listener to the DatePicker value property
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(newValue.getYear(), newValue.getMonthValue() - 1, newValue.getDayOfMonth());
            
        });

        Pane pane = new Pane();
        pane.getChildren().add(datePicker);
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("Date Picker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Calendar calendarFromString(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    private Calendar calendarFromDate(DatePicker datePicker) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, datePicker.getValue().getYear());
        calendar.set(Calendar.MONTH, datePicker.getValue().getMonthValue() - 1); // Month is zero-based
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getValue().getDayOfMonth());
        return calendar;
    }

    private String formatDate(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static void main(String[] args) {
        launch(args);
    }

}