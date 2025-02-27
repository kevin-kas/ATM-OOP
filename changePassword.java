import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class changePassword {
    private String amountNum;
    private String newPassword;

    public changePassword(String amountNum, String newpassword) {
        this.amountNum = amountNum;
        this.newPassword = newpassword;
    }

    public String changePassword1() {
        String ans = "";
        String txtfile = "src/bankAccount";
        try {
            BufferedReader br = new BufferedReader(new FileReader(txtfile));

            int num1 = 0;
            String line;
            String[] info_list;
            Boolean flag = false;
            while ((line = br.readLine()) != null) {
                num1 += 1;
                info_list = line.split(" ");
                if (info_list[0].equals(amountNum)) {
                    flag = true;
                    break;
                }
            }
            br.close();

            if (!flag) {
                ans = "Account information not found";
                return ans;
            }

            BufferedReader br2 = new BufferedReader(new FileReader((txtfile)));
            StringBuilder sb1 = new StringBuilder();
            String line2;
            while ((line2 = br2.readLine()) != null) {
                sb1.append(line2).append(",");
            }
            br2.close();

            String[] lines = sb1.toString().split(",");
            String old_content = lines[num1 - 1];

            String[] changer = old_content.split(" ");
            String s1 = newPassword;
            changer[2] = s1;

            String new_content = "";
            for (String s : changer) {
                new_content += (s + " ");
            }
            lines[num1 - 1] = new_content;

            BufferedWriter writer = new BufferedWriter(new FileWriter(txtfile));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            writer.close();
            ans = "Change successful";

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ans;
    }
}
