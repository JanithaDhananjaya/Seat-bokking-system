package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.CustomerBooking;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddCustomer {
    public AddCustomer() {
        readBookingDetailsFile();
    }

    @FXML
    private TextField txtName;

    @FXML
    private FlowPane container;

    @FXML
    private List labels;

    private static List<String> reservedSeats = new ArrayList<>();

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup route;

    private List<CustomerBooking> customerBookings = new ArrayList<>();

    @FXML
    private Button btnReserve;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reserveData() {

        Gson gsonBuilder = new GsonBuilder().create();

        CustomerBooking customerBooking = new CustomerBooking();
        customerBooking.setCustomerId(txtName.getText());
        customerBooking.setBookingDate(datePicker.getValue().toString());
        customerBooking.setRoute(((RadioButton) route.getSelectedToggle()).getText());
        customerBooking.setSeats(reservedSeats);

        customerBookings.add(customerBooking);

        String s = gsonBuilder.toJson(customerBookings);

        try (FileWriter file = new FileWriter("booking.json")) {

            file.write(s);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void view() {
        if(datePicker.getValue()== null || txtName.getText() == null){
            return;
        }
        emptySeats();
        container.setVisible(true);
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
                button.setTextFill(Color.WHITE);
                button.setDisable(true);
            }
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    reservedSeats.add(String.valueOf(finalI));
                }
            });
            container.getChildren().add(button);
        }
        btnReserve.setVisible(true);
    }

    private void emptySeats() {
        reservedSeats.clear();
    }
}










