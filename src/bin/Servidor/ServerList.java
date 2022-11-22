package bin.Servidor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerList implements Serializable {

    public List<ServerInfo> list;

    public ServerList(List lst) {
        this.list = lst;
    }

    public List<ServerInfo> getList() {
        return list;
    }

    public synchronized void addServer(ServerInfo si){
        list.add(si);
    }
}
