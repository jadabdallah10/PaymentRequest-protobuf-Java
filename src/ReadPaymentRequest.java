import proto.PaymentRequestProtos.PaymentRequest;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadPaymentRequest {
    public static void main(String[] args) {
        // Deserialize from a binary file
        try (FileInputStream inputStream = new FileInputStream("payment_request.bin")) {
            // Explicitly use parseFrom(InputStream)
            PaymentRequest request = PaymentRequest.parseFrom((java.io.InputStream) inputStream);
            System.out.println("Transaction ID: " + request.getTransactionId());
            System.out.println("Payer Name: " + request.getPayerName());
            System.out.println("Payee Name: " + request.getPayeeName());
            System.out.println("Amount: " + request.getAmount());
            System.out.println("Currency: " + request.getCurrency());
            System.out.println("Payment Date: " + request.getPaymentDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
