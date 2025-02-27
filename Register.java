import java.io.*;

// Class representing the registration of new accounts
public class Register {
    private String name; // Account holder's name
    private String password; // Account password
    private String phone; // Phone number associated with the account
    private String branch; // Branch of the account
    private String type; // Type of account

    // Constructor to initialize the register object
    public Register(String name, String password, String phone, String branch, String type) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.branch = branch;
        this.type = type;
    }

    // Method to generate a new account number based on the branch
    public String getAccountNum(String branch) {
        int s1 = 0; // Counter for UIC branch
        int c1 = 0; // Counter for HKBU branch
        String txtFile = "src/bankAccount"; // Path to the file containing account information
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                String[] s = line.split(" ");
                if (s[5].equals("UIC")) {
                    s1 += 1;
                } else if (s[5].equals("HKBU")) {
                    c1 += 1;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Generate account number based on branch
        String num1 = "";
        if (branch.equals("HKBU")) {
            for (int i = 0; i < 5 - Integer.toString(c1).length(); i++) {
                num1 += "0";
            }
            num1 += Integer.toString(c1);
        } else if (branch.equals("UIC")) {
            for (int i = 0; i < 5 - Integer.toString(s1).length(); i++) {
                num1 += "0";
            }
            num1 += Integer.toString(s1);
        }
        String num2 = "";
        if (branch.equals("UIC")) {
            num2 = "1" + num1.substring(1);
        } else if (branch.equals("HKBU")) {
            num2 = "0" + num1.substring(1);
        }
        return num2;
    }

    // Method to register a new account
    public String register() {
        String res1 = "";
        Account a = new Account() {
            @Override
            public String getAccountType() {
                return null;
            }
        };
        // Create account object based on branch
        if (branch.equals("UIC") || branch.equals("uic")) {
            a = new cAccount(getAccountNum(branch), name, password, 0.00, branch, phone);
        } else if (branch.equals("HKBU") || branch.equals("hkbu")) {
            a = new sAccount(getAccountNum(branch), name, password, 0.00, branch, phone);
        }
        String txtfile = "src/bankAccount"; // Path to the file containing account information
        try {
            // Write account information to file
            String info = a.getAccountNo() + " " + a.getName() + " " + a.getPassword() + " " + a.getBalance() + " " + a.getAccountType() + " " + a.getBranch() + " " + a.getPhone();
            res1 = a.getAccountNo();
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(txtfile, true));
            bw1.write(info);
            bw1.newLine();
            bw1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res1;
    }

    // Method to check the number of accounts associated with a phone number
    public static int check(String phone) {
        String txtfile = "src/bankAccount"; // Path to the file containing account information
        int a = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(txtfile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                String[] s1 = line.split(" ");
                if (s1[6].equals(phone)) {
                    a += 1;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    public static void main(String[] args) {
        // Test the check method
        System.out.println(Register.check("11223344556"));
    }
}
