package com.example.fitjourney;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        while(true) {
            Servidor servidor = new Servidor();
            servidor.iniciarServidor();
        }

    }
}
