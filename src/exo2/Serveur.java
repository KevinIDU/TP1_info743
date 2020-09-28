package exo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Serveur {

	
	//static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 1600;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        System.out.println("Choosing a number ... ");
        Random random = new Random();
        int nb = random.nextInt(101);
        System.out.println("Okay the number is : "+nb);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){

            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            int message = (int) ois.readObject();
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            //close resources

            //terminate the server if client sends exit request
            
           if(message == nb){
        	   oos.writeObject("Vous avez trouvez le nombre Mystere (=) ! il s'agissait de "+nb);
               //close resources
               ois.close();
               oos.close();
               socket.close();
           }else if(message > nb){
        	   oos.writeObject("Ce n'est pas le nombre mystère ! c'est - ");
               //close resources
               ois.close();
               oos.close();
               socket.close();
           }else if(message < nb){
        	   oos.writeObject("Ce n'est pas le nombre mystère ! c'est + ");
               //close resources
               ois.close();
               oos.close();
               socket.close();
           }else if(message == 200) {
        	   System.out.println("Le jeu est fini hehe");
        	   break;
           }
            
        }
        
       
    }
    
    
    
	
}
