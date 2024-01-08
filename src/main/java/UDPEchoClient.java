//package udp_echo
// ;

import java.net.*;
import java.util.Scanner;
// or, for 2 below, java.util.Datagtam.*;
//import.java.util.Datagram.Socket;
//import.java.util.Datagram.Packet;
//import.java.net.InetAddress; // covered above by net.*;


// client must know where the server is
// what info does the user need to pass to the client?
// ----where (IP address and port) to send the payload

public class UDPEchoClient {	
    public static void main(String[] args) {
		
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
		
        try {
            // create UDP socket
            socket = new DatagramSocket();

            // server address and port - I used my laptop IP and server 9876
			InetAddress serverAddress = InetAddress.getByName("68.50.123.68"); 
			
            int serverPort = 9876;  // Use same port as the server

            // read message from the user
			System.out.print("Enter a message: ");
			String message = scanner.nextLine();

            // convert message to bytes
            byte[] sendData = message.getBytes();

            // create UDP packet to send to server
			DatagramPacket sendPacket = new DatagramPacket(sendData, 
				sendData.length, serverAddress, serverPort);

            // send the packet to server
			socket.send(sendPacket);

            // receive echoed message from server			
            byte[] receiveData = new byte[1024];
			
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			
			socket.receive(receivePacket);
			
			// extract and print echoed message			
			String echoedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Echoed message from server: " + echoedMessage);
        
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
        scanner.close();
    }
}
