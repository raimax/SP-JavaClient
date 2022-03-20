import java.net.*;
import java.io.*;

/**
 * This class lets you connect to a tcp server
 */
public class JavaClient {
    private Socket clientSocket;
    private BufferedOutputStream  out;
    private BufferedInputStream in;

    /**
     * Connects to server with specified ip and port address
     * @param ip server ip address
     * @param port server port address
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        System.out.println(String.format("Connected to %s:%d", ip, port));
        out = new BufferedOutputStream(new FileOutputStream("blog.xml"));
        in = new BufferedInputStream(new BufferedInputStream(clientSocket.getInputStream()));

        receiveFile();
    }

    private void receiveFile() throws IOException {
        byte[] b = new byte[8 * 1024];

        int len;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }

        System.out.println("File received");
    }

    public void stopConnection() {
        if (clientSocket.isConnected()) {
            try {
                in.close();
                out.close();
                clientSocket.close();
            }
            catch (IOException ex) {
                System.out.println("Error stopping connection: " + ex.getMessage());
            }
        }
    }
}
