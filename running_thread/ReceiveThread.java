package test02.running_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReceiveThread extends Thread {
    private final Socket socket;
    private final BufferedReader in;
    private final String name_String;

    public ReceiveThread(Socket socket, BufferedReader in, String  name_String) {
        this.socket = socket;
        this.in = in;
        this.name_String = name_String;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("TYPING")) {
                    System.out.println("Server is typing...");
                } else {
                    System.out.println(name_String+": " + message);
                }
            }
        } catch (IOException e) {
            System.out.println("Connection closed...");
        }
    }
}