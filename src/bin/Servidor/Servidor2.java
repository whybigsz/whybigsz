package bin.Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class Servidor2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(0);
        DatagramSocket ds = new DatagramSocket();
        InetAddress ipGroup = InetAddress.getByName("239.39.39.39");
        Scanner sc = new Scanner(System.in);
        while (true){
            String msg = ss.getInetAddress().getHostAddress()+","+ss.getLocalPort();
            DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),ipGroup,4004);
            ds.send(dp);
            Thread.sleep(10000);
        }
    }
}
