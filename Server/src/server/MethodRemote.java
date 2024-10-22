package server;

import interfaces.Method; //shared
import interfaces.Callback; //shared
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MethodRemote extends UnicastRemoteObject implements Method {

    private String serverHostName;

    protected MethodRemote() throws RemoteException {
        super();
        try {
            //hostname of the machine where the server is running
            serverHostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            serverHostName = "unknown";
        }
    }

    // This method checks if "time" is entered, otherwise capitalizes the string
    @Override
    public String action(String input) throws RemoteException {
        if ("time".equalsIgnoreCase(input)) {
            // Return the current date and time
            return new SimpleDateFormat("EEE, MMM d, HH:mm:ss yyyy").format(new Date());
        } else {
            // Return the capitalized version of the input
            return input.toUpperCase();
        }
    }

    // This method registers a callback and invokes the callback method with the result
    @Override
    public void registerCallback(Callback clientCallback, String input) throws RemoteException {
        String response = action(input);
        clientCallback.receiveMessage(response);
    }

    // Method to return the server's hostname
    @Override
    public String getServerHostName() throws RemoteException {
        return serverHostName;
    }
}