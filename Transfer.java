import java.io.*;

public class Transfer{
    private String fromAccountNum;
    private String toAccountNum;
    private String toAccountName;
    private String fromBranch;
    private String toBranch;
    private String password; // Your own password

    String txtfile = "src/bankAccount";

    public Transfer(String fromAccountNum, String toAccountNum, String toAccountName, String fromBranch, String toBranch) {
        this.fromAccountNum = fromAccountNum;
        this.toAccountNum = toAccountNum;
        this.fromBranch = fromBranch;
        this.toBranch = toBranch;
        this.toAccountName = toAccountName;
    }

    // Pass in the found account information, only the account name is searched, the rest are passed in by the Login method
    public String transfer(String fromBranch, String toBranch, double fromBalance, double toBalance, double amount, String fromAccountType, String toAccountType) {
        String ans;
        try {
            // Find the other party's account information
            BufferedReader br1 = new BufferedReader(new FileReader(txtfile));
            int num1 = 0;
            String line1;
            String[] info_list;
            boolean flag = false;
            while ((line1 = br1.readLine()) != null) {
                num1 += 1;
                info_list = line1.split(" ");
                if (info_list[0].equals(toAccountNum)) {
                    flag = true;
                    break;
                }
            }
            br1.close();

            if (!flag) {
                ans = "Account information not found";
                return ans;
            }

            // Find the position of your own account
            BufferedReader br2 = new BufferedReader(new FileReader(txtfile));
            int num2 = 0;
            String line2;
            String[] info_list2;
            while ((line2 = br2.readLine()) != null) {
                num2 += 1;
                info_list2 = line2.split(" ");

                if (info_list2[0].equals(fromAccountNum)) {
                    break;
                }
            }
            br2.close();

            ans = "";
            // Transfer within the same branch
            if (fromBranch.equals(toBranch)) {
                if (amount <= 0 || amount * 1.005 > fromBalance) {
                    ans = "Insufficient balance";
                    return ans;
                } else {
                    fromBalance = fromBalance - 0.005 * amount - amount;
                    toBalance = toBalance + amount;

                    // Insert IO modification method, first read, use the information of each account
                    // Create a table to store all information and modify fromBalance
                    BufferedReader br3 = new BufferedReader(new FileReader(txtfile));
                    StringBuilder sb1 = new StringBuilder();
                    String line3;
                    while ((line3 = br3.readLine()) != null) {
                        sb1.append(line3).append(",");
                    }
                    br3.close();
                    // lines is a variable that contains all the information
                    String[] lines1 = sb1.toString().split(",");
                    String fromOldContent = lines1[num2 - 1];
                    String toOldContent = lines1[num1 - 1];

                    String[] fromChanger = fromOldContent.split(" ");
                    double fromA = fromBalance;
                    fromChanger[3] = Double.toString(fromA);

                    String[] toChanger = toOldContent.split(" ");
                    double toA = toBalance;
                    toChanger[3] = Double.toString(toA);

                    String fromNewContent = "";
                    for (String s : fromChanger) {
                        fromNewContent += (s + " ");
                    }
                    lines1[num2 - 1] = fromNewContent;

                    String toNewContent = "";
                    for (String s : toChanger) {
                        toNewContent += (s + " ");
                    }
                    lines1[num1 - 1] = toNewContent;

                    BufferedWriter writer = new BufferedWriter(new FileWriter(txtfile));
                    for (String l : lines1) {
                        writer.write(l); // Rewrite
                        writer.newLine();
                    }
                    writer.close();
                    ans = "Transfer successful"; // Modification is completed, send success message
                    return ans;
                }

            }
            // Transfer between different branches
            else {
                if (fromAccountType.equals("C") && toAccountType.equals("C")) {
                    if (amount <= 0 || amount * 1.01 > fromBalance) {
                        ans = "Insufficient balance";
                        return ans;
                    } else {
                        fromBalance = fromBalance - 0.01 * amount - amount;
                        toBalance = toBalance + amount;

                        BufferedReader br3 = new BufferedReader(new FileReader(txtfile));
                        StringBuilder sb1 = new StringBuilder();
                        String line3;
                        while ((line3 = br3.readLine()) != null) {
                            sb1.append(line3).append(",");
                        }
                        br3.close();
                        // lines is a variable that contains all the information
                        String[] lines1 = sb1.toString().split(",");
                        String fromOldContent = lines1[num2 - 1];
                        String toOldContent = lines1[num1 - 1];

                        String[] fromChanger = fromOldContent.split(" ");
                        double fromA = fromBalance;
                        fromChanger[3] = Double.toString(fromA);

                        String[] toChanger = toOldContent.split(" ");
                        double toA = toBalance;
                        toChanger[3] = Double.toString(toA);

                        String fromNewContent = "";
                        for (String s : fromChanger) {
                            fromNewContent += (s + " ");
                        }
                        lines1[num2 - 1] = fromNewContent;

                        String toNewContent = "";
                        for (String s : toChanger) {
                            toNewContent += (s + " ");
                        }
                        lines1[num1 - 1] = toNewContent;

                        BufferedWriter writer = new BufferedWriter(new FileWriter(txtfile));
                        for (String l : lines1) {
                            writer.write(l); // Rewrite
                            writer.newLine();
                        }
                        writer.close();
                        ans = "Transfer successful"; // Modification is completed, send success message
                        return ans;
                    }
                } else {
                    ans = "One of the accounts is not a checking account, transfer not allowed";
                    return ans;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "Transfer successful";
    }

    public static void main(String[] args) {
        Transfer t = new Transfer("10001", "00000", "SUN", "UIC", "HKBU");
        System.out.println(t.transfer("UIC", "HKBU", 2000.0, 0.0, 1000, "C", "C"));
    }
}
