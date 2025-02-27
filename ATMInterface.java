import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private JTextField accountNoTextField;
    private JTextField passwordTextField;

    public ATMInterface(){
    }


    public void initializeUI() {
        JFrame frame1 = new JFrame("ATM MACHINE");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1000, 800); // Increase the window size
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = (screenWidth - frame1.getWidth()) / 2;
        int y = (screenHeight - frame1.getHeight()) / 2;
        frame1.setBounds(x, y, frame1.getWidth(), frame1.getHeight());
        frame1.setLayout(null);

        // Set background image
        ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(frame1.getWidth(), frame1.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 150)); // Set background color and increase transparency
        panel.setBounds(150, 210, 700, 350); // Adjust panel size and position
        panel.setOpaque(false);
        panel.setLayout(null);
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        JLabel welcomeLabel = new JLabel("Welcome to BU BANK");
        welcomeLabel.setBounds(150, 20, 350, 20); // Adjust position and size
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLACK); // Set font color
        panel.add(welcomeLabel);

        JLabel accountNoLabel = new JLabel("Account Number");
        accountNoLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        accountNoLabel.setBounds(60, 100, 150, 30); // Adjust position and size
        accountNoLabel.setForeground(Color.BLACK); // Set font color
        panel.add(accountNoLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordLabel.setBounds(60, 150, 150, 30); // Adjust position and size
        passwordLabel.setForeground(Color.BLACK); // Set font color
        panel.add(passwordLabel);

        accountNoTextField = new JTextField();
        accountNoTextField.setBounds(220, 100, 200, 30); // Adjust position and size
        panel.add(accountNoTextField);
        accountNoTextField.setColumns(10);

        passwordTextField = new JTextField();
        passwordTextField.setColumns(10);
        passwordTextField.setBounds(220, 150, 200, 30); // Adjust position and size
        panel.add(passwordTextField);

        JButton signInButton = new JButton("Sign in");
        signInButton.setBounds(320, 200, 100, 30); // Adjust position and size
        panel.add(signInButton);
        signInButton.addActionListener(new ActionListener() {
            // Login method
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accountNoTextField.getText().length() == 0 || passwordTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Input Error", "Result", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Login l1 = new Login(accountNoTextField.getText(), passwordTextField.getText());
                String ans1 = l1.login();
                if (ans1.equals("Found")) {
                    frame1.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login Successfully!", "Result", JOptionPane.INFORMATION_MESSAGE);
                    // Find the corresponding entry for the user
                    String[] a = Login.login(accountNoTextField.getText());
                    if (a[4].equals("C")) {
                        cAccount ac = new cAccount(a[0], a[1], a[2], Double.parseDouble(a[3]), a[5], a[6]);
                        UserInterface(ac);
                    } else if (a[4].equals("S")) {
                        sAccount as = new sAccount(a[0], a[1], a[2], Double.parseDouble(a[3]), a[5], a[6]);
                        UserInterface(as);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed, Incorrect Username or Password", "Result", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton signUpButton = new JButton("Register");
        signUpButton.setBounds(200, 200, 100, 30); // Adjust position and size
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the corresponding interface
                JDialog registerDialog = new JDialog((Dialog) null, "Register", true);
                registerDialog.setSize(400, 300); // Adjusted size to make the dialog more compact
                registerDialog.setLocationRelativeTo(null);
                registerDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                registerDialog.setLayout(new GridBagLayout()); // Using GridBagLayout for better control over component placement

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components
                gbc.anchor = GridBagConstraints.WEST;

                JLabel[] labels = {
                        new JLabel("Name:"),
                        new JLabel("Password:"),
                        new JLabel("Confirm Password:"),
                        new JLabel("Phone Number:"),
                        new JLabel("Branch Name:"),
                        new JLabel("Type:")
                };
                JTextField[] textFields = {
                        new JTextField(15),
                        new JPasswordField(15),
                        new JPasswordField(15),
                        new JTextField(15),
                        new JTextField(15),
                        new JTextField(15),
                };

                for (int i = 0; i < labels.length; i++) {
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    registerDialog.add(labels[i], gbc);

                    gbc.gridx = 1;
                    registerDialog.add(textFields[i], gbc);
                }

                JButton confirmButton = new JButton("Confirm");
                gbc.gridx = 0;
                gbc.gridy = labels.length;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(textFields[4].getText().equals("UIC") || textFields[4].getText().equals("HKBU"))) {
                            JOptionPane.showMessageDialog(null, "Branch information is incorrect", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (!(textFields[5].getText().equals("C") || textFields[5].getText().equals("S"))) {
                            JOptionPane.showMessageDialog(null, "Account type information is incorrect", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        // Check the information on the interface: whether the password is valid, phone number, double password consistency
                        if (!textFields[1].getText().equals(textFields[2].getText())) {
                            JOptionPane.showMessageDialog(null, "Passwords do not match", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (!textFields[1].getText().matches("\\d{6}") || !textFields[2].getText().matches("\\d{6}")) {
                            JOptionPane.showMessageDialog(null, "Password must be 6 digits", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        if (!textFields[3].getText().matches("\\d{11}")) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please enter the correct phone number",
                                    "Result",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            return;
                        }


                        if (Register.check(textFields[3].getText()) >= 2) {
                            JOptionPane.showMessageDialog(null, "The limit for allowed registrations has been reached", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        Register r = new Register(textFields[0].getText(), textFields[1].getText(), textFields[3].getText(), textFields[4].getText(), textFields[5].getText());
                        String a = r.register();
                        JOptionPane.showMessageDialog(null, "Registration successfully！, your account number is " + a, "Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                registerDialog.add(confirmButton, gbc);

                registerDialog.setVisible(true);
            }
        });

        frame1.add(layeredPane);
        frame1.setVisible(true);
    }


    public void UserInterface(Account a) {
        JFrame frame1 = new JFrame("User Interface");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame1.setSize(1000, 800);
        frame1.setResizable(true);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(null);
        frame1.getContentPane().setBackground(new Color(240, 240, 240)); // Color
        ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg"); // Image name
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(frame1.getWidth(), frame1.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());
        frame1.getContentPane().add(backgroundLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 150)); // Set background color and increase transparency
        panel.setBounds(235, 270, 330, 170);// Adjust panel size and position to match the ATM screen area
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(3, 2, 10, 20)); // 3x2 grid with horizontal and vertical gaps
        backgroundLabel.add(panel);

        // Create buttons and add to the panel
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton checkInfoButton = new JButton("Personal Information");
        JButton changePasswordButton = new JButton("Change Password");

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(transferButton);
        panel.add(checkInfoButton);
        panel.add(changePasswordButton);


        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a deposit interface
                JFrame depositFrame = new JFrame("Deposit Interface");
                depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                depositFrame.setSize(1000, 800); // Increase the window size
                depositFrame.setResizable(false);
                depositFrame.setVisible(true);

                // Set background image
                ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(depositFrame.getWidth(), depositFrame.getHeight(), Image.SCALE_SMOOTH);
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundLabel.setBounds(0, 0, depositFrame.getWidth(), depositFrame.getHeight());
                depositFrame.getContentPane().add(backgroundLabel);

                backgroundLabel.setLayout(null); // Set layout manager to null for absolute positioning

                // Add amount label
                JLabel amountLabel = new JLabel("Please enter deposit amount:");
                amountLabel.setBounds(225, 220, 400, 50); // Adjust position and size
                amountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                amountLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(amountLabel);

                // Add amount text field
                JTextField amountTextField = new JTextField();
                amountTextField.setBounds(225, 280, 360, 50); // Adjust position and size
                backgroundLabel.add(amountTextField);

                // Add confirm button
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBounds(225, 360, 360, 50); // Adjust position and size
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String txt = amountTextField.getText();
                        if (!txt.matches("\\d+(\\.\\d+)?")) {
                            JOptionPane.showMessageDialog(null, "Amount is incorrect", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        String[] userList = Login.login(accountNoTextField.getText());
                        if (userList[4].equals("S")) {
                            sAccount as = new sAccount(userList[0], userList[1], userList[2], Double.parseDouble(userList[3]), userList[5], userList[6]);
                            String ans = as.deposite(Double.parseDouble(txt));
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        } else if (userList[4].equals("C")) {
                            cAccount ac = new cAccount(userList[0], userList[1], userList[2], Double.parseDouble(userList[3]), userList[5], userList[6]);
                            String ans = ac.deposite(Double.parseDouble(txt));
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                backgroundLabel.add(confirmButton);
            }



        });


        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a withdraw interface
                JFrame withdrawFrame = new JFrame("Withdraw Interface");
                withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                withdrawFrame.setSize(1000, 800); // Increase the window size
                withdrawFrame.setResizable(false);
                withdrawFrame.setVisible(true);

                // Set background image
                ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(withdrawFrame.getWidth(), withdrawFrame.getHeight(), Image.SCALE_SMOOTH);
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundLabel.setBounds(0, 0, withdrawFrame.getWidth(), withdrawFrame.getHeight());
                withdrawFrame.getContentPane().add(backgroundLabel);

                backgroundLabel.setLayout(null); // Set layout manager to null for absolute positioning

                // Add amount label
                JLabel amountLabel = new JLabel("Please enter the withdrawal amount:");
                amountLabel.setBounds(225, 220, 400, 50); // Adjust position and size
                amountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                amountLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(amountLabel);

                // Add amount text field
                JTextField amountTextField = new JTextField();
                amountTextField.setBounds(225, 280, 360, 50); // Adjust position and size
                backgroundLabel.add(amountTextField);

                // Add confirm button
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBounds(225, 360, 360, 50); // Adjust position and size
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String txt = amountTextField.getText();
                        if (!txt.matches("\\d+(\\.\\d+)?")) {
                            JOptionPane.showMessageDialog(null, "Amount is incorrect", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        String[] userList = Login.login(accountNoTextField.getText());

                        if (userList[4].equals("S")) {
                            sAccount as = new sAccount(userList[0], userList[1], userList[2], Double.parseDouble(userList[3]), userList[5], userList[6]);
                            String ans = as.withdraw(Double.parseDouble(txt));
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        } else if (userList[4].equals("C")) {
                            cAccount as = new cAccount(userList[0], userList[1], userList[2], Double.parseDouble(userList[3]), userList[5], userList[6]);
                            String ans = as.withdraw(Double.parseDouble(txt));
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                backgroundLabel.add(confirmButton);
            }
        });




        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a transfer interface
                JFrame transferFrame = new JFrame("Transfer");
                transferFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                transferFrame.setSize(1000, 800); // Adjust window size
                transferFrame.setResizable(false);
                transferFrame.setVisible(true);

                // Set background image
                ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(transferFrame.getWidth(), transferFrame.getHeight(), Image.SCALE_SMOOTH);
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundLabel.setBounds(0, 0, transferFrame.getWidth(), transferFrame.getHeight());
                transferFrame.getContentPane().add(backgroundLabel);

                backgroundLabel.setLayout(null); // Set layout manager to null for absolute positioning

                int verticalGap = 37;
                int initialYOffset = 213;

                // Add account name label
                JLabel accountNameLabel = new JLabel("Recipient's account name:");
                accountNameLabel.setBounds(255, initialYOffset, 350, 25); // Adjust position and size
                accountNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                accountNameLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(accountNameLabel);

                // Add account name text field
                JTextField toAccountNameTextField = new JTextField();
                toAccountNameTextField.setBounds(255, initialYOffset + 30, 300, 25); // Adjust position and size
                backgroundLabel.add(toAccountNameTextField);

                // Add account number label
                JLabel accountNoLabel = new JLabel("Recipient's account number:");
                accountNoLabel.setBounds(255, initialYOffset + verticalGap + 30, 400, 25); // Adjust position and size
                accountNoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                accountNoLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(accountNoLabel);

                // Add account number text field
                JTextField toAccountNoTextField = new JTextField();
                toAccountNoTextField.setBounds(255, initialYOffset + verticalGap + 60, 300, 25); // Adjust position and size
                backgroundLabel.add(toAccountNoTextField);

                // Add amount label
                JLabel amountLabel = new JLabel("Please enter the transfer amount:");
                amountLabel.setBounds(255, initialYOffset + 2 * verticalGap + 60, 400, 25); // Adjust position and size
                amountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                amountLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(amountLabel);

                // Add amount text field
                JTextField amountTextField = new JTextField();
                amountTextField.setBounds(255, initialYOffset + 2 * verticalGap + 90, 300, 25); // Adjust position and size
                backgroundLabel.add(amountTextField);

                // Add transfer button
                JButton transferButton = new JButton("Transfer");
                transferButton.setBounds(255, initialYOffset + 3 * verticalGap + 90, 140, 40); // Adjust position and size
                transferButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fromAccount = accountNoTextField.getText();
                        String[] a1 = Login.login(fromAccount);

                        String toAccount = toAccountNoTextField.getText();
                        String[] a2 = Login.login(toAccount);
                        if (a2.length == 0) {
                            JOptionPane.showMessageDialog(null, "Failed, please try again", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (fromAccount.equals(toAccount)) {
                            JOptionPane.showMessageDialog(null, "Invalid input", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        if (!a2[1].equals(toAccountNameTextField.getText())) {
                            JOptionPane.showMessageDialog(null, "Illegal input", "Result", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        Transfer transfer1 = new Transfer(a1[0], a2[0], toAccountNameTextField.getText(), a1[5], a2[5]);
                        String ans = transfer1.transfer(a1[5], a2[5], Double.parseDouble(a1[3]), Double.parseDouble(a2[3]), Double.parseDouble(amountTextField.getText()), a1[4], a2[4]);
                        JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                backgroundLabel.add(transferButton);

                // Add back button
                JButton backButton = new JButton("Back");
                backButton.setBounds(415, initialYOffset + 3 * verticalGap + 90, 140, 40); // Adjust position and size
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        transferFrame.dispose();
                    }
                });
                backgroundLabel.add(backButton);
            }
        });


        checkInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a check personal information interface
                JFrame queryFrame = new JFrame("Check Personal Information");
                queryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                queryFrame.setSize(1000, 800); // Increase the window size
                queryFrame.setResizable(false);
                queryFrame.setVisible(true);

                // Set background image
                ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(queryFrame.getWidth(), queryFrame.getHeight(), Image.SCALE_SMOOTH);
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundLabel.setBounds(0, 0, queryFrame.getWidth(), queryFrame.getHeight());
                queryFrame.getContentPane().add(backgroundLabel);

                backgroundLabel.setLayout(null); // Set layout manager to null for absolute positioning

                // Retrieve user information
                String[] userInfo = Login.login(accountNoTextField.getText());

                // Add name label
                JLabel firstNameLabel = new JLabel("Hello: " + userInfo[1]);
                firstNameLabel.setBounds(225, 220, 400, 50); // Adjust position and size
                firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                firstNameLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(firstNameLabel);
                //add accountType label
                JLabel accountTypeLabel = new JLabel("Your account type is: " + userInfo[4]);
                accountTypeLabel.setBounds(225, 320, 400, 50); // Adjust position and size
                accountTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                accountTypeLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(accountTypeLabel);
                // Add balance label
                JLabel amountLabel = new JLabel("Your balance is: " + userInfo[3]);
                amountLabel.setBounds(225, 260, 400, 50); // Adjust position and size
                amountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                amountLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(amountLabel);
                
                // Add phone number label
                JLabel phoneLabel = new JLabel("Your phone number is: " + userInfo[6]);
                phoneLabel.setBounds(225, 290, 400, 50); // Adjust position and size
                phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                phoneLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(phoneLabel);
             // Add branchName label
                JLabel branchNameLabel = new JLabel("Your branch name is: " + userInfo[5]);
                branchNameLabel.setBounds(225, 350, 400, 50); // Adjust position and size
                branchNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                branchNameLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(branchNameLabel);

                // Add back button
                JButton backButton = new JButton("Back");
                backButton.setBounds(225, 410, 360, 50); // Adjust position and size
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        queryFrame.dispose();
                    }
                });
                backgroundLabel.add(backButton);
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a change password interface
                JFrame changePasswordFrame = new JFrame("Change Password");
                changePasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                changePasswordFrame.setSize(1000, 800); // Adjust window size
                changePasswordFrame.setResizable(false);
                changePasswordFrame.setVisible(true);

                // Set background image
                ImageIcon backgroundIcon = new ImageIcon("src/Backgroun.jpg");
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(changePasswordFrame.getWidth(), changePasswordFrame.getHeight(), Image.SCALE_SMOOTH);
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundLabel.setBounds(0, 0, changePasswordFrame.getWidth(), changePasswordFrame.getHeight());
                changePasswordFrame.getContentPane().add(backgroundLabel);

                backgroundLabel.setLayout(null); // Set layout manager to null for absolute positioning

                int verticalGap = 35; // Set a smaller gap for more compact layout
                int initialYOffset = 223;

                // Add old password label
                JLabel oldPasswordLabel = new JLabel("Please enter the old password:");
                oldPasswordLabel.setBounds(260, initialYOffset, 300, 25); // Adjust position and size
                oldPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                oldPasswordLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(oldPasswordLabel);

                // Add old password text field
                JTextField oldPasswordTextField = new JTextField();
                oldPasswordTextField.setBounds(260, initialYOffset + 30, 300, 25); // Adjust position and size
                backgroundLabel.add(oldPasswordTextField);

                // Add new password label
                JLabel newPasswordLabel = new JLabel("Please enter the new password:");
                newPasswordLabel.setBounds(260, initialYOffset + verticalGap + 30, 300, 25); // Adjust position and size
                newPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                newPasswordLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(newPasswordLabel);

                // Add new password text field
                JTextField newPasswordTextField = new JTextField();
                newPasswordTextField.setBounds(260, initialYOffset + verticalGap + 60, 300, 25); // Adjust position and size
                backgroundLabel.add(newPasswordTextField);

                // Add confirm password label
                JLabel confirmPasswordLabel = new JLabel("Please confirm the new password:");
                confirmPasswordLabel.setBounds(260, initialYOffset + 2 * verticalGap + 60, 300, 25); // Adjust position and size
                confirmPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
                confirmPasswordLabel.setForeground(Color.BLACK); // Set font color
                backgroundLabel.add(confirmPasswordLabel);

                // Add confirm password text field
                JTextField confirmPasswordTextField = new JTextField();
                confirmPasswordTextField.setBounds(260, initialYOffset + 2 * verticalGap + 90, 300, 25); // Adjust position and size
                backgroundLabel.add(confirmPasswordTextField);

                // Add confirm button
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBounds(260, initialYOffset + 3 * verticalGap + 90, 140, 40); // Adjust position and size
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] a = Login.login(accountNoTextField.getText());
                        if (a[4].equals("S")) {
                            sAccount as = new sAccount(a[0], a[1], a[2], Double.parseDouble(a[3]), a[5], a[6]);
                            String ans = as.changePassword(oldPasswordTextField.getText(), newPasswordTextField.getText(), confirmPasswordTextField.getText());
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        } else if (a[4].equals("C")) {
                            cAccount ac = new cAccount(a[0], a[1], a[2], Double.parseDouble(a[3]), a[5], a[6]);
                            String ans = ac.changePassword(oldPasswordTextField.getText(), newPasswordTextField.getText(), confirmPasswordTextField.getText());
                            JOptionPane.showMessageDialog(null, ans, "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                backgroundLabel.add(confirmButton);

                // Add cancel button
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBounds(420, initialYOffset + 3 * verticalGap + 90, 140, 40); // Adjust position and size
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        changePasswordFrame.dispose();
                    }
                });
                backgroundLabel.add(cancelButton);
            }
        });


        // Call revalidate and repaint to ensure the panel is correctly displayed
        frame1.revalidate();
        frame1.repaint();

    }


    public static void main(String[] args) {
        ATMInterface a = new ATMInterface();
        a.initializeUI();
    }

}

