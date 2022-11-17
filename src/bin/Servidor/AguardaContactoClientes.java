package bin.Servidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class AguardaContactoClientes extends Thread{

    MulticastSocket ms;
    ServerList lst;

    public AguardaContactoClientes(MulticastSocket ms, ServerList lst){
        this.ms = ms;
        this.lst = lst;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dpRec = new DatagramPacket(new byte[256], 256);
                ms.receive(dpRec);
                String msgRec =new String(dpRec.getData(), 0 , dpRec.getLength());
                System.out.println("Recebi a msg: " + msgRec + "de "+dpRec.getAddress().getHostAddress() + ":" + dpRec.getPort());


                //Serialização
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(lst);
                byte[] bytes = baos.toByteArray();

                DatagramPacket dpSend = new DatagramPacket(bytes, bytes.length,
                        InetAddress.getByName(dpRec.getAddress().getHostAddress()), dpRec.getPort());
                ds.send(dpSend);


            } catch (IOException e) {
                System.out.println("Exceção ocorrida AtendeContactoClientes(run)");
                throw new RuntimeException(e);
            }
        }

    }
}
