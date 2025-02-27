import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inquiry {
    private String AccountNum;
    private String AccountType;

    public Inquiry(String AccountNum, String AccountType) {
        this.AccountNum = AccountNum;
        this.AccountType = AccountType;
    }

    public String inquiry() {
        String txtFile = "src/bankAccount";
        String line;
        String ans = "";
        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            while ((line = br.readLine()) != null) {
                String[] s1 = line.split(" ");
                if (s1[0].equals(AccountNum) && s1[4].equals(AccountType)) {
                    ans = s1[3];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ans.length() == 0) {
            return "No Account";
        }
        return ans;
    }

    public static void main(String[] args) {
        Inquiry i = new Inquiry("90480", "S");
        System.out.println("The balance is " + i.inquiry());
    }
}
