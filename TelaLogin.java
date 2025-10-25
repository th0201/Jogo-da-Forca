
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JButton registerBtn;

    private static Map<String, String> users = new HashMap<>();

    public TelaLogin() {
        setTitle("Forca Sustentável — Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Painel com fundo decorado
        JPanel panelPrincipal = new JPanel() {
            private Image img;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img == null) {
                    img = new ImageIcon("resources/fundo_login.jpg").getImage();
                }
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelPrincipal.setLayout(new GridBagLayout());

        // Painel transparente/semitransparente para os componentes
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(new Color(255, 255, 255, 180)); // branco semi-transparente
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Acesse sua conta"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Usuário:");
        JLabel passLabel = new JLabel("Senha:");
        userField = new JTextField(15);
        passField = new JPasswordField(15);

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Registrar");

        // cores dos botões
        loginBtn.setBackground(new Color(76, 175, 80)); // verde
        loginBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(new Color(33, 150, 243)); // azul
        registerBtn.setForeground(Color.WHITE);

        // posição dos componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        painelFormulario.add(userLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelFormulario.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        painelFormulario.add(passLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelFormulario.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelFormulario.add(registerBtn, gbc);
        gbc.gridx = 1;
        painelFormulario.add(loginBtn, gbc);

        panelPrincipal.add(painelFormulario);
        getContentPane().add(panelPrincipal);

        registerBtn.addActionListener(e -> doRegister());
        loginBtn.addActionListener(e -> doLogin());
    }

    private void doRegister() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário e senha não podem ser vazios.");
            return;
        }
        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Usuário já existe.");
        } else {
            users.put(username, password);
            JOptionPane.showMessageDialog(this, "Registrado com sucesso! Faça login.");
        }
    }

    private void doLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        if (users.containsKey(username) && users.get(username).equals(password)) {
            SelecaoTema temaSel = new SelecaoTema(username);
            temaSel.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
        }
    }
}
