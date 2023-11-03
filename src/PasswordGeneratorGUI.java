import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorGUI extends JFrame {
    private JTextField passwordField;

    public PasswordGeneratorGUI() {
        super("Password Generator");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        passwordField = new JTextField(20);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);  // Set background color to yellow
        panel.add(passwordField);
        panel.add(generateButton);

        add(panel);

        setVisible(true);
    }

    private void generatePassword() {
        int length = 8;
        String password = PasswordGenerator.generateStrongPassword(length);
        passwordField.setText(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGeneratorGUI();
            }
        });
    }
}

class PasswordGenerator {
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@,$,#,%,&,*_,-";

    public static String generateStrongPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        for (int i = 4; i < length; i++) {
            String allCharacters = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;
            int randomIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }

        return password.toString();
    }
}
