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

        JTextArea boneco = new JTextArea();
        boneco.setEditable(false);
        boneco.setFont(new Font("Monospaced", Font.PLAIN, 18));
        boneco.setBackground(Color.WHITE);
        boneco.setText(
                "   +---+\n" +
                "   |   |\n" +
                "   O   |\n" +
                "  /|\\  |\n" +
                "  / \\  |\n" +
                "       |\n" +
                "========="
        );
        painelBoneco.add(boneco);
        add(painelBoneco, BorderLayout.CENTER);

        
        JButton jogarBtn = new JButton("Jogar");
        jogarBtn.setFont(new Font("Arial", Font.BOLD, 22));
        jogarBtn.setBackground(new Color(0, 102, 204));
        jogarBtn.setForeground(Color.WHITE);
        jogarBtn.setFocusPainted(false);
        jogarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jogarBtn.setPreferredSize(new Dimension(150, 50));
        jogarBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jogarBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 80, 160), 2, true));

        jogarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InterfaceGUI();
            }
        });

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(jogarBtn);

        add(painelBotao, BorderLayout.SOUTH);

        setVisible(true);
    }
}
