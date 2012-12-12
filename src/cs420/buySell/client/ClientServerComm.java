package cs420.buySell.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12/11/12
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientServerComm implements Runnable{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientServerComm(Socket socket){
        try{
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

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

    public void interpret(String line) throws IOException {
        if(line.equals("send_port")) {
            out.println(ClientSocket.getPort());
            out.flush();
        }
        else if(line.equals("list_incoming")){
            while(!(line = in.readLine()).equals("end_of_list")){

            }
        }

    }
}
