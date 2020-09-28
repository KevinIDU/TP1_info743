package tp1info743;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        //Use scanner to let the client write his message
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("exit")) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 1600);
        	 //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(s);
            System.out.println("Sending request to Socket Server");
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            System.out.println("Voulez vous continuer ? si oui tapez votre message sinon tapez exit");
            sc = new Scanner(System.in);
            s = sc.nextLine();
        }
        //close resources
        ois.close();
        oos.close();
        socket.close();
        Thread.sleep(100);
       
    }
	
	

}
