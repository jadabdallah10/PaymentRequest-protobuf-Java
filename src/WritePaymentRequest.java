import java.io.FileOutputStream;
import java.io.IOException;
import proto.PaymentRequestProtos.PaymentRequest;

public class WritePaymentRequest {
    public static void main(String[] args) {
        // Create a PaymentRequest object
        PaymentRequest request = PaymentRequest.newBuilder()
                .setTransactionId("12345")
                .setPayerName("John Doe")
                .setPayeeName("Alice Smith")
                .setAmount(250.75)
                .setCurrency("USD")
                .setPaymentDate("2024-12-04")
                .build();

        // Serialize to a file
        try (FileOutputStream outputStream = new FileOutputStream("payment_request.bin")) {
            request.writeTo(outputStream);
            System.out.println("PaymentRequest written to payment_request.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
