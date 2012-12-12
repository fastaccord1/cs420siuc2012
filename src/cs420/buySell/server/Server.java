package cs420.buySell.server;

import cs420.buySell.client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Server.java
 *
 * Buy_sell_exchange
 * Kevin Reuter, Jordan Martin, and Matt Troutt
 * This class handles the client-server communication on the server side
 */

public class Server {

    private static final int MAX_CONNECTIONS = 100;

    /**
     * Main method that starts the server and handles starts a thread for each incoming connection.
     * @param args Command line arguments(there are none).
     */
    public static void main(String[] args) {
        LinkedList<Client> clients = new LinkedList<Client>();
        try{
            int port = 25001;
            ServerSocket serverSocket = new ServerSocket(port);

            Socket server;
            int connections = 0;
            while(connections++ < MAX_CONNECTIONS){
                Communication comm;

                server = serverSocket.accept();

                comm = new Communication(server, clients);
                Thread t = new Thread(comm);
                t.start();

            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}

/**
 * This class actually contains the socket code for the server
 */
class Communication implements Runnable{
    private Socket socket;
    private LinkedList<Client> clients;

    /**
     * Constructor that initializes the variables for Communication class
     * @param socket The socket object created by the main method
     * @param clients A LinkedList of connected clients
     */
    Communication(Socket socket, LinkedList<Client> clients){
        this.socket = socket;
        this.clients = clients;

    }

    /**
     * This method overrides the run method from Runnable to allow for multiple threads to be run.
     * It waits for incoming messages and sends those messages to an interpreter method for processing.
     */
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());

            String output = "send_port";
            out.println(output);
            out.flush();
            String input = in.readLine();

            int port = Integer.parseInt(input);
            Client client = new Client(socket.getInetAddress(), port);
            clients.add(client);

            String line;
            while((line = in.readLine()) != null){
                interpret(line);
            }
            clients.remove(clients.indexOf(client));


        } catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Interpreter method that will interpret the message and call the appropriate method
     * @param input    The message to be interpreted.
     * @throws IOException Throws this exception if sendList throws the exception.
     */
    public void interpret(String input) throws IOException {
        if(input.equals("get_list")){
            sendList();
        }

    }

    /**
     * This method sends the list of connected clients to the client that requested it.
     * @throws IOException Throws this if PrintWriter cannot be initialized.
     */
    public void sendList() throws IOException {
        String output = "list_incoming";
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(output);
        out.flush();
        for(Client client : clients){
            out.println(client);
            out.flush();
        }
        out.println("end_of_list");
        out.flush();



    }


}
