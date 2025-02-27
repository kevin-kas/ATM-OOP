import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    private String accountNo;
    private String accountPassword;

    public Login(String accountNo, String accountPassword) {
        this.accountNo = accountNo;
        this.accountPassword = accountPassword;
    }

    public String login() {
        String txtFile = "src/bankAccount";
        String line;
        String ans = "";

        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            while ((line = br.readLine()) != null) {
                String[] s1 = line.split(" ");
                if (s1[0].equals(accountNo) && s1[2].equals(accountPassword)) {
                    ans = "Found";
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ans;
    }

    public static String[] login(String accountNo) {
        String txtFile = "src/bankAccount";
        String line;
        String[] ans = {};

        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            while ((line = br.readLine()) != null) {
                String[] s1 = line.split(" ");
                if (s1[0].equals(accountNo)) {
                    ans = line.split(" ");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ans;
    }
}
