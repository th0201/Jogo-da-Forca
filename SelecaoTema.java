
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelecaoTema extends JFrame {

    private String username;
    private JComboBox<String> temaCombo;
    private JButton iniciarBtn;

    public SelecaoTema(String username) {
        this.username = username;
        setTitle("Selecione Tema Sustentável");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

private void initComponents() {
        JLabel label = new JLabel("Escolha o tema:");
        String[] temas = {"Meio Ambiente", "Energia Renovável", "Reciclagem", "Biodiversidade", "Mudança Climática"};
        temaCombo = new JComboBox<>(temas);
        iniciarBtn = new JButton("Iniciar Forca");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(temaCombo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(iniciarBtn, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);

        iniciarBtn.addActionListener(e -> iniciarForca());
    }

    private void iniciarForca() {
        String tema = (String) temaCombo.getSelectedItem();
        JogoForca forca = new JogoForca(username, tema);
        forca.setVisible(true);
        this.dispose();
    }
}
