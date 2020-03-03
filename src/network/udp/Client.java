package network.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//客户端参数，arg[0]为 0/1 表示上传/下载，arg[1]为文件名
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
                s = "请求上传";
                try {
                    byte[] uploadBuffer = new byte[1024];
                    File target = new File(args[1]);
                    FileInputStream fis = new FileInputStream(target);

                    byte[] requestBuffer = s.getBytes();
                    DatagramPacket outPack = new DatagramPacket(requestBuffer,requestBuffer.length, sAddr, 8000);
                    dSocket.send(outPack);

                    //发送文件名
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
                    System.out.println("文件上传成功!");
                } catch (FileNotFoundException e) {
                    System.out.println("请求上传的文件不存在！");
                }

            } else {
                s = "请求下载";
                byte[] requestBuffer = s.getBytes();
                DatagramPacket outPack = new DatagramPacket(requestBuffer, requestBuffer.length, sAddr, 8000);
                dSocket.send(outPack);

                DatagramPacket namePack = new DatagramPacket(args[1].getBytes(), args[1].getBytes().length, sAddr, 8000);
                dSocket.send(namePack);

                //接收返回信息
                inBuffer = new byte[100];
                DatagramPacket warningPacket = new DatagramPacket(inBuffer, inBuffer.length);
                dSocket.receive(warningPacket);
                String w = new String(warningPacket.getData(), 0, warningPacket.getLength());
                System.out.println(w);
                if (w.equals("下载开始！")) {
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
                    System.out.println("文件" + args[1] + "下载成功!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
