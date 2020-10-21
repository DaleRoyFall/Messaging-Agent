package rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Iterator;

public class Broker extends UnicastRemoteObject implements BrokerService {

    protected Broker() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        context.rebind("rmi://localhost:1099/broker", new Broker());
        System.out.println("Broker server successfully started....");
    }

    @Override
    public void routingMessage(Message message) throws NamingException {
        System.out.println("Received message from publisher...");
        System.out.println("Sending message to subscribers...");
        Context context = new InitialContext();
        Enumeration<NameClassPair> enumeration = context.list("rmi://localhost/");
        Iterator<NameClassPair> iterator = enumeration.asIterator();
        while (iterator.hasNext()) {
            String rmi = iterator.next().getName();
            try {
                if (!rmi.equals("broker")) {
                    String id = rmi.substring("subscriber".length());
                    if (Integer.parseInt(id) == message.getGroupId()) {
                        SubscriberService subscriberService = (SubscriberService) context.lookup("rmi://localhost/" + rmi);
                        subscriberService.printMessage(message);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
