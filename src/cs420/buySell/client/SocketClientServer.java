package cs420.buySell.client;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetAddress;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * SocketClientServer.java
 *
 * This class handles communication between the client and the server on the Client side.
 */
public class SocketClientServer extends Thread {

    private SSLSocket socket;

    public SocketClientServer(InetAddress serverAddress, int port) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException,
            KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("keystore.ks"), null);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, null);

        SSLContext sslContext = SSLContext.getInstance("SSLv3");

        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

        sslContext.init(keyManagers, null, null);

        SSLSocketFactory socketFactory = sslContext.getSocketFactory();

        socket = (SSLSocket) socketFactory.createSocket(serverAddress, port);

        socket.startHandshake();

    }

    public void sendMessage(Object message) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
    }

    public void waitForMessage() throws ClassNotFoundException, IOException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Object message = in.readObject();

        if(message instanceof String) {
            interpret((String)message);
        }
    }

    public void interpret(String message) {

        if(message.equals("updated_info")) {
            System.out.println("Updated info coming in");
        }

    }

    @Override
    public void run() {
        while(true) {
            try {
                waitForMessage();
            } catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
