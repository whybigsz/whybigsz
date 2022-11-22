package bin.Cliente;

import bin.Servidor.ServerInfo;
import bin.Servidor.ServerList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        int Port = 4001;
        DatagramSocket ds = new DatagramSocket();
        ds.setBroadcast(true);

        String pedido = "Ola Quero ligar-me";
        DatagramPacket dp = new DatagramPacket(pedido.getBytes(), pedido.length(), InetAddress.getLocalHost(), 4001);
        ds.send(dp);
        while (true) {
            dp = new DatagramPacket(new byte[256], 256);
            ds.receive(dp);

            ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ServerList msgRec = (ServerList) ois.readObject();

            System.out.println("Received: " + msgRec.getList() + " from "
                    + dp.getAddress().getHostAddress() + ":" + dp.getPort());
            List <ServerInfo> lst = msgRec.getList();
            int port;
            String ip;
            for(int i = 0; i < 3; i++){
                ip = lst.get(i).getIp();
                port = lst.get(i).getPort();

                System.out.println("ip: "+ ip + ", port: "+ port);
            }
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

//    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
//        ByteArrayInputStream in = new ByteArrayInputStream(data);
//        ObjectInputStream is = new ObjectInputStream(in);
//        return is.readObject();
//    }
}
