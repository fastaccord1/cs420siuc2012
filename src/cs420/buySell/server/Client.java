package cs420.buySell.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12/11/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args){
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 25001);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String output;
            while(true){
                System.out.println("Please input your message");
                output = input.nextLine();
                if(output.equals("quit"))
                    break;
                out.println(output);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
