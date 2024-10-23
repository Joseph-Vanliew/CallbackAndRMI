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

    @Override
    public String action(String input) throws RemoteException {
        if ("time".equalsIgnoreCase(input)) {
            return new SimpleDateFormat("EEE, MMM d, HH:mm:ss yyyy").format(new Date());
        } else {
            return input.toUpperCase();
        }
    }

    @Override
    public void registerCallback(Callback clientCallback, String input) throws RemoteException {
        String response = action(input);
        clientCallback.receiveMessage(response);
    }

    // returns the server's hostname
    @Override
    public String getServerHostName() throws RemoteException {
        return serverHostName;
    }
}