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
    private static Socket socket;
    private static PrintWriter out;
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
                int index = line.indexOf(':');
                String address = line.substring(0, index - 1);
                String port = line.substring(index + 1);
                int portNum = Integer.parseInt(port);
                Client client = new Client(InetAddress.getByName(address), portNum);

            }
        }

    }

    public static void getList(){
        String output = "get_list";
        out.println(output);
        out.flush();

    }

    public static void main(String[] args){
        try {
            Socket socket = new Socket(InetAddress.getByName("192.168.1."), 25001);
            ClientServerComm comm = new ClientServerComm(socket);
            Thread t = new Thread(comm);
            t.start();
            ClientServerComm.getList();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
