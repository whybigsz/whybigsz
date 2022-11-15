package bin.Servidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
//    private ArrayList<String> endIPS;
//    private ArrayList<String> portos;

    public static void main(String[] args) throws IOException {
        Servidor s = new Servidor();
        s.startServer();

    }

    void startServer() throws IOException {
        MulticastSocket ms = new MulticastSocket(4004);
        DatagramSocket ds = new DatagramSocket(4005);
        ds.setBroadcast(true);
        ServerSocket ss = new ServerSocket(0);
        ArrayList<ServerInfo> allServers = new ArrayList<>();
        InetAddress ipGroup = InetAddress.getByName("239.39.39.39");
        SocketAddress sa = new InetSocketAddress(ipGroup, 4004);
        NetworkInterface ni = NetworkInterface.getByName("wlp10s0f0");
        ms.joinGroup(sa, ni);
        ServerInfo si = new ServerInfo(String.valueOf(ss.getInetAddress()), String.valueOf(ss.getLocalPort()));
        RecebeHeardBeats rhb = new RecebeHeardBeats(ms, ss);
        EnviaHeardBeats ehb = new EnviaHeardBeats(ms, ss);
        rhb.start();
        ehb.start();
        allServers.add(si);
        AtendeClientes ac = new AtendeClientes(ds, ss, allServers);
        ac.start();
    }


    public class AtendeClientes extends Thread {
        DatagramSocket ds;

        ServerSocket ss;
        ArrayList<ServerInfo> siList;

        public AtendeClientes(DatagramSocket ds, ServerSocket ss, ArrayList<ServerInfo> siList) {
            this.ds = ds;
            this.ss = ss;
            this.siList = siList;
        }

        @Override
        public void run() {
            try {
                byte[] MAX_DATA;
                InetAddress addr;
                while (true) {
                    DatagramPacket dpRec = new DatagramPacket(new byte[256], 256);
                    ds.receive(dpRec);
                    String pedido = new String(dpRec.getData(), 0, dpRec.getLength());
                    if (pedido.equals(" ")) {
                        addr = dpRec.getAddress();
                        System.out.println(addr);
                        break;
                    }
                }

                for (ServerInfo si : siList) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(si);
                    MAX_DATA = baos.toByteArray();
                    DatagramPacket dpSend = new DatagramPacket(MAX_DATA, MAX_DATA.length, addr, 4005);
                    ds.send(dpSend);
                    System.out.println("server");
                }
//                    Socket connectionSocket = ss.accept();
//                    ObjectOutputStream oos = new ObjectOutputStream(connectionSocket.getOutputStream());
//                    oos.writeObject(siList);
            } catch (Exception e) {
                System.out.println("Exception!");
            }
        }
    }

    public class RecebeHeardBeats extends Thread {

        MulticastSocket ms;
        ServerSocket ss;

        public RecebeHeardBeats(MulticastSocket ms, ServerSocket ss) {
            this.ms = ms;
            this.ss = ss;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    DatagramPacket dp = new DatagramPacket(new byte[256], 256);
                    ms.receive(dp);
                    String rec = new String(dp.getData(), 0, dp.getLength());
                    if (Integer.parseInt(rec) != ss.getLocalPort())
                        System.out.println(rec);

                }
            } catch (IOException e) {
                System.out.println("Exceção run");
            }
        }
    }

    public class EnviaHeardBeats extends Thread {

        MulticastSocket ms;
        ServerSocket ss;
        InetAddress ipGroup;

        public EnviaHeardBeats(MulticastSocket ms, ServerSocket ss) throws UnknownHostException {
            this.ms = ms;
            this.ss = ss;
            this.ipGroup = InetAddress.getByName("239.39.39.39");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String hb = String.valueOf(ss.getLocalPort());
                    DatagramPacket dp = new DatagramPacket(hb.getBytes(), hb.length(), ipGroup, 4004);
                    ms.send(dp);
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                System.out.println("Exceção run");
            }
        }
    }

}
