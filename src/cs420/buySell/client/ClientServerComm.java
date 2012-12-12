package cs420.buySell.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

/**
 * ClientServerComm.java
 *
 * Buy_sell_exchange
 * Kevin Reuter, Jordan Martin, and Matt Troutt
 *
 * This class handles the connection to the server to send and receive a list of connected clients.
 */
public class ClientServerComm implements Runnable{
    private static Socket socket;
    private static PrintWriter out;
    private BufferedReader in;
    private LinkedList<Client> clients;

    /**
     * Constructor that sets up the socket to handle the communication between client and server
     */
    public ClientServerComm(){
        try{
            //Change the address
            socket = new Socket(InetAddress.getByName(""), 25001);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients = new LinkedList<Client>();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method overrides run within Runnable to handle different threads.
     *
     * It waits for incoming messages and then passes them to an interpreter method for parsing.
     */
    @Override
    public void run(){
        try{


            String line;
            while((line = in.readLine()) != null){
                interpret(line);
            }

        } catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method interprets the incoming messages and calls the appropriate method based on the received message.
     * @param line The incoming message received by the socket
     * @throws IOException Throws this if there is an error with reading in the list of clients
     */
    public void interpret(String line) throws IOException {
        if(line.equals("send_port")) {
            out.println(ClientSocket.getPort());
            out.flush();
        }
        else if(line.equals("list_incoming")){
            clients.clear();
            while(!(line = in.readLine()).equals("end_of_list")){

                int index = line.indexOf(':');
                String address = line.substring(1, index);
                String port = line.substring(index + 2);
                int portNum = Integer.parseInt(port);
                Client client = new Client(InetAddress.getByName(address), portNum);
                clients.add(client);

            }
        }

    }

    /**
     * This method sends a message to the server telling it that the client wants the list of connected clients.
     */
    public void sendGetList(){
        String output = "get_list";
        out.println(output);
        out.flush();

    }

    /**
     * This getter method returns the current list of connected clients
     * @return The current list of connected clients.
     */
    public LinkedList<Client> getClientList(){
        if(!clients.isEmpty())
            return clients;

        return null;
    }

}
