package cs420.buySell.server;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: kreuter
 * Date: 11/15/12
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketServerThread extends Thread{
    private SSLSocket socket;
    //private InetAddress client;

    public SocketServerThread(SSLSocket socket) {

        super("MultiClientServer");
        this.socket = socket;
    }

    public void sendMessage(Object message) throws IOException {
        ObjectOutputStream objOutStream = new ObjectOutputStream(socket.getOutputStream());
        objOutStream.writeObject(message);
    }

    public void waitForMessage(Scanner input) throws IOException, ClassNotFoundException {
        ObjectInputStream objInStream = new ObjectInputStream(socket.getInputStream());
        Object inputObject = objInStream.readObject();
        if(inputObject instanceof String) {
            interpretMessage((String) inputObject);
        }
        else {
            //do something
        }


    }

    public void interpretMessage(String message) {
        if(message.equals("update_database")) {

        }
    }

    @Override
    public void run(){

        socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
            @Override
            public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
                Scanner input;
                try {
                    input = new Scanner(new InputStreamReader(socket.getInputStream()));
                    while(true) {
                        waitForMessage(input);
                    }
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        });

    }

    public InetAddress getClientAddress() {
        return socket.getInetAddress();
    }

}
