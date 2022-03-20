public class Main {
    public static void main(String[] args) {
        JavaClient javaClient = new JavaClient();

        try {
            javaClient.startConnection("127.0.0.1", 6666);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            javaClient.stopConnection();
        }
    }
}
