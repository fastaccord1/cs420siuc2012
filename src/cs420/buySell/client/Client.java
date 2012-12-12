package cs420.buySell.client;

import java.net.InetAddress;

/**
 * Client.java
 *
 * Buy_sell_exchange
 * Kevin Reuter, Jordan Martin, and Matt Troutt
 *
 * This class allows for the address and port of a client to be stored in one location
 */
public class Client {
    private InetAddress address;
    private int port;

    /**
     * Constructor that initializes all of the variables
     * @param address The address of the client
     * @param port The port of the client
     */
    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * This gets the address of the current client object
     * @return The address of the client object
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * This gets the port of the current client object
     * @return The port of the client object
     */
    public int getPort() {
        return port;
    }

    public String toString(){
        String output = "";
        output += address;
        output += ": ";
        output += port;
        return output;
    }
}
