package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CustomerBooking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    private List<CustomerBooking> customerBookings = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        printInstructions();
    }

    public void printInstructions() {
        System.out.println("\t***Train Booking System*** ");
        System.out.println();

        System.out.println("Description:");
        System.out.println("A: Add a customer to a seat. \nV: View all seats. \nE: Display Empty seats. \nD: Delete customer from seat." +
                "\nF: Find the seat for a given customers name.\nQ: Quit.");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the letter which is related your requirement: ");
        String input = scanner.next(); //getting user input.


        switch (input) {
            case "A":
                addCustomer();
                break;

            case "V":
                view();
                break;

            case "E":
                emptyMethod();
                break;

            case "D":
                deleteMethod();
                break;

            case "F":
                findMethod();
                break;

            case "Q":
                break;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void addCustomer() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/addCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Book Seats");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    private void view() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/viewAllSeats.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("View Seats");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    private static void emptyMethod() {
    }


    private static void deleteMethod() {
    }


    private static void findMethod() {
    }
}
