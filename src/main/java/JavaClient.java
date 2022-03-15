import java.net.*;
import java.io.*;

public class JavaClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws java.io.IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws java.io.IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public String sendMessage(String message) throws java.io.IOException {
        out.println(message);
        return in.readLine();
    }
}
