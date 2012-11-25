package cs420.buySell.server;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.LinkedList;

/**
 * SocketServer.java
 */
public class SocketServer {

    private SSLServerSocketFactory socketFactory;
    private SSLServerSocket serverSocket;
    private LinkedList<SocketServerThread> socketServers;
    private KeyStore keyStore;
    private KeyManager[] keyManagers;
    private SSLContext sslContext;
    //private SSLSession sslSession;
    private final int PORT = 25000;

    public SocketServer() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException, KeyManagementException {
        socketServers = new LinkedList<SocketServerThread>();

        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("keystore.ks"), null);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, null);

        sslContext = SSLContext.getInstance("SSLv3");

        keyManagers = keyManagerFactory.getKeyManagers();

        sslContext.init(keyManagers, null, null);

        socketFactory = sslContext.getServerSocketFactory();
        serverSocket = (SSLServerSocket)socketFactory.createServerSocket(PORT);

        SocketServerThread socketServer = new SocketServerThread((SSLSocket)serverSocket.accept());
        socketServers.add(socketServer);

        socketServer.start();


    }


}
