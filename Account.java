// Abstract class representing an account
public abstract class Account{
    private String accountNo; // Account number
    private String name; // Account holder's name
    private String password; // Account password
    private double balance; // Account balance
    private String accountType; // Type of account
    private String branch; // Branch of the account
    private String phone; // Phone number associated with the account

    // Constructor with parameters
    public Account(String accountNo, String name, String password, double balance, String branch, String phone) {
        this.accountNo = accountNo;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.branch = branch;
        this.phone = phone;
    }

    // Default constructor
    public Account(){

    }

    // Getter and setter methods for account attributes
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Abstract method to get account type
    public abstract String getAccountType();

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Method to inquire account balance
    public String Inquiry(String AccountNum,String AccountType){
        Inquiry i =new Inquiry(AccountNum,AccountType);
        String s="The balance of your account is "+i.inquiry()+" yuan";
        return s;
    }

    // Method to change account password
    public String changePassword(String oldpassword,String newpassword,String confirmpassword){
        String ans;
        if(!oldpassword.equals(password)){
            ans="The old password is incorrect";
            return ans;
        }
        if(!(newpassword.matches("\\d{6}") && confirmpassword.matches("\\d{6}"))){
            ans="Incorrect new password format";
            return ans;
        }
        if(!newpassword.equals(confirmpassword)){
            ans="Confirm password does not match";
            return ans;
        }
        changePassword cp=new changePassword(accountNo,newpassword);
        String ans2=cp.changePassword1();
        return ans2;
    }
}
