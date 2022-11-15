package bin.Servidor;

import java.io.Serializable;

public class ServerInfo implements Serializable {

    String ip,port;

    public ServerInfo(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

   /* public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }
*/
    @Override
    public String toString() {
        return
                "ip: " + ip + " port: " + port;
    }
}
