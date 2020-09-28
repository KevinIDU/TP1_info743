package exo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean pasFini = true;
        Random random = new Random();
        int nb = random.nextInt(101);
        //Use scanner to let the client write his message
        while (pasFini) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 1600);
        	 //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(nb);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            if (message.contains("=")) {
            	pasFini = false;
            	System.out.println("Le jeu est terminé");
            	break;
            }else if(message.contains("+")) {
            	nb = nb+random.nextInt(101-nb);
            	System.out.println("nombre choisi pour le nouvel essai: "+nb);
            }else if(message.contains("-")) {
            	nb = 0+random.nextInt(nb-0);
            	System.out.println("nombre choisi pour le nouvel essai: "+nb);
            }
        }
        //close resources
        ois.close();
        oos.close();
        socket.close();
        Thread.sleep(100);
       
    }
	
	

}
