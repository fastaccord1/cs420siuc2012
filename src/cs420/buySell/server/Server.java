package cs420.buySell.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12/11/12
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */

public class Server {

    //private Socket socket;
    private static ServerSocket serverSocket;

    private static int port;
    private static final int MAX_CONNECTIONS = 100;
    private static LinkedList<InetAddress> clients;

    public static void main(String[] args) {
        clients = new LinkedList<InetAddress>();
        try{
            port = 25001;
            serverSocket = new ServerSocket(port);

            Socket server;
            int connections = 0;
            while(connections++ < MAX_CONNECTIONS || MAX_CONNECTIONS == 0){
                Communication comm;

                server = serverSocket.accept();
                clients.add(server.getInetAddress());
                comm = new Communication(server, clients);
                Thread t = new Thread(comm);
                t.start();

            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }




}

class Communication implements Runnable{
    private Socket socket;
    private String line, input;
    private LinkedList<InetAddress> clients;

    Communication(Socket socket, LinkedList<InetAddress> clients){
        this.socket = socket;
        this.clients = clients;

    }

    public void run(){
        input = "";
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());

            while((line = in.readLine()) != null){
                System.out.println(line);
                for (InetAddress client : clients){
                    System.out.println(client.getHostAddress());
                }
                //input=input+line;
                //out.println("I got: " + line);
            }
            System.out.println("Total message: " + input);
            clients.remove(clients.indexOf(socket.getInetAddress()));


        } catch(IOException e){
            e.printStackTrace();
        }

    }


}
