package bin.Servidor;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.ServerSocket;

public class RecebeHeardBeats extends Thread{
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
        } catch (Exception e) {

        }
    }
}
