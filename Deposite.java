import java.io.*;
import java.time.LocalDate;

public class Deposite { // Deposit class to handle deposit transactions
    private String AmountNum; // Account number
    private String AmountType; // Account type
    private double Money; // Amount of money to deposit

    // Constructor to initialize the deposit object
    public Deposite(String AmountNum, String AmountType, double Money) {
        this.AmountNum = AmountNum;
        this.AmountType = AmountType;
        this.Money = Money;
    }

    // Method to execute the deposit transaction
    public String deposite() {
        String ans = "";
        String txtfile = "src/bankAccount"; // Path to the file containing account information
        try {
            BufferedReader br = new BufferedReader(new FileReader(txtfile)); // Reader to read account file

            int num1 = 0; // Line number tracker
            String line; // Variable to store each line from the file
            String[] info_list; // Array to store split line information
            Boolean flag = false; // Flag to check if account is found
            while ((line = br.readLine()) != null) {
                num1 += 1;
                info_list = line.split(" ");
                if (info_list[0].equals(AmountNum) && info_list[4].equals(AmountType)) {
                    flag = true; // Account found
                    break;
                }
            }
            br.close();

            if (!flag) { // If account is not found
                ans = "Account information not found";
                return ans;
            }

            BufferedReader br2 = new BufferedReader(new FileReader(txtfile)); // Second reader to read account file again
            StringBuilder sb1 = new StringBuilder();
            String line2;
            while ((line2 = br2.readLine()) != null) {
                sb1.append(line2).append(",");
            }
            br2.close();

            String[] lines = sb1.toString().split(","); // Split file content by comma
            String old_content = lines[num1 - 1]; // Get the line with the account information

            String[] changer = old_content.split(" "); // Split the account information line by spaces
            double a = Double.parseDouble(changer[3]) + Money; // Update the balance
            changer[3] = Double.toString(a); // Convert new balance to string

            String new_content = "";
            for (String s : changer) {
                new_content += (s + " "); // Reconstruct the line with updated balance
            }
            lines[num1 - 1] = new_content; // Update the lines array

            BufferedWriter writer = new BufferedWriter(new FileWriter(txtfile)); // Writer to write updated content to the file
            for (String l : lines) {
                writer.write(l); // Write each line back to the file
                writer.newLine();
            }
            writer.close();
            ans = "Deposit successful";

            // Log the deposit transaction if successful
            if (ans.equals("Deposit successful")) {
                String txtfile2 = "src/Deposite_info.txt"; // Path to the deposit log file
                LocalDate d1 = LocalDate.now(); // Get current date
                String info1 = d1.toString(); // Convert date to string
                String info = AmountNum + " " + info1 + " " + Money; // Construct log entry
                BufferedWriter w2 = new BufferedWriter(new FileWriter(txtfile2, true)); // Append mode writer to log file
                w2.write(info); // Write log entry
                w2.newLine();
                w2.close();
            }
            return ans;

        } catch (Exception ex) { // Handle exceptions
            ex.printStackTrace();
        }

        return ans;
    }

    public static void main(String[] args) { // Main method to test deposit functionality
        Inquiry i = new Inquiry("74145", "C");
        System.out.println(i.inquiry()); // Print current balance
        Deposite d = new Deposite("74145", "C", 100); // Create a deposit object
        System.out.println(d.deposite()); // Execute deposit
        Inquiry i2 = new Inquiry("74145", "C");
        System.out.println(i2.inquiry()); // Print new balance
    }
}
