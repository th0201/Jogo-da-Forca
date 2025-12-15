import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("JOGO DA FORCA");

        // 🔹 Tela cheia sempre
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(10, 25, 58));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JPanel painelForca = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6));
                g2.setColor(new Color(139, 69, 19));

                // 🔹 Forca maior e mais próxima das escritas
                int width = getWidth();
                int height = getHeight();

                int baseX = 50; // mais à esquerda
                int baseY = height - 50; // base mais baixa

                g2.drawLine(baseX, baseY, baseX + 300, baseY); // base maior
                g2.drawLine(baseX + 75, baseY, baseX + 75, baseY - 400); // tronco maior
                g2.drawLine(baseX + 75, baseY - 400, baseX + 275, baseY - 400); // viga horizontal maior
                g2.drawLine(baseX + 75, baseY - 300, baseX + 150, baseY - 400); // corda
                g2.drawLine(baseX + 275, baseY - 400, baseX + 275, baseY - 360); // corda curta
                g2.drawOval(baseX + 265, baseY - 360, 40, 40); // cabeça maior
            }
        };
        painelForca.setOpaque(false);
        painelForca.setPreferredSize(new Dimension(400, 600)); // aumenta largura para ficar mais perto do texto

        // 🔹 Painel de texto centralizado
        JPanel painelTexto = new JPanel(new GridBagLayout());
        painelTexto.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel title = new JLabel("Jogo da Forca", SwingConstants.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 72)); // maior por causa da tela cheia
        title.setForeground(new Color(180, 255, 180));
        gbc.gridy = 0;
        painelTexto.add(title, gbc);

        JLabel subtitulo = new JLabel("Explore conhecimentos enquanto joga", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        subtitulo.setForeground(new Color(200, 255, 200));
        gbc.gridy = 1;
        painelTexto.add(subtitulo, gbc);

        JButton iniciar = new JButton("Iniciar");
        iniciar.setPreferredSize(new Dimension(300, 80));
        iniciar.setFont(new Font("SansSerif", Font.BOLD, 28));
        iniciar.setBackground(new Color(0, 51, 102));
        iniciar.setForeground(Color.WHITE);
        iniciar.setFocusPainted(false);
        iniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iniciar.setBackground(new Color(20, 80, 150));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                iniciar.setBackground(new Color(0, 51, 102));
            }
        });
        iniciar.addActionListener(e -> {
            TelaNomeJogador tn = new TelaNomeJogador();
            tn.setVisible(true);
            this.dispose();
        });
        gbc.gridy = 2;
        painelTexto.add(iniciar, gbc);

        // 🔹 Painel central que centraliza verticalmente
        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.setOpaque(false);
        painelCentro.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        painelCentro.add(painelTexto, BorderLayout.CENTER);
        painelCentro.add(Box.createVerticalGlue(), BorderLayout.SOUTH);

        // 🔹 Layout final
        painel.add(painelForca, BorderLayout.WEST);
        painel.add(painelCentro, BorderLayout.CENTER);

        add(painel);
    }
}
