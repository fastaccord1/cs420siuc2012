package cs420.buySell.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

/**
 * ClientSocket.java
 *
 * This class will handle communication between the different clients. It will send the update messages
 * when necessary and call an update method when a message is received.
 */
public class ClientSocket implements Runnable{

    private static DatagramSocket socket;

    /**
     * Constructor method that creates the Datagram socket to send and receive messages.
     */
    public ClientSocket() {
        try {
            int port = 25001;
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public static void sendData(String sendMessage, InetAddress client, int port) {
        try {
            byte[] data = sendMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(data, data.length, client, port);
            socket.send(sendPacket);


        } catch(IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void waitForPackets() {
        try{
            while(true) {
                    byte[] data = new byte[1024];
                    DatagramPacket received = new DatagramPacket(data, data.length);


                    socket.receive(received);
                    String output = new String(received.getData());

                    interpret(output);



            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendUpdateBuy(List<Client> clients) {
        for(Client client : clients) {
            InetAddress address = client.getAddress();
            int port = client.getPort();

            String output = "Update_want_to_buy";

            sendData(output, address, port);
        }
    }

    public static void sendUpdateSell(List<Client> clients) {
        for(Client client : clients) {
            InetAddress address = client.getAddress();
            int port = client.getPort();

            String output = "Update_want_to_sell";

            sendData(output, address, port);
        }
    }

    public void interpret(String message) {
        if (message.equals("Update_want_to_sell")) {
            System.out.println("Sell update");

        } else if (message.equals("Update_want_to_buy")) {
            System.out.println("buy update");

        }
    }


    @Override
    public void run() {
        this.waitForPackets();
    }
}
