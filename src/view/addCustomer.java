package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import model.CustomerBooking;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class addCustomer {
    public addCustomer() {
        readBookingDetailsFile();
    }

    @FXML
    private TextField txtName;

    @FXML
    private FlowPane container;

    @FXML
    private List labels;

    private static List<String> reservedSeats;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup route;

    private List<CustomerBooking> customerBookings = new ArrayList<>();

    @FXML
    void getBookingDate(ActionEvent event) {
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

    public static void saveData(String[] data) {
        File fIle = new File("C:\\Users\\Tharuka\\Desktop\\data.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fIle);
            for (int i = 0; i < data.length; i++) {
                fileWriter.write(data[i] + "-" + "Available" + "\n");
                //   fileWriter.write(String.valueOf(data));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        String[] data = new String[42];
        for (int i = 0; i < data.length; i++) {
            data[i] = "Seat " + (i + 1);
        }
        saveData(data);
        reservedSeats = new ArrayList<>();
        labels = new ArrayList<>();

        for (int i = 1; i <= 42; i++) {
            Button button = new Button("Seat:" + i);
            button.setId(Integer.toString(i));
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    reservedSeats.add(String.valueOf(finalI));
                }
            });
            container.getChildren().add(button);

        }
    }
}










