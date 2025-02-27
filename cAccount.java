import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class cAccount extends Account implements methods {
    public cAccount(String accountNo, String name, String password, double balance, String branch, String phone) {
        super(accountNo, name, password, balance, branch, phone);
    }

    @Override
    public String getAccountType() {
        return "C";
    }

    public String inquiry() {
        Inquiry i1 = new Inquiry(super.getAccountNo(), "C");
        String ans = i1.inquiry();
        return ans;
    }

    public String withdraw(double money1) {
        if (money1 > 2000 && money1 <= super.getBalance()) {
            return "Withdrawal failed, exceeds maximum limit";
        }
        if (money1 > super.getBalance()) {
            return "You need to save money";
        }
        Withdraw w1 = new Withdraw(super.getAccountNo(), "C", money1);
        return w1.withdraw();
    }

    public String deposite(double money1) {
        // First check if a person's daily deposit amount exceeds 5000

        try {
            BufferedReader br = new BufferedReader(new BufferedReader(new FileReader("src/Deposite_info.txt")));
            double m = 0;
            LocalDate d1 = LocalDate.now();
            StringBuilder sb1 = new StringBuilder();
            String line2;
            while ((line2 = br.readLine()) != null) {
                if (line2.length() == 0) {
                    continue;
                }
                String[] a1 = line2.split(" ");
                if (a1[1].equals(d1.toString()) && a1[0].equals(super.getAccountNo())) {
                    m += Double.parseDouble(a1[2]);
                }
            }
            if (m > 50000) {
                return "Deposit failed, exceeds daily limit";
            } else if (m + money1 > 50000) {
                return "Deposit failed, exceeds daily limit";
            } else {
                Deposite d2 = new Deposite(super.getAccountNo(), "C", money1);
                d2.deposite();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Check if the balance after deposit exceeds 5000

        return "Deposit successful";
    }
    

    public String transfer() {
        return "num";
    }

    public static void main(String[] args) {
        cAccount ac = new cAccount("00000", "SUN", "123456", 22770.9, "UIC", "11223344556");
        System.out.println(ac.deposite(1000));
        System.out.println(ac.inquiry());
    }
}
