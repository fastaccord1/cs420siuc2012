package cs420.buySell.server;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

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

    public void sendMessage(String Message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(Message);
    }

    public void waitForMessage() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()
                ));

    }

    @Override
    public void run(){

        socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
            @Override
            public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
                while(true) {
                    try {
                        waitForMessage();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        });

    }

    public InetAddress getClientAddress() {
        return socket.getInetAddress();
    }

}
