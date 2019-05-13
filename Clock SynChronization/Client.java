
//IMPLEMENTATION OF BERKERLY ALGORITHM
//CLIENT SIDE OF JAVA SOCKET


//import packages
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.*;

public class Client {
	  public final static int SOCKET_PORT = 13267;//Socket number
	  public final static String SERVER = "127.0.0.1"; // localhost
	    
	                                                            // different name because i don't want to
	                                                            // overwrite the one used by server...
	    
	                                                            // different name because i don't want to
	                                                            // overwrite the one used by server...

public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub



  FileOutputStream fos = null;
  BufferedOutputStream bos = null;
  Socket sock = null; 

	try {

		  //Socket connection
	      sock = new Socket(SERVER, SOCKET_PORT);//opening a socket
	      System.out.println("Connecting...");//shows it is connecting
	       
	      
	      //Function to get client date
	      Date currentDate = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		  String i = sdf.format(currentDate);
		  
          //Print Client time
		  System.out.println("Your time is:"); 
          System.out.println(i); 		  
		  
		  //Send Client time to Server
          PrintStream p5 = new PrintStream(sock.getOutputStream());// create printsream object(also import)
		  p5.println(i);// use object to pass string to server
		  
		  
		  //Get new time from server
		  
		  //Get hour
          Scanner sc = new Scanner (sock.getInputStream());//define scanner variable 
          String shr = sc.nextLine();
          int nhr = Integer.parseInt(shr);
          
         //Get Minutes
          String smin = sc.nextLine();
          int nmin = Integer.parseInt(smin);
          
          
          //Get Seconds
          String ssec = sc.nextLine();
          int nsec = Integer.parseInt(ssec);

          
          //Print time acquired from server
          System.out.println("New time is(after server calculation):"); 
          System.out.println(nhr +":" + nmin + ":" + nsec); 
          
	 }
  finally {//close all connections and socket
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
     
  }




	}


}
