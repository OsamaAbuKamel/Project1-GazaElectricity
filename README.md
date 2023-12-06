# Gaza Electricity Project

## Overview

This project implements an electricity data management system using combined sorted linked lists. The data is obtained from the OCHA in a CSV file (Electricity.csv), with each line containing a daily electricity record. The project includes a graphical user interface (GUI) built with JavaFX, offering various functionalities for managing, analyzing, and saving electricity data.

**Important Note:** This project prohibits the use of arrays or ArrayLists for data storage.

## Getting Started

Follow the instructions below to set up and run the Gaza Electricity project on your local machine.

### Prerequisites

- Java Development Kit (JDK) installed (version 8 or higher)
- JavaFX library installed
- Git installed

### Installation

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/GazaElectricity.git
   ```

2. Change directory to the project folder:

   ```bash
   cd GazaElectricity
   ```

### Running the Application

1. Compile the Java files:

   ```bash
   javac --module-path /path/to/javafx-sdk-xx/lib --add-modules javafx.controls,javafx.fxml *.java
   ```

   Replace `/path/to/javafx-sdk-xx` with the actual path to your JavaFX SDK.

2. Run the application:

   ```bash
   java --module-path /path/to/javafx-sdk-xx/lib --add-modules javafx.controls,javafx.fxml Main
   ```

## Usage

Upon running the application, the user will be prompted to load the electricity data file using a file chooser. After loading the data, the user can choose from the following screens:

### Management Screen

1. **Insert New Electricity Record:**
   - Allows the user to add a new electricity record.

2. **Update Electricity Record:**
   - Enables the user to update an existing electricity record.

3. **Delete Electricity Record:**
   - Allows the user to delete a specific electricity record.

4. **Search for Electricity Record by Date:**
   - Utilizes a calendar GUI to select a specific date and retrieve the corresponding electricity record.

### Statistics Screen

1. **Specific Electricity Statistic:**
   - Displays statistics for a specific day, month, or year across all months and years.
   - Options include total (sum), average, maximum, and minimum.

2. **Total Statistic for All Data:**
   - Provides overall statistics for the entire dataset, including total (sum), average, maximum, and minimum.

### Save Screen

- Allows the user to save the updated linked lists back to a new file in the same CSV format.
- Utilizes a file chooser to select the folder for saving the new file.

## Contributing

If you would like to contribute to this project, please follow the [Contribution Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Special thanks to OCHA for providing the electricity data.
- The project makes use of JavaFX for creating the graphical user interface.
