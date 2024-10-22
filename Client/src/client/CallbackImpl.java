package client;

import interfaces.Callback;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallbackImpl extends UnicastRemoteObject implements Callback {

    private final String serverHostName;

    // Constructor accepts the server's hostname
    protected CallbackImpl(String serverHostName) throws RemoteException {
        super();
        this.serverHostName = serverHostName;
    }

    // Method to receive a message from the server via the callback
    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Using Callback (" + serverHostName + "): " + message);
    }
}