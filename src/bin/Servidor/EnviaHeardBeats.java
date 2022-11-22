package bin.Servidor;

import java.net.*;

public class EnviaHeardBeats extends Thread{
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

        }
    }
}

