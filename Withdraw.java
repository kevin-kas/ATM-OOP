import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Withdraw {
    private String AccountNum;
    private String AccountType;
    private double Money;

    String txtfile = "src/bankAccount";

    public Withdraw(String accountNum, String accountType, double Money) {
        this.AccountNum = accountNum;
        this.AccountType = accountType;
        this.Money = Money;
    }

    public String withdraw() { // Withdrawal
        // Find the corresponding AccountInfo
        String ans = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(txtfile)); // Create a file reader object
            int num1 = 0; // Record the line number
            String line; // Store the content of each line
            String[] info_list; //
            Boolean flag = false; // Check if the result is found
            while ((line = br1.readLine()) != null) {
                num1 += 1;
                info_list = line.split(" "); // Convert the information of each line into an array
                if (info_list[0].equals(AccountNum) && info_list[4].equals(AccountType)) {
                    flag = true; // If found, set flag to true
                    break; // End the loop
                }
            }
            br1.close();

            if (!flag) { // If flag is not modified, it means the account information is not found
                ans = "Account information not found";
                return ans;
            }

            // After checking, store all the information in the table
            BufferedReader br2 = new BufferedReader(new FileReader(txtfile)); // Create a new reader to start from the beginning
            StringBuilder sb1 = new StringBuilder();
            String line2;
            while ((line2 = br2.readLine()) != null) {
                sb1.append(line2).append(",");
            }
            br2.close(); // Use commas to separate each line, so that we know the order of the information

            String[] lines = sb1.toString().split(","); // Split by commas
            String old_content = lines[num1 - 1]; // Read the information

            String[] changer = old_content.split(" "); // Split the found line by spaces
            double a = Double.parseDouble(changer[3]) - Money; // Modify
            changer[3] = Double.toString(a);

            String new_content = "";
            for (String s : changer) {
                new_content += (s + " ");
            } // Assemble the modified information
            lines[num1 - 1] = new_content; // Assign to the original position

            BufferedWriter writer = new BufferedWriter(new FileWriter(txtfile));
            for (String l : lines) {
                writer.write(l); // Rewrite
                writer.newLine();
            }
            writer.close();
            ans = "Withdrawal successful"; // Modification is completed, send success message
            return ans;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ans;
    }

    public static void main(String[] args) {
        Inquiry i = new Inquiry("74145", "C");
        System.out.println(i.inquiry());
        Withdraw w = new Withdraw("74145", "C", 100);
        System.out.println(w.withdraw());
        Inquiry i2 = new Inquiry("74145", "C");
        System.out.println(i2.inquiry());
    }
}
