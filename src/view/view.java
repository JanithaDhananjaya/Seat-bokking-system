package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import model.CustomerBooking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class view {
    private List<CustomerBooking> customerBookings = new ArrayList<>();
    @FXML
    private FlowPane container;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup route;

    private List<String> reservedSeats = new ArrayList<>();

    public view() {
        readBookingDetailsFile();
    }

    public void readBookingDetailsFile() {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("booking.json"));

            // convert JSON array to list of users
            List<CustomerBooking> users = new Gson().fromJson(reader, new TypeToken<List<CustomerBooking>>() {
            }.getType());

            if (users != null) {
                customerBookings = users;
            }

            // close reader
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void emptySeats() {
        reservedSeats.clear();
        ;
    }


    public void viewSeats() {

        emptySeats();

        LocalDate date = datePicker.getValue();
        String routing = ((RadioButton) this.route.getSelectedToggle()).getText();

        for (CustomerBooking c : customerBookings) {

            if (c.getBookingDate().equals(date.toString()) && c.getRoute().equals(routing)) {
                List<String> seats = c.getSeats();
                for (String s : seats
                ) {
                    reservedSeats.add(s);
                }
            }
        }
        container.getChildren().clear();
        String[] data = new String[42];

        for (int i = 1; i <= data.length; i++) {
            Button button = new Button("Seat:" + i);
            button.setId(Integer.toString(i));
            if (reservedSeats.contains(String.valueOf(i))) {
                button.setStyle("-fx-background-color: red;");
            }
            container.getChildren().add(button);
        }
    }
}
