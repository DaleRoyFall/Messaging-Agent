package rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Publisher {
    public static void main(String[] args) throws NamingException, RemoteException, InterruptedException {
        Context context = new InitialContext();
        BrokerService brokerService = (BrokerService) context.lookup("rmi://localhost/broker");
        Publisher publisher = new Publisher();
        publisher.makeMenu(brokerService);

    }

    void makeMenu(BrokerService brokerService) throws InterruptedException, RemoteException {
        int groupId = getGroupId();
        String messageText = getMessage();
        System.out.println();

        Message message = new Message(groupId, messageText);
        System.out.println("Sending message to broker....");
        brokerService.routingMessage(message);
        Thread.sleep(1000);
        makeMenu(brokerService);
    }

    private int getGroupId() {
        Scanner scannerId = new Scanner(System.in);
        System.out.print("Type id group: ");
        return scannerId.nextInt();
    }

    private String getMessage() {
        Scanner scannerText = new Scanner(System.in);
        System.out.print("Type message: ");
        return scannerText.nextLine();
    }
}
