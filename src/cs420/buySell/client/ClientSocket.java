package cs420.buySell.client;

import java.io.IOException;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cs420.buySell.gui.*;

/**
 * ClientSocket.java
 *
 * Buy_sell_exchange
 * Kevin Reuter, Jordan Martin, Matt Troutt
 *
 * This class will handle communication between the different clients. It will send the update messages
 * when necessary and call an update method when a message is received.
 */
public class ClientSocket implements Runnable{

    private DatagramSocket socket;
    private BuySellUI ui;
    private static int port;

    /**
     * Constructor method that creates the Datagram socket to send and receive messages.
     */
    public ClientSocket(BuySellUI ui) {
        try {
            Random rand = new Random();
            do{
                port = rand.nextInt(25000);
            } while(port < 9000);
            socket = new DatagramSocket(port);

        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        this.ui = ui;

    }

    /**
     * This method sends out a message to all connected clients to update the WantToBuy table
     * @param clients A LinkedList of connected clients.
     */
    public void sendUpdateBuy(LinkedList<Client> clients) {
        for(Client client : clients) {
            InetAddress address = client.getAddress();
            int port = client.getPort();

            String output = "Update_want_to_buy";

            sendData(output, address, port);
        }
    }

    /**
     * This method sends out a message to all connected clients to update the WantToSell table.
     * @param clients A LinkedList of connected clients.
     */
    public void sendUpdateSell(LinkedList<Client> clients) {
        for(Client client : clients) {
            InetAddress address = client.getAddress();
            int port = client.getPort();

            String output = "Update_want_to_sell";

            sendData(output, address, port);
        }
    }

    /**
     * Sends a message to a specific client
     * @param sendMessage The message to be sent
     * @param client The address of the client that will receive the message
     * @param port The port of the receiving client.
     */
    public void sendData(String sendMessage, InetAddress client, int port) {
        try {
            byte[] data = sendMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(data, data.length, client, port);
            socket.send(sendPacket);


        } catch(IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * This method receives packets from the datagram socket and sends them to an interpreter method.
     */
    public void waitForPackets() {
        try{
            byte[] data = new byte[1024];
            DatagramPacket received = new DatagramPacket(data, data.length);
            while(true) {

                socket.receive(received);
                String output = new String(received.getData());


                interpret(output);



            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method interprets received messages and calls the appropriate method for updating
     * @param message The message that was received by the socket
     */
    public void interpret(String message) {
        if (message.contains("Update_want_to_sell")) {
            ui.refreshWTS();

        } else if (message.contains("Update_want_to_buy")) {
            ui.refreshWTB();

        }
    }

    /**
     * This method gets the port of the current client object
     * @return The port of the current client
     */
    public static int getPort(){
        return port;
    }

    /**
     * This method overrides the run method from Runnable to allow for multiple threads.
     */
    @Override
    public void run() {
        this.waitForPackets();
    }
}
