package cs420.buySell.server;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Socket.java
 */
public class Socket implements Runnable {

    private SSLServerSocketFactory socketFactory;
    private SSLServerSocket serverSocket;
    private SSLSocket socket;
    private KeyStore keyStore;
    private KeyManager[] keyManagers;
    private SSLContext sslContext;
    private SSLSession sslSession;
    private final int PORT = 25000;

    public Socket() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException, KeyManagementException {
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("keystore.ks"), null);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, null);

        sslContext = SSLContext.getInstance("SSLv3");

        keyManagers = keyManagerFactory.getKeyManagers();

        sslContext.init(keyManagers, null, null);

        socketFactory = sslContext.getServerSocketFactory();
        serverSocket = (SSLServerSocket)socketFactory.createServerSocket(PORT);

    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
