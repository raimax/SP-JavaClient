package lt.viko.eif.rcepauskas.javaclient;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.FileService;
import lt.viko.eif.rcepauskas.blog.JaxbTransformer;

import javax.xml.bind.JAXBException;
import java.net.*;
import java.io.*;

/**
 * This class lets you connect to a tcp server
 */
public class JavaClient {
    private Socket clientSocket;
    private BufferedOutputStream  out;
    private BufferedInputStream in;
    private final FileService fileService;

    public JavaClient() {
        fileService = new FileService();
    }

    /**
     * Connects to server with specified ip and port address
     * @param ip server ip address
     * @param port server port address
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        System.out.println(String.format("Connected to %s:%d", ip, port));
        in = new BufferedInputStream(new BufferedInputStream(clientSocket.getInputStream()));
    }

    /**
     * Receives file from server
     * @param filePath path to file
     * @throws FileNotFoundException
     */
    public void receiveFile(String filePath) throws FileNotFoundException {
        out = new BufferedOutputStream(new FileOutputStream(filePath));
        fileService.receiveFile(in, out);
    }

    /**
     * closes socket connection and input, output buffers
     */
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
