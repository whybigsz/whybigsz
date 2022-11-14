package bin.Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Servidor1 {
    public static void main(String[] args) throws IOException {
        MulticastSocket ms = new MulticastSocket(4004);


        while (true){
            byte[] recData = new byte[256];
            DatagramPacket dp = new DatagramPacket(recData,recData.length);
            ms.receive(dp);
            String msg = new String(dp.getData(),0,dp.getLength());
            System.out.println(msg);
        }

    }
}
