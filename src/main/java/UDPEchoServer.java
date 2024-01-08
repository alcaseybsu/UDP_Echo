import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create UDP socket, bind to port
            socket = new DatagramSocket(9876);

            System.out.println("UDP Echo Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive UDP packet from client
                socket.receive(receivePacket);

                // Get client's address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Extract message from received packet
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Echo message back to client
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
