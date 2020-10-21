package rmi;

import javax.naming.NamingException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerService extends Remote {
    void routingMessage(Message message) throws RemoteException, NamingException;
}
