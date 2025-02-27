

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Start {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure GUI creation is done in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set JTattoo look and feel
                UIManager.setLookAndFeel(new AluminiumLookAndFeel());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Initialize the ATM interface
            ATMInterface a = new ATMInterface();
            a.initializeUI();

            // Create and display the advertisement frame
            createAdvertisementFrame();
        });
    }

    private static void createAdvertisementFrame() {
        JFrame frame = new JFrame("Advertisement");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(440, 440);

        // Load the image resource
        ImageIcon imageIcon = new ImageIcon("src/advertisement.png"); // Ensure the path is correct
        JLabel label = new JLabel(imageIcon);
        frame.add(label);

        // Create a button and set the click event
        JButton button = new JButton("Learn More");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Open the specified URL in the default browser
                    Desktop.getDesktop().browse(new URI("https://y.qq.com/n/ryqq/albumDetail/002HgJfp494Uax"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}



/*

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Start {
    public static void main(String[] args) {
        ATMInterface a=new ATMInterface();
        a.initializeUI();

        JFrame frame = new JFrame("advertisement");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(440, 440);

        // 创建图片标签
        ImageIcon imageIcon = new ImageIcon("src/advertisement.png"); // 替换为你的图片路径
        JLabel label = new JLabel(imageIcon);
        frame.add(label);

        // 创建按钮并设置点击事件
        JButton button = new JButton("Learn More");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://y.qq.com/n/ryqq/albumDetail/002HgJfp494Uax")); // 替换为你要打开的网页URL
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

*/
