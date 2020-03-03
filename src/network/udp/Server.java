package network.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        DatagramSocket dSocket;
        DatagramPacket requestType;
        DatagramPacket uploadPacket;
        byte[] inBuffer;
        System.out.println("服务器开始监听");
        String s;
        try {
            dSocket = new DatagramSocket(8000);
            while (true) {
                inBuffer = new byte[100];
                requestType = new DatagramPacket(inBuffer, inBuffer.length);
                dSocket.receive(requestType);
                s = new String(requestType.getData(), 0, requestType.getLength
                        ());
                System.out.println("接收的客户端信息为：" + s);

                if (s.equals("请求上传")) {
                    //接收文件名字
                    inBuffer = new byte[100];
                    DatagramPacket namePacket = new DatagramPacket(inBuffer,
                            inBuffer.length);
                    dSocket.receive(namePacket);
                    if (namePacket.getLength() != 0) {
                        String name = new String(namePacket.getData(), 0, namePacket.getLength());
                        System.out.println("文件名为：" + name);

                        //接收文件内容
                        inBuffer = new byte[1024];
                        File target = new File(name);
                        FileOutputStream fos = new FileOutputStream(target);
                        int c = 0;
                        while (true) {
                            System.out.println(c++);
                            uploadPacket = new DatagramPacket(inBuffer, inBuffer.length);
                            dSocket.receive(uploadPacket);
                            if (uploadPacket.getLength() == 0)
                                break;
                            fos.write(uploadPacket.getData(), 0, uploadPacket.getLength());
                            fos.flush();
                        }
                        fos.close();
                        System.out.println("文件接收成功!");
                    }
                } else if (s.equals("请求下载")) {
                    inBuffer = new byte[100];
                    int cPort;
                    InetAddress cAddr;
                    DatagramPacket namePacket = new DatagramPacket(inBuffer,
                            inBuffer.length);
                    dSocket.receive(namePacket);
                    cPort = namePacket.getPort();
                    cAddr = namePacket.getAddress();
                    String name = new String(namePacket.getData(), 0, namePacket.getLength());
                    System.out.println("请求下载的文件名为：" + name);
                    try {
                        File target = new File(name);
                        FileInputStream fis = new FileInputStream(target);

                        String warning = "下载开始！";
                        System.out.println(warning);
                        DatagramPacket warningPacket = new DatagramPacket(warning.getBytes(), warning.getBytes().length, cAddr, cPort);
                        dSocket.send(warningPacket);

                        byte[] downloadBuffer = new byte[1024];
                        int c;
                        int i = 0;//test
                        c = fis.read(downloadBuffer);
                        while (c != -1) {
                            //System.out.println(i++);
                            DatagramPacket downloadPack = new DatagramPacket
                                    (downloadBuffer, c, cAddr, cPort);
                            dSocket.send(downloadPack);
                            //Thread.sleep(1);
                            downloadBuffer = new byte[1024];
                            c = fis.read(downloadBuffer);
                        }
                        DatagramPacket endPack = new DatagramPacket(downloadBuffer, 0, cAddr, cPort);
                        dSocket.send(endPack);
                        System.out.println("文件" + name + "上传成功!");
                    } catch (FileNotFoundException e) {
                        String warning = "服务器目录下无" + name + "此文件！";
                        System.out.println(warning);
                        DatagramPacket warningPacket = new DatagramPacket(warning.getBytes(), warning.getBytes().length, cAddr, cPort);
                        dSocket.send(warningPacket);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}