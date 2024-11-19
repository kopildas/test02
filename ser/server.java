package test02.ser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import test02.running_thread.ReceiveThread;

public class server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String server = "Server";
            Thread receiveThread = new ReceiveThread(socket, in,server);
            receiveThread.start();

            
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // sending messages
            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}