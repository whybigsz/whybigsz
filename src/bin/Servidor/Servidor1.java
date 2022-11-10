package bin.Servidor;

import java.net.MulticastSocket;

public class Servidor1 {
    public static void main(String[] args) {
        MulticastSocket ms = new MulticastSocket(4004);
    }
}
