import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class sAccount extends Account implements methods {
    public sAccount(String accountNo, String name, String password, double balance, String branch, String phone) {
        super(accountNo, name, password, balance, branch, phone);
    }

    @Override
    public String getAccountType() {
        return "S";
    }

    public String inquiry() {
        Inquiry i1 = new Inquiry(super.getAccountNo(), "S");
        String ans = i1.inquiry();
        return ans;
    }

    public String withdraw(double money1) {
        if (money1 > 3000 && money1 <= super.getBalance()) {
            return "Withdrawal failed, exceeds maximum limit";
        }
        if (money1 > super.getBalance()) {
            return "You need to save money";
        }

        Withdraw w1 = new Withdraw(super.getAccountNo(), "S", money1);
        return w1.withdraw();
    }

    public String deposite(double money1) {
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
                Deposite d2 = new Deposite(super.getAccountNo(), "S", money1);
                d2.deposite();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "Deposit successful";
    }

    public String transfer() {
        return "hahahha";
    }

    public static void main(String[] args) {
        sAccount as = new sAccount("90480", "tHkbctADAA", "194611", 339051.13, "UIC", "16754017244");
        System.out.println(as.inquiry());
        System.out.println(as.deposite(1000));
        System.out.println(as.inquiry());
    }
}
