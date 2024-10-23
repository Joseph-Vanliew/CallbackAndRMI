package client;

import interfaces.Method; //shared with server
import java.rmi.Naming;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {

    private static final Logger LOGGER = Logger.getLogger(MyClient.class.getName());

    public static void main(String[] args) {
        try {
            // Look up the remote object (Method) from the RMI registry
            Method stub = (Method) Naming.lookup("rmi://localhost:1099/lab4");

            // Get the server's hostname (client-side)
            String serverHostName = stub.getServerHostName();

            Scanner scanner = new Scanner(System.in);
            String input;

            while (true) {
                System.out.print("Enter a string to send to the server (empty to quit): ");
                input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Exiting...");
                    break;
                }
                // create stub and send to server via RMI
                String rmiResponse = stub.action(input);

                // Registering the callback with the server
                CallbackImpl callback = new CallbackImpl(serverHostName);
                stub.registerCallback(callback, input);

                // Displaying the RMI response
                System.out.println("Using RMI from " + serverHostName + ": " + rmiResponse);
            }

            scanner.close();
            System.exit(0);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while communicating with the server.", e);
            // For potential debugging
            e.printStackTrace();
        }
    }
}