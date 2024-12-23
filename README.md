
---

# **PaymentRequest Protobuf -README- Jad Abdallah (210037)**

This project demonstrates the use of Google Protocol Buffers (Protobuf) to serialize and deserialize a `PaymentRequest` message in Java. It includes a `.proto` schema file, a class to write serialized data, and a class to read and deserialize the data.

---

## **Project Structure**

```
.
├── build/                          # Compiled classes and generated files
├── lib/
│   └── protobuf-java-4.29.0.jar    # Protobuf runtime library
├── src/
│   └── proto/
│       ├── PaymentRequestProtos.java    # Generated Protobuf Java classes
│   ├── ReadPaymentRequest.java      # Reads and deserializes PaymentRequest
│   └── WritePaymentRequest.java     # Creates and serializes PaymentRequest
├── payment_request.proto            # Protobuf schema file
├── payment_request.bin              # Serialized binary file (output from WritePaymentRequest)
└── README.md                        # This documentation
```

---

## **Setup and Requirements**

### **Prerequisites**
1. **Java Development Kit (JDK)**: Install JDK 8 or later.
2. **Protocol Buffers Compiler (protoc)**: Install version `29.0`.
   - Verify installation with:
     ```bash
     protoc --version
     ```
     Expected output: `libprotoc 29.0`.

3. **Protobuf Runtime Library**:
   - Ensure `protobuf-java-4.29.0.jar` is in the `lib` directory.

---

## **How to Use**

### **Step 0: Create Build directory**

Run the following command to generate the build directory that will contain the compiled classes:

```bash
mkdir build
```
This command helps to verify that the Java classes are being compiled correctly if they appear inside the build folder.


### **Step 1: Compile the Protobuf Schema**

Run the following command to generate the Protobuf Java classes from the schema:

```bash
protoc --java_out=./src payment_request.proto
```

---

### **Step 2: Compile Java Classes**

Compile the Protobuf-generated and custom Java files:

```bash
javac -cp ".;lib/protobuf-java-4.29.0.jar;build" -d build src/proto/PaymentRequestProtos.java
```
```bash
javac -cp ".;lib/protobuf-java-4.29.0.jar;build" -d build src/WritePaymentRequest.java 
```
```bash
javac -cp ".;lib/protobuf-java-4.29.0.jar;build" -d build src/ReadPaymentRequest.java
```
---

### **Step 3: Write PaymentRequest Data**

Run the `WritePaymentRequest` program to serialize a `PaymentRequest` object into a binary file:

```bash
java -cp ".;lib/protobuf-java-4.29.0.jar;build" WritePaymentRequest
```

This creates a file named `payment_request.bin`.

#### Example Output:
```
PaymentRequest written to payment_request.bin
```

---

### **Step 4: Read PaymentRequest Data**

Run the `ReadPaymentRequest` program to deserialize the `payment_request.bin` file and print its contents:

```bash
java -cp ".;lib/protobuf-java-4.29.0.jar;build" ReadPaymentRequest
```

#### Example Output:
```
Transaction ID: 12345
Payer Name: Jad Abdallah
Payee Name: Hadi Ghandour
Amount: 250.75
Currency: USD
Payment Date: 2024-12-04
```

---

## **Protobuf Schema**

The `payment_request.proto` file defines the `PaymentRequest` message structure:

```proto
syntax = "proto3";

package proto;
option java_outer_classname = "PaymentRequestProtos";

message PaymentRequest {
    string transaction_id = 1;
    string payer_name = 2;
    string payee_name = 3;
    double amount = 4;
    string currency = 5;
    string payment_date = 6;
}
```

---

## **Java Classes**

### **1. PaymentRequestProtos.java**

This file is generated by the Protobuf compiler. It defines the `PaymentRequest` class with methods to build, serialize, and deserialize objects.

---

### **2. WritePaymentRequest.java**

This program creates a `PaymentRequest` object and writes it to a binary file:

```java
import proto.PaymentRequestProtos.PaymentRequest;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritePaymentRequest {
    public static void main(String[] args) {
        PaymentRequest request = PaymentRequest.newBuilder()
                .setTransactionId("12345")
                .setPayerName("Jad Abdallah")
                .setPayeeName("Hadi Ghandour")
                .setAmount(250.75)
                .setCurrency("USD")
                .setPaymentDate("2024-12-04")
                .build();

        try (FileOutputStream outputStream = new FileOutputStream("payment_request.bin")) {
            request.writeTo(outputStream);
            System.out.println("PaymentRequest written to payment_request.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

### **3. ReadPaymentRequest.java**

This program reads the `payment_request.bin` file and deserializes it:

```java
import proto.PaymentRequestProtos.PaymentRequest;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadPaymentRequest {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(name:"payment_request.bin")) {
            PaymentRequest request = PaymentRequest.parseFrom((java.io.InputStream)inputStream);
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
```

---

## **Known Issues**

1. **Version Mismatch**: Ensure that the `protoc` compiler and `protobuf-java` runtime library versions match. For example:
   - `protoc 29.0` → `protobuf-java-4.29.0.jar`.

2. **Classpath Errors**: Verify that the classpath includes:
   - `build` directory (for compiled files).
   - `protobuf-java-4.29.0.jar`.

---


