
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

