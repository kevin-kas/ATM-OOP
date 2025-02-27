public class CheckPassword {

    // Used to check if the entered password meets the requirements
    String password;

    public CheckPassword(String password) {
        this.password = password;
    }

    public String check() throws NumFormatExp {
        String ans = "";
        try {
            int a = Integer.parseInt(password);
        } catch (NumberFormatException ex) {
            ans = "Format of Your Password is Wrong";
        }
        if (password.length() != 6) {
            throw new NumFormatExp("Please Enter a 6-Digit Password");
        }

        return ans;
    }
}
