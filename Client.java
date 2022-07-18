package com.mycompany.ecoclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
     public static void main(String[] args) throws IOException{
         //definicion del puerto udp
         final int portServer =8000;
         //definidion del tamanio del buffer
         byte[] buffer = new byte[1024];
         //definicion de scanner para leer los datos tecleados por el usuario del cliente
         Scanner readText = new Scanner(System.in);
         
         try{
             System.out.println("Iniciando CLiente\n");
            //direccion ip sera local
            InetAddress ipServer = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            System.out.println("Escriba lo que quiera: :D \n ");
            String clientMessage = new String();
            clientMessage = readText.nextLine();
            buffer = clientMessage.getBytes();
            DatagramPacket requestToServer = new DatagramPacket(buffer, buffer.length, ipServer, portServer);
            System.out.println("mensaje al servidor UDP \n ");
            socketUDP.send(requestToServer);
            DatagramPacket responseToClient = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(responseToClient);
            String serverMessage = new String(responseToClient.getData());
            System.out.println("\n >>> Mensaje recibido del servidor <<< \n");
            System.out.println("> "+serverMessage);
            readText.close();
            socketUDP.close();
            
         }catch(SocketException ex){
             Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
