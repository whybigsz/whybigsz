package bin.Servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Inicio do Servidor
        //1-Receber nos Argumentos um Porto de escuta onde aguarda por contactos de clientes
        //2-E o caminho da diretoria de armazenamento da BASE DE DADOS
        ThreadGroup parent = new ThreadGroup("Servidores");
        ArrayList<Thread> allServers = new ArrayList<>();
        List<ServerInfo> listServers = new ArrayList<>();
        ServerList sl = new ServerList(listServers);
        Thread t1 = new Thread ( parent, new ThreadServer(Integer.parseInt(args[0]), args[1], sl) );
        Thread t = new Thread(t1);
        t.start();
        allServers.add(t);

        for(Thread th : allServers)
            t.join();

    }

//    public static byte[] serialize(Object obj) throws IOException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(out);
//        os.writeObject(obj);
//        return out.toByteArray();
//    }


    void startServer(int Port, String caminho, ServerList sl) throws IOException {

        InetAddress ipServer = InetAddress.getByName("127.0.0.1");

        ServerInfo si = new ServerInfo(ipServer.getHostAddress(), Port);
        sl.addServer(si);

        try {
            MulticastSocket ms = new MulticastSocket(Port);
            //Adiciona o servidor á lista de servers ativos!
            //Aguarda por contactos de clientes no porto de Escuta UDP
            AguardaContactoClientes acc = new AguardaContactoClientes(ms, sl);
            acc.start();
            //Aguarda continuamente pela receção de datagramas UDP enviados para
            // o porto 4004 e endereço de multicast 239.39.39.39
            int msPort = 4004;
            String msEndereco = "239.39.39.39";
//             MulticastSocket ms = new MulticastSocket(msPort);
//             ms.setBroadcast(true);
            //Tambem aguarda continuamente por pedidos de ligaçao TCP num porto Automatico (0)
            ServerSocket ss = new ServerSocket(0);

            AtendeClientes ac = new AtendeClientes(ms, ss);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        AtendeClientes ac = new AtendeClientes(ds, ss, allServers);
//        ac.start();


        //Depois da fase de Arranque, um servidor envia, a cada 10s, uma mensagem de heartbeat
        //para o porto 4004 do endereço de multicast 239.39.39.39


//
//        DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));
//        ds.setBroadcast(true);
//        ArrayList<ServerInfo> allServers = new ArrayList<>();
//        InetAddress ipGroup = InetAddress.getByName("239.39.39.39");
//        SocketAddress sa = new InetSocketAddress(ipGroup, 4004);
//        NetworkInterface ni = NetworkInterface.getByName("wlp10s0f0");
//        ms.joinGroup(sa, ni);
//        ServerInfo si = new ServerInfo(String.valueOf(ss.getInetAddress()), String.valueOf(ss.getLocalPort()));
//        RecebeHeardBeats rhb = new RecebeHeardBeats(ms, ss);
//        EnviaHeardBeats ehb = new EnviaHeardBeats(ms, ss);
//        rhb.start();
//        ehb.start();
//        allServers.add(si);
//        System.out.println(allServers);
    }

}
