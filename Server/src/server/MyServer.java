package server;

import interfaces.Method;  // shared interface
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer {

    private static final Logger LOGGER = Logger.getLogger(MyServer.class.getName());

    public static void main(String[] args) {
        try {
            // must match MyClient
            LocateRegistry.createRegistry(1099);

            Method stub = new MethodRemote();

            Naming.rebind("rmi://localhost:1099/lab4", stub);

            System.out.println("Server is running...");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Some kind of error occurred while starting the server: ", e);
        }
    }
}