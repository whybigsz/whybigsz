package bin.Servidor;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Servidor2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ipGroup = InetAddress.getByName("239.39.39.39");
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Insira mensagem");
            String msg = sc.nextLine();
            DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),ipGroup,4004);
            ds.send(dp);
        }
    }

}
