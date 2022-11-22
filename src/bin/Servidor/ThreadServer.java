package bin.Servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;

public class ThreadServer implements Runnable{

    private int Port;
    private String ip;
    private ServerList list;

    public ThreadServer(int port, String ip, ServerList list) {
        Port = port;
        this.ip = ip;
        this.list = list;
    }

    @Override
    public void run() {
        try {
        InetAddress ipServer = InetAddress.getByName("127.0.0.1");

        ServerInfo si = new ServerInfo(ipServer.getHostAddress(), Port);
        list.addServer(si);


            MulticastSocket ms = new MulticastSocket(Port);
            //Adiciona o servidor á lista de servers ativos!
            //Aguarda por contactos de clientes no porto de Escuta UDP

            AguardaContactoClientes acc = new AguardaContactoClientes(ms, list);
            Thread t = new Thread(acc);
            t.start();
            //Aguarda continuamente pela receção de datagramas UDP enviados para
            // o porto 4004 e endereço de multicast 239.39.39.39
//            int msPort = 4004;
//            String msEndereco = "239.39.39.39";
//             MulticastSocket ms = new MulticastSocket(msPort);
//             ms.setBroadcast(true);
            //Tambem aguarda continuamente por pedidos de ligaçao TCP num porto Automatico (0)
//            ServerSocket ss = new ServerSocket(0);

//            AtendeClientes ac = new AtendeClientes(ms, ss);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
