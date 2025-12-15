import javax.swing.*;
import java.awt.*;

public class TelaNomeJogador extends JFrame {

    public TelaNomeJogador() {
        setTitle("Jogo da Forca - Nome do Jogador");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setBackground(new Color(10, 25, 58)); // mesmo azul escuro
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        JLabel title = new JLabel("Digite Seu Nome", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 32));
        title.setForeground(new Color(180, 255, 180)); // verde suave
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        p.add(title, gbc);

        // Campo de texto
        JTextField nomeField = new JTextField(18);
        nomeField.setFont(new Font("SansSerif", Font.PLAIN, 22));
        gbc.gridy = 1;
        p.add(nomeField, gbc);

        // Botão continuar
        JButton continuarBtn = new JButton("Continuar");
        continuarBtn.setPreferredSize(new Dimension(180, 50));
        continuarBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        continuarBtn.setBackground(new Color(0, 51, 102));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setFocusPainted(false);
        continuarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 20, 20, 20);
        p.add(continuarBtn, gbc);

        // Ação do botão
        continuarBtn.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            if (!nome.isEmpty()) {
                SelecaoTema tema = new SelecaoTema(nome);
                tema.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Digite seu nome!");
            }
        });

        add(p);
    }
}
