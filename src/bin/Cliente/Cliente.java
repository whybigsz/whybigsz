package bin.Cliente;

import bin.Servidor.ServerInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Cliente {
/*

    private ArrayList<String> endIPS;
    private ArrayList<String> portos;
*/


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DatagramSocket ds = new DatagramSocket();

        ds.setBroadcast(true);

        String pedido = " ";

        DatagramPacket dp = new DatagramPacket(pedido.getBytes(), pedido.length(), InetAddress.getLocalHost(), 4005);
        ds.send(dp);
        while (true) {
            dp = new DatagramPacket(new byte[256], 256);
            ds.receive(dp);
            System.out.println("Aqui");
            ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ServerInfo si = (ServerInfo) ois.readObject();
            System.out.println(si);
        }


//        InetAddress ipAddress = InetAddress.getByName("239.39.39.39");
//        Socket connectionSocket = new Socket(ipAddress,4004);
//
//
//
//        InetAddress ipGroup = InetAddress.getByName("239.39.39.39");
//
//        byte[] receiveData = new byte[256];
//        DatagramPacket dp = new DatagramPacket("".getBytes(),
//                "".length(),ipGroup,4004);
//        ds.send(dp);
//        dp = new DatagramPacket(receiveData,receiveData.length);
//        ds.receive(dp);
//        String msg = new String(dp.getData(),0,dp.getLength());
//        System.out.println(msg);

    }
}
