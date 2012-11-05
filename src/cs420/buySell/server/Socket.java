package cs420.buySell.server;

import javax.net.ssl.*;
import java.security.KeyStore;

/**
 * Socket.java
 */
public class Socket implements Runnable {

    private SSLServerSocketFactory socketFactory;
    private SSLServerSocket serverSocket;
    private SSLSocket socket;
    private KeyStore keyStore;
    private KeyManager keyManager;
    private SSLContext sslContext;
    private SSLSession sslSession;

    public Socket() {

    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
