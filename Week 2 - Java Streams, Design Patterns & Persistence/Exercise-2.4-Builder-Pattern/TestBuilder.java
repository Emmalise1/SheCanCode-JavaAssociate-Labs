public class TestBuilder {
    public static void main(String[] args) {
        // Test 1: Valid construction
        try {
            NotificationMessage message = new NotificationMessage.Builder()
                    .to("user@example.com")
                    .subject("Welcome!")
                    .body("Thank you for joining.")
                    .priority(Priority.HIGH)
                    .attach("welcome.pdf")
                    .build();

            System.out.println(" Valid message created: " + message);
            System.out.println("  Recipient: " + message.getRecipient());
            System.out.println("  Priority: " + message.getPriority());
            System.out.println("  Attachments: " + message.getAttachments());

        } catch (IllegalStateException e) {
            System.out.println(" Unexpected error: " + e.getMessage());
        }

        // Test 2: Missing recipient
        try {
            System.out.println("\nTest: Missing recipient");
            NotificationMessage message = new NotificationMessage.Builder()
                    .subject("Test")
                    .body("This should fail")
                    .build();

            System.out.println(" Should have thrown exception but didn't");

        } catch (IllegalStateException e) {
            System.out.println(" Caught expected exception: " + e.getMessage());
        }

        // Test 3: Missing body
        try {
            System.out.println("\nTest: Missing body");
            NotificationMessage message = new NotificationMessage.Builder()
                    .to("user@example.com")
                    .subject("Test")
                    .build();

            System.out.println(" Should have thrown exception but didn't");

        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Test 4: Empty recipient
        try {
            System.out.println("\nTest: Empty recipient");
            NotificationMessage message = new NotificationMessage.Builder()
                    .to("")
                    .subject("Test")
                    .body("This should fail")
                    .build();

            System.out.println("Should have thrown exception but didn't");

        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }


    }
}
