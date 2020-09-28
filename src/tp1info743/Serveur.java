package tp1info743;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serveur {

	
	//static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 1600;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            //close resources

            //terminate the server if client sends exit request
            
            if(message.equalsIgnoreCase("exit")) {
           	 System.out.println("Shutting down Socket server!!");
                //close the ServerSocket object
           	 socket.close();
                server.close();
           }else if(message.equalsIgnoreCase("MOON")){
        	   oos.writeObject("I don't know");
               //close resources
               ois.close();
               oos.close();
               socket.close();
           }else if(message.equalsIgnoreCase("DATE")){
        	   DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	   Date date = new Date();
        	   oos.writeObject("date : "+format.format(date));
               //close resources
               ois.close();
               oos.close();
               socket.close();
           }else if(message.equalsIgnoreCase("HOUR")){
        	   DateFormat format = new SimpleDateFormat("HH:mm:ss");
        	   Date date = new Date();
        	   oos.writeObject("Heure : "+format.format(date));
               //close resources
               ois.close();
               oos.close();
           }
            
            if(message.equalsIgnoreCase("exit")) {
            	 System.out.println("Shutting down Socket server!!");
                 //close the ServerSocket object
                 ois.close();
                 oos.close();
                 socket.close();
            	 socket.close();
                 server.close();
            }
        }
       
    }
    
    
    
	
}
