package test02.clien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import test02.running_thread.ReceiveThread;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Please First enter Server's IP address:");
        Scanner sc = new Scanner(System.in);
        String sv_ip= sc.nextLine();
        try {
            Socket socket = new Socket(sv_ip, 12345);
            System.out.println("Connected to the server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String client = "Client";
            Thread receiveThread = new ReceiveThread(socket, in, client);
            receiveThread.start();


            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
            // for sending messages
            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}