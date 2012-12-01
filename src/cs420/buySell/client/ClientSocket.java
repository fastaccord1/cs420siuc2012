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

    private DatagramSocket socket;
    private final int PORT = 25001;

    /**
     * Constructor method that creates the Datagram socket to send and receive messages.
     */
    public ClientSocket() {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void sendData(String sendMessage, InetAddress client, int port) {
        try {
            byte[] data = sendMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(data, data.length, client, port);
            socket.send(sendPacket);


        } catch(IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void waitForPackets() {
        while(true) {
            try{
                byte[] data = new byte[1024];
                DatagramPacket received = new DatagramPacket(data, data.length);


                socket.receive(received);
                String output = new String(received.getData());

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


        }

    }



    @Override
    public void run() {
        this.waitForPackets();
    }
}
