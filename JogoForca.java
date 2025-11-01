import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class JogoForca extends JFrame {

    private String username;
    private String tema;
    private String palavraSecreta;
    private String dicaAtual;
    private Set<Character> letrasCorretas = new HashSet<>();
    private Set<Character> letrasErradas = new HashSet<>();
    private int tentativasErradas = 0;
    private final int MAX_ERROS = 6;

    private JLabel labelPalavra;
    private JLabel labelDica;
    private JLabel labelErros;
    private JPanel painelBoneco;
    private JPanel painelLetras;
    private JButton[] botoesLetra = new JButton[26];
    private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Map<String, List<String>> palavrasPorTema = new HashMap<>();
    private static final Map<String, List<String>> dicasPorTema = new HashMap<>();

    static {
        palavrasPorTema.put("Meio Ambiente", List.of("SUSTENTABILIDADE", "ECOSSISTEMA", "RECURSO", "BIOMA", "CONSERVACAO", "BIODIVERSIDADE"));
        dicasPorTema.put("Meio Ambiente", List.of(
                "Uso responsável dos recursos naturais.",
                "Conjunto de seres vivos e seu ambiente.",
                "Algo que a natureza oferece para nosso uso.",
                "Grande conjunto de vida com características próprias.",
                "Ato de proteger e preservar a natureza.",
                "Variedade de formas de vida existentes."
        ));

        palavrasPorTema.put("Energia Renovável", List.of("SOLAR", "EOLICO", "HIDROELETRICA", "GEOTERMIA", "BIOMASSA", "HIDROGENIO"));
        dicasPorTema.put("Energia Renovável", List.of(
                "Energia obtida a partir da luz do sol.",
                "Energia obtida pela força dos ventos.",
                "Energia gerada através da água em movimento.",
                "Energia obtida a partir do calor interno da Terra.",
                "Energia gerada a partir de matéria orgânica.",
                "Elemento químico leve e usado como combustível limpo."
        ));

        palavrasPorTema.put("Reciclagem", List.of("PAPEL", "PLASTICO", "VIDRO", "METAL", "COMPOSTAGEM", "SEPARACAO"));
        dicasPorTema.put("Reciclagem", List.of(
                "Material feito de celulose.",
                "Material derivado do petróleo, muito usado em embalagens.",
                "Material transparente que pode ser reciclado infinitamente.",
                "Material condutor de eletricidade e reciclável.",
                "Processo biológico que transforma resíduos orgânicos em adubo.",
                "Ato de dividir o lixo conforme seu tipo."
        ));

        palavrasPorTema.put("Biodiversidade", List.of("ESPECIE", "ECOSSISTEMA", "HABITAT", "GENETICA", "EVOLUCAO", "INTERACAO"));
        dicasPorTema.put("Biodiversidade", List.of(
                "Conjunto de organismos com características semelhantes.",
                "Conjunto de seres vivos e ambiente físico.",
                "Local onde uma espécie vive naturalmente.",
                "Relacionado ao DNA e hereditariedade.",
                "Processo de mudança das espécies ao longo do tempo.",
                "Relação entre seres vivos de um ambiente."
        ));

        palavrasPorTema.put("Mudança Climática", List.of("AQUECIMENTO", "GEE", "EMISSAO", "DERRETIMENTO", "CLIMA", "ATMOSFERA"));
        dicasPorTema.put("Mudança Climática", List.of(
                "Aumento da temperatura média global.",
                "Gases que intensificam o efeito estufa.",
                "Liberação de substâncias poluentes no ar.",
                "Processo causado pelo aumento da temperatura global.",
                "Condições meteorológicas de uma região.",
                "Camada gasosa que envolve a Terra."
        ));
    }

    public JogoForca(String username, String tema) {
        this.username = username;
        this.tema = tema;
        setTitle("JOGO DA FORCA — " + tema + " — Usuário: " + username);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        escolherPalavra();
        initComponents();
    }

    private void escolherPalavra() {
        List<String> lista = palavrasPorTema.get(tema);
        List<String> dicas = dicasPorTema.get(tema);
        Random rand = new Random();
        int index = rand.nextInt(lista.size());
        palavraSecreta = lista.get(index).toUpperCase();
        dicaAtual = dicas.get(index);
    }

    private void initComponents() {
        String urlImagem = switch (tema) {
            case "Meio Ambiente" ->
                "https://i.pinimg.com/736x/de/5f/d4/de5fd462e425813b6aaf45440d737e4e.jpg";
            case "Energia Renovável" ->
                "https://i.pinimg.com/1200x/57/55/69/5755692199474101ca2bcad35090e780.jpg";
            case "Reciclagem" ->
                "https://i.pinimg.com/736x/06/f7/02/06f7024181c60c04c0de115235595358.jpg";
            case "Biodiversidade" ->
                "https://i.pinimg.com/736x/15/a6/d1/15a6d19a47064878a30b6d3c02684450.jpg";
            case "Mudança Climática" ->
                "https://i.pinimg.com/736x/99/9a/f3/999af354c5344e0ff41503a35bdf1592.jpg";
            default ->
                "https://i.imgur.com/wTzjPbd.jpg";
        };

        PainelDeFundo mainPanel = new PainelDeFundo(urlImagem);
        mainPanel.setLayout(new BorderLayout());


        labelPalavra = new JLabel(formataPalavra(), SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            
                g2d.setColor(Color.BLACK);
                g2d.drawString(getText(), x + 2, y + 2);
                
                g2d.setColor(Color.WHITE);
                g2d.drawString(getText(), x, y);
                g2d.dispose();
            }
        };
        labelPalavra.setFont(new Font("Impact", Font.BOLD, 48));
        labelPalavra.setOpaque(false);

        
        labelDica = new JLabel("Dica: " + dicaAtual, SwingConstants.CENTER);
        labelDica.setFont(new Font("SansSerif", Font.BOLD, 22));
        labelDica.setForeground(Color.BLACK);
        labelDica.setOpaque(true);
        labelDica.setBackground(new Color(255, 255, 255, 160)); // leve transparência
        labelDica.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

        
        labelErros = new JLabel("Erros: ");
        labelErros.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelErros.setForeground(Color.WHITE);

        // Boneco
        painelBoneco = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharBoneco(g, tentativasErradas);
            }
        };
        painelBoneco.setOpaque(false);
        painelBoneco.setPreferredSize(new Dimension(300, 400));

        // Letras
        painelLetras = new JPanel(new GridLayout(2, 14, 5, 5));
        painelLetras.setOpaque(false);
        for (int i = 0; i < 26; i++) {
            char c = alfabeto.charAt(i);
            JButton btn = new JButton(String.valueOf(c));
            botoesLetra[i] = btn;
            btn.setFont(new Font("Arial Black", Font.BOLD, 16));
            btn.setBackground(new Color(240, 240, 240));
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                JButton origem = (JButton) e.getSource();
                origem.setEnabled(false);
                tentarLetra(origem.getText().charAt(0));
            });
            painelLetras.add(btn);
        }

        // Botão "Sair"
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSair.setForeground(Color.RED);
        btnSair.setBackground(new Color(255, 220, 220));
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja sair do jogo?",
                    "Sair",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        painelLetras.add(btnSair);

        // Botão "Menu" para voltar à seleção de tema
        JButton btnMenu = new JButton("Menu");
        btnMenu.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnMenu.setForeground(Color.BLUE);
        btnMenu.setBackground(new Color(220, 220, 255));
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja voltar ao menu de seleção de tema?",
                    "Menu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // fecha a janela atual
                new SelecaoTema(username).setVisible(true); // abre o menu
            }
        });
        painelLetras.add(btnMenu);

    // Painel superior
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        topPanel.setOpaque(false);
        topPanel.add(labelPalavra);
        topPanel.add(labelDica);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(painelLetras, BorderLayout.CENTER);
        bottomPanel.add(labelErros, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(painelBoneco, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private String formataPalavra() {
        StringBuilder sb = new StringBuilder();
        for (char c : palavraSecreta.toCharArray()) {
            sb.append(letrasCorretas.contains(c) ? c + " " : "_ ");
        }
        return sb.toString().trim();
    }

    private void tentarLetra(char letra) {
        if (letrasCorretas.contains(letra) || letrasErradas.contains(letra)) {
            return;
        }

        if (palavraSecreta.indexOf(letra) >= 0) {
            letrasCorretas.add(letra);
        } else {
            letrasErradas.add(letra);
            tentativasErradas++;
        }

        labelPalavra.setText(formataPalavra());
        labelErros.setText("Erros (" + letrasErradas.size() + "): " + letrasErradas);
        painelBoneco.repaint();
        checkEstado();
    }

    private void checkEstado() {
        boolean ganhou = palavraSecreta.chars().allMatch(c -> letrasCorretas.contains((char) c));

        if (ganhou) {
            JOptionPane.showMessageDialog(this, "Parabéns, " + username + "! A palavra era: " + palavraSecreta);
            iniciarProximaPalavraMesmoTema();
        } else if (tentativasErradas >= MAX_ERROS) {
            JOptionPane.showMessageDialog(this, "Você perdeu! A palavra era: " + palavraSecreta);
            iniciarProximaPalavraMesmoTema();
        }
    }

    private void iniciarProximaPalavraMesmoTema() {
        letrasCorretas.clear();
        letrasErradas.clear();
        tentativasErradas = 0;
        for (JButton btn : botoesLetra) {
            btn.setEnabled(true);
        }
        escolherPalavra();
        labelPalavra.setText(formataPalavra());
        labelErros.setText("Erros: ");
        labelDica.setText("Dica: " + dicaAtual);
        painelBoneco.repaint();
    }

    private void desenharBoneco(Graphics g, int erros) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Base com relevo
        GradientPaint grama = new GradientPaint(10, 350, new Color(34, 139, 34), 230, 350, new Color(0, 100, 0));
        g2.setPaint(grama);
        g2.fillRoundRect(10, 345, 220, 15, 10, 10);

        //Tronco com gradiente madeira
        GradientPaint madeiraVert = new GradientPaint(45, 50, new Color(139, 69, 19), 55, 350, new Color(101, 45, 0));
        GradientPaint madeiraHori = new GradientPaint(45, 50, new Color(160, 82, 45), 165, 60, new Color(101, 45, 0));
        g2.setPaint(madeiraVert);
        g2.fillRoundRect(45, 50, 10, 300, 10, 10);
        g2.setPaint(madeiraHori);
        g2.fillRoundRect(45, 50, 120, 10, 10, 10);

        //Corda
        g2.setStroke(new BasicStroke(4));
        g2.setColor(new Color(150, 75, 0)); // marrom claro
        g2.drawLine(150, 50, 150, 90);

        //Boneco com traços arredondados
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (erros >= 1) {
            g2.drawOval(130, 90, 40, 40); // cabeça

        }
        if (erros >= 2) {
            g2.drawLine(150, 130, 150, 200); // tronco

        }
        if (erros >= 3) {
            g2.drawLine(150, 140, 120, 180); // braço esquerdo

        }
        if (erros >= 4) {
            g2.drawLine(150, 140, 180, 180); // braço direito

        }
        if (erros >= 5) {
            g2.drawLine(150, 200, 120, 260); // perna esquerda

        }
        if (erros >= 6) {
            g2.drawLine(150, 200, 180, 260); // perna direita
        }
        //Rosto triste se perdeu
        if (erros >= 6) {
            g2.drawLine(142, 100, 148, 105); // olho esquerdo
            g2.drawLine(158, 100, 152, 105); // olho direito
            g2.drawArc(140, 110, 20, 10, 0, -180); // boca triste
        }

        //Sombra da base
        g2.setColor(new Color(0, 0, 0, 50));
        g2.setStroke(new BasicStroke(8));
        g2.drawLine(50, 350, 200, 350);
    }

}
