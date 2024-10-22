package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Method extends Remote {
    String action(String input) throws RemoteException;
    void registerCallback(Callback clientCallback, String input) throws RemoteException;
    String getServerHostName() throws RemoteException;  // New method to get the server's hostname
}