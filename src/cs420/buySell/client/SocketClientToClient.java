package cs420.buySell.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: kreuter
 * Date: 11/15/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketClientToClient implements Runnable{

    private DatagramSocket socket;
    private final int PORT = 25001;

    public SocketClientToClient() {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void sendData()

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
