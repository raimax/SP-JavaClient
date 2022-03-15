public class Main {
    public static void main(String[] args) {
        JavaClient javaClient = new JavaClient();

        try {
            javaClient.startConnection("127.0.0.1", 6666);
            String response = javaClient.sendMessage("hello java server");
            System.out.println("Server: " + response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
