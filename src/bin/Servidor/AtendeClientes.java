package bin.Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;



public class AtendeClientes extends Thread{
    DatagramSocket ds;
    ServerSocket ss;
    ArrayList<ServerInfo> siList;

    public AtendeClientes(DatagramSocket ds, ServerSocket ss, ArrayList<ServerInfo> siList) {
        this.ds = ds;
        this.ss = ss;
        this.siList = siList;
    }

    public AtendeClientes(DatagramSocket ds, ServerSocket ss){
        this.ds = ds;
        this.ss = ss;
    }
    @Override
    public void run() {
//        try {
//            byte[] MAX_DATA;
//            InetAddress addr;
//            int port;
//            while (true) {
//                DatagramPacket dpRec = new DatagramPacket(new byte[256], 256);
//                ds.receive(dpRec);
//                String pedido = new String(dpRec.getData(), 0, dpRec.getLength());
//                if (pedido.equals(" ")) {
//                    addr = dpRec.getAddress();
//                    port = dpRec.getPort();
//                    break;
//                }
//            }
//            MAX_DATA = serialize(siList);
//            DatagramPacket dpSend = new DatagramPacket(MAX_DATA, MAX_DATA.length, addr, port);
//            ds.send(dpSend);
//            System.out.println("server");
//
////                    Socket connectionSocket = ss.accept();
////                    ObjectOutputStream oos = new ObjectOutputStream(connectionSocket.getOutputStream());
////                    oos.writeObject(siList);
//        } catch (
//                Exception e) {
//
//        }
            while (true) {
                try {
                    Socket socket = ss.accept();
                    System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

                    String msg= "Ola";
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(msg);
                    System.out.println("Enviei!");
                } catch (IOException e) {
                    System.out.println("Exceção ocorrida AtendeClientes(run)");
                    throw new RuntimeException(e);
                }
            }

    }
}

