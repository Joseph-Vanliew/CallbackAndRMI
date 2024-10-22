package interfaces;
// Interfaces/src/Callback.java

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
