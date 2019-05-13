

//IMPLEMENTATION OF BERKERLY ALGORITHM
//SERVER SIDE OF JAVA SOCKET

//import packages
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Servers {
	public final static int SOCKET_PORT = 13267;//Socket number
	 public static void main (String [] args ) throws IOException {
		    
		   
		    
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    ServerSocket servsock = null;
		    Socket sock = null;
		    try {
		      //Socket Connection
		    	servsock = new ServerSocket(SOCKET_PORT);
		      
		        
		        while(true) {
		        System.out.println("Waiting...");//Server waiting for client connection	
		        try {
			  	      
		        	  //Get server time
		        	  Date currentDate = new Date();
					  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					  String j = sdf.format(currentDate);

		        	sock = servsock.accept();//method to allow socket to listen to connection
		            System.out.println("Accepted connection : " + sock);//shows connection is successful
					  System.out.println("Server time is:");
					  System.out.println(j);
		            //read data input

		            
		            //get client time from client via socket
		            Scanner sc = new Scanner (sock.getInputStream());//define scanner variable 
		            String i = sc.nextLine();
		            System.out.println("Client time is:");
					System.out.println(i);
		            
					//Get hours from time strings
		        	String k1 = j.substring(0,2);
		            int y1 =Integer.parseInt(k1);
		        	
		            //Get minute from time strings
		        	String k2 = j.substring(3,5);
		            int y2 =Integer.parseInt(k2);
                   
		            //Get seconds from time string
		        	String k3 = j.substring(6,8);
		            int y3 =Integer.parseInt(k3);

		          //Get hours from time strings
		        	String k4 = i.substring(0,2);
		            int y4 =Integer.parseInt(k4);

		          //Get minute from time strings
		        	String k5 = i.substring(3,5);
		            int y5 =Integer.parseInt(k5);

                    
		          //Get seconds from time string
		        	String k6 = i.substring(6,8);
		            int y6 =Integer.parseInt(k6);

		            //Calculate offset
		            if(y6<y3)
		            {
		            	y6=y6+60;
		            	y5--;
		            	
		            }
		            if(y5<y2) {
		            	y5 = y5 +60;
		            	y4--;
	                  
		            }
		            int ofhr = y4 - y1;//hour offset
		            int ofmin  = y5 - y2;//minute offset
		            int ofsec = y6 -y3;//second offset
		            
		            
		            //calculate average offset time
		            int avoh = ofhr /2;
		            int avom = ofmin/2;
		            int avos = ofsec/2;
		            
		            
		          //calculate new time
		            int nhr =  y1 +avoh ;//new hour
		            int nmin = y2 + avom ;//new minute
		            int nsec = y3 + avos;//new second
		            
		            
                      if(nsec>60)
		            {
		            	nsec= nsec-60;
		            	nmin++;
		            	
		            }
		            if(nmin>60) {
		            	nmin = nmin-60;
		            	nhr++;
	                  
		            }

		            //print new time in server 
		          System.out.println("\nThe  new calculated time is :");  
		          System.out.println(nhr +":" + nmin + ":" + nsec);  
		          
		          //send time to client
		          PrintStream p2 = new PrintStream(sock.getOutputStream()); //create printsream object(also import)
				  p2.println(nhr);// use object to pass string to client  
		          
                  p2.println(nmin);// use object to pass string to client  
				  
				  p2.println(nsec);// use object to pass string to client  
		            
		            
		            
		        }
		        
		       
		          finally {
		           //Closing all the connections
		        	if (bis != null) bis.close();
		            if (os != null) os.close();
		            if (sock!=null) sock.close();
		          }
		        
		      }}
		      finally {
		          // close server socket
		    	  if (servsock != null) servsock.close();
		      }
		    }
	 }
