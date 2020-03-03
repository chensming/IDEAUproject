package network.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//�ͻ��˲�����arg[0]Ϊ 0/1 ��ʾ�ϴ�/���أ�arg[1]Ϊ�ļ���
public class Client {
    public static void main(String[] args) {
        InetAddress sAddr;
        byte[] inBuffer;
        try {
            DatagramSocket dSocket = new DatagramSocket();
            String s;
            int c;
            sAddr = InetAddress.getByName("127.0.0.1");
            if (args[0].equals("0")) {
                s = "�����ϴ�";
                try {
                    byte[] uploadBuffer = new byte[1024];
                    File target = new File(args[1]);
                    FileInputStream fis = new FileInputStream(target);

                    byte[] requestBuffer = s.getBytes();
                    DatagramPacket outPack = new DatagramPacket(requestBuffer,requestBuffer.length, sAddr, 8000);
                    dSocket.send(outPack);

                    //�����ļ���
                    DatagramPacket namePack = new DatagramPacket(args[1].getBytes(), args[1].getBytes().length, sAddr, 8000);
                    dSocket.send(namePack);


                    int i = 0;//test
                    c = fis.read(uploadBuffer);
                    while (c != -1) {
                        System.out.println(i++);
                        DatagramPacket uploadPack = new DatagramPacket(uploadBuffer, c, sAddr, 8000);
                        dSocket.send(uploadPack);
                        //Thread.sleep(1);
                        uploadBuffer = new byte[1024];
                        c = fis.read(uploadBuffer);
                    }
                    DatagramPacket uploadPack = new DatagramPacket(uploadBuffer, 0, sAddr, 8000);
                    dSocket.send(uploadPack);
                    System.out.println("�ļ��ϴ��ɹ�!");
                } catch (FileNotFoundException e) {
                    System.out.println("�����ϴ����ļ������ڣ�");
                }

            } else {
                s = "��������";
                byte[] requestBuffer = s.getBytes();
                DatagramPacket outPack = new DatagramPacket(requestBuffer, requestBuffer.length, sAddr, 8000);
                dSocket.send(outPack);

                DatagramPacket namePack = new DatagramPacket(args[1].getBytes(), args[1].getBytes().length, sAddr, 8000);
                dSocket.send(namePack);

                //���շ�����Ϣ
                inBuffer = new byte[100];
                DatagramPacket warningPacket = new DatagramPacket(inBuffer, inBuffer.length);
                dSocket.receive(warningPacket);
                String w = new String(warningPacket.getData(), 0, warningPacket.getLength());
                System.out.println(w);
                if (w.equals("���ؿ�ʼ��")) {
                    byte[] outBuffer = new byte[1024];
                    DatagramPacket downloadPacker;
                    File target = new File(args[1]);
                    FileOutputStream fos = new FileOutputStream(target);
                    c = 0;
                    while (true) {
                        //System.out.println(c++);
                        downloadPacker = new DatagramPacket(outBuffer, outBuffer.length);
                        dSocket.receive(downloadPacker);
                        if (downloadPacker.getLength() == 0)
                            break;
                        fos.write(downloadPacker.getData(), 0, downloadPacker.getLength());
                        fos.flush();
                    }
                    fos.close();
                    System.out.println("�ļ�" + args[1] + "���سɹ�!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
