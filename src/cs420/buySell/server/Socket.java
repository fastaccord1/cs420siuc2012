package cs420.buySell.server;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

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
    private final int PORT = 25000;

    public Socket() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
            UnrecoverableKeyException {
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("keystore.ks"), null);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, null);


    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
