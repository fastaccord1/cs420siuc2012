package cs420.buySell.client;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: kreuter
 * Date: 11/30/12
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    private InetAddress address;
    private int port;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
