package rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Subscriber extends UnicastRemoteObject implements SubscriberService {
    private static Scanner scannerId = new Scanner(System.in);
    SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");

    protected Subscriber() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        System.out.println("Subscriber successfully started...");
        int groupId = getGroupId();
        context.rebind("rmi://localhost:1099/subscriber" + groupId, new Subscriber());
    }

    @Override
    public void printMessage(Message message) {
        Date date = new Date();

        System.out.println(formatter.format(date) + "  Publisher: " + message.getMessage());
    }

    private static int getGroupId() {
        System.out.print("Type group id: ");
        int id = scannerId.nextInt();
        System.out.println();

        return id;
    }
}
