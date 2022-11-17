package bin.Servidor;

import java.io.Serializable;
import java.net.InetAddress;

public class ServerInfo  implements Serializable{

    private String ip;
    private int Port;


    public ServerInfo(String ip, int port) {
        this.ip = ip;
        this.Port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return Port;
    }

    @Override
    public String toString() {
        return "ServerInfo: " +
                "IP: " + ip +
                ", Port= " + Port +
                '}';
    }
}

