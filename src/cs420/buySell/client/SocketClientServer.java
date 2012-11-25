package cs420.buySell.client;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created with IntelliJ IDEA.
 * User: kreuter
 * Date: 11/15/12
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocketClientServer {

    private SSLSocket socket;
    private SSLSocketFactory socketFactory;
    private KeyStore keyStore;
    private KeyManager[] keyManagers;
    private SSLContext sslContext;

    public SocketClientServer(InetAddress serverAddress, int port) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException,
            KeyManagementException {

        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream("keystore.ks"), null);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, null);

        sslContext = SSLContext.getInstance("SSLv3");

        keyManagers = keyManagerFactory.getKeyManagers();

        sslContext.init(keyManagers, null, null);

        socketFactory = sslContext.getSocketFactory();

        socket = (SSLSocket)socketFactory.createSocket(serverAddress, port);

        socket.startHandshake();

    }
}
