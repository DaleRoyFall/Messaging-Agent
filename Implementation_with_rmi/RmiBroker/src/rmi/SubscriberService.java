package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberService extends Remote {
    void printMessage(Message message) throws RemoteException;
}
