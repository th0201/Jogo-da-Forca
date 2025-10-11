import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaInicial extends JFrame {
    public TelaInicial() {
        setTitle("ForcaPetri");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        
        JLabel titulo = new JLabel("Jogo da Forca", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 38));
        titulo.setForeground(new Color(0, 102, 204));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        
        JPanel painelBoneco = new JPanel(new GridBagLayout());
        painelBoneco.setBackground(Color.WHITE);

        
