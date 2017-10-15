package com.example.patricia.matrix.LiveStreaming;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LiveStreaming implements Runnable {

    private String TAG = LiveStreaming.class.getSimpleName();
    private final String UDP_IP = "10.5.5.9";
    private final int UDP_PORT = 8554;
    private byte[] message;
    private InetAddress inetAddress;
    private DatagramPacket datagramPacket;
    private DatagramSocket datagramSocket;

    public LiveStreaming() {
        try {
            inetAddress = InetAddress.getByName(UDP_IP);
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO HOST DESCONHECIDO : " + exception.getMessage(), exception);
        }
        message = "_GPHD_:0:0:2:0.000000".getBytes();
        datagramPacket = new DatagramPacket(message, message.length, inetAddress, UDP_PORT);
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO AO CRIAR DATAGRAM SOCKET : " + exception.getMessage(), exception);
        }
    }

    @Override
    public void run() {
        try {
            datagramSocket.send(datagramPacket);
        } catch(IOException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO AO EXECUTAR MÉTODO RUN : ", exception);
        }
    }

    public void closeSocket() {
        if ( datagramSocket != null ) {
            datagramSocket.close();
        }
    }
}