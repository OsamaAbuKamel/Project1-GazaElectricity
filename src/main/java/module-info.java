module com.example.project1gazaelectricity {
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens com.example.project1gazaelectricity to javafx.fxml;
    exports com.example.project1gazaelectricity;
}