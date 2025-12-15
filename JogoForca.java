import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
        palavrasPorTema.put("Meio Ambiente", List.of("SUSTENTABILIDADE", "ECOSSISTEMA", "CONSERVACAO",
                "BIODIVERSIDADE", "DESMATAMENTO", "RECURSO", "PRESERVAÇÃO", "BIOMA", "FLORESTA", "FAUNA", "AR"));

        dicasPorTema.put("Meio Ambiente", List.of(
                "Uso responsável dos recursos naturais.",
                "Conjunto de seres vivos e seu ambiente.",
                "Ato de proteger e cuidar do meio ambiente.",
                "Variedade de formas de vida existentes em uma região.",
                "Corte ou remoção excessiva de árvores.",
                "Algo que a natureza oferece para nosso uso.",
                "Ação de proteger e preservar algo natural.",
                "Grande conjunto de vida com características próprias.",
                "Grande área coberta por árvores e rica em vida.",
                "Conjunto de animais que vivem em uma região.",
                "Componente essencial para a vida e que pode ser poluído."));

        palavrasPorTema.put("Energia Renovável", List.of("SOLAR", "EOLICO", "HIDROELETRICA", "GEOTERMIA", "BIOMASSA",
                "HIDROGENIO", "PLANTA", "AGUA", "VENTO", "BIOGAS"));
        dicasPorTema.put("Energia Renovável", List.of(
                "Energia obtida a partir da luz do sol.",
                "Energia obtida pela força dos ventos.",
                "Energia gerada através da água em movimento.",
                "Energia obtida a partir do calor interno da Terra.",
                "Energia gerada a partir de matéria orgânica.",
                "Elemento químico leve e usado como combustível limpo.",
                "Usada como matéria orgânica para produzir energia.",
                "Recurso usado para gerar energia em usinas.",
                "Movimento do ar usado para gerar energia.",
                "Gás produzido pela decomposição de resíduos orgânicos."));

        palavrasPorTema.put("Reciclagem", List.of("PAPEL", "PLASTICO", "VIDRO", "METAL", "COMPOSTAGEM", "SEPARACAO",
                "COLETA", "LATA", "REUSO", "DESCARTE"));
        dicasPorTema.put("Reciclagem", List.of(
                "Material feito de celulose.",
                "Material derivado do petróleo, muito usado em embalagens.",
                "Material transparente que pode ser reciclado infinitamente.",
                "Material condutor de eletricidade e reciclável.",
                "Processo biológico que transforma resíduos orgânicos em adubo.",
                "Ato de dividir o lixo conforme seu tipo.",
                "Processo de recolher o lixo para reciclagem.",
                "Recipiente metálico muito reciclado, como as de refrigerante.",
                "Ação de usar um objeto novamente antes de jogá-lo fora.",
                "Ação de jogar fora algo que não será mais usado."));

        palavrasPorTema.put("Biodiversidade", List.of("ESPECIE", "ECOSSISTEMA", "HABITAT", "GENETICA", "EVOLUCAO",
                "INTERACAO", "ADAPTACAO", "FLORA", "DIVERSIDADE"));
        dicasPorTema.put("Biodiversidade", List.of(
                "Conjunto de organismos com características semelhantes.",
                "Conjunto de seres vivos e ambiente físico.",
                "Local onde uma espécie vive naturalmente.",
                "Relacionado ao DNA e hereditariedade.",
                "Processo de mudança das espécies ao longo do tempo.",
                "Relação entre seres vivos de um ambiente.",
                "Mudanças que ajudam uma espécie a sobreviver no ambiente.",
                "Conjunto de plantas que vivem em um ambiente.",
                "Variedade de formas de vida em um local."));

        palavrasPorTema.put("Mudança Climática", List.of("AQUECIMENTO", "EMISSAO", "DERRETIMENTO", "CLIMA", "ATMOSFERA",
                "CHUVA", "TEMPERATURA", "SECA"));
        dicasPorTema.put("Mudança Climática", List.of(
                "Aumento da temperatura média global.",
                "Liberação de substâncias poluentes no ar.",
                "Processo causado pelo aumento da temperatura global.",
                "Condições meteorológicas de uma região.",
                "Camada gasosa que envolve a Terra.",
                "Precipitação que pode ficar irregular com as mudanças do clima.",
                "Medida do calor que está aumentando no planeta.",
                "Período longo sem chuvas, afetando plantas e rios."));

        palavrasPorTema.put("Astronomia", List.of(
                "PLANETA", "ESTRELA", "GALAXIA", "COMETA", "LUA",
                "ECLIPSE", "ORBITA", "ASTRONAUTA", "TELESCOPIO", "METEORO"));
        dicasPorTema.put("Astronomia", List.of(
                "Corpo celeste que orbita uma estrela.",
                "Corpo celeste que emite luz própria.",
                "Conjunto gigante de estrelas, poeira e gás.",
                "Corpo celeste com cauda brilhante.",
                "Satélite natural da Terra.",
                "Quando um astro é ocultado por outro.",
                "Caminho que um corpo celeste percorre.",
                "Pessoa que viaja ao espaço.",
                "Instrumento para observar o céu.",
                "Fragmento que entra na atmosfera terrestre."));

        palavrasPorTema.put("História do Brasil", List.of(
                "INDEPENDENCIA", "BANDEIRA", "IMPERADOR", "QUILOMBO", "DESCOBRIMENTO",
                "COLONIZACAO", "CONSTITUICAO", "REVOLUCAO", "REPUBLICA", "HERANCA"));
        dicasPorTema.put("História do Brasil", List.of(
                "Ano em que o Brasil se libertou de Portugal.",
                "Símbolo nacional.",
                "Chefe de Estado durante o período monárquico.",
                "Comunidade de escravos fugitivos.",
                "Evento de chegada dos portugueses.",
                "Período de ocupação europeia.",
                "Lei máxima do país.",
                "Mudança radical no governo ou sociedade.",
                "Forma de governo após a monarquia.",
                "Bens ou tradições passadas de geração em geração."));

        palavrasPorTema.put("Biomas", List.of(
                "AMAZONIA", "CERRADO", "PANTANAL", "CAATINGA", "MATAATLANTICA",
                "TUNDRA", "DESERTO", "SAVANA", "FLORESTA", "PAMPAS"));
        dicasPorTema.put("Biomas", List.of(
                "Maior floresta tropical do mundo.",
                "Savana brasileira, biodiversidade rica.",
                "Maior área alagada do planeta.",
                "Bioma árido do nordeste brasileiro.",
                "Floresta litorânea brasileira.",
                "Bioma frio, vegetação rasteira.",
                "Região seca com pouca vegetação.",
                "Planície tropical com gramíneas.",
                "Área densamente arborizada.",
                "Campos do sul da América do Sul."));
    }

    public JogoForca(String username, String tema) {
        this.username = username;
        this.tema = tema;

        setTitle("Forca Sustentável — " + tema + " — Usuário: " + username);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            case "Meio Ambiente" -> "https://i.pinimg.com/736x/de/5f/d4/de5fd462e425813b6aaf45440d737e4e.jpg";
            case "Energia Renovável" -> "https://i.pinimg.com/1200x/57/55/69/5755692199474101ca2bcad35090e780.jpg";
            case "Reciclagem" -> "https://i.pinimg.com/736x/06/f7/02/06f7024181c60c04c0de115235595358.jpg";
            case "Biodiversidade" -> "https://i.pinimg.com/736x/15/a6/d1/15a6d19a47064878a30b6d3c02684450.jpg";
            case "Mudança Climática" -> "https://i.pinimg.com/736x/99/9a/f3/999af354c5344e0ff41503a35bdf1592.jpg";
            case "Astronomia" -> "https://i.pinimg.com/736x/9a/f8/28/9af82858e3c5a0bb60e33321b7c901d3.jpg";
            case "História do Brasil" -> "https://i.pinimg.com/1200x/11/7c/85/117c85927e0a309628a6daee17412b0d.jpg";
            case "Biomas" -> "https://i.pinimg.com/736x/60/92/1d/60921d7ed31249471aecbc429ad00ac4.jpg";
            default -> "https://i.imgur.com/wTzjPbd.jpg";
        };

        PainelDeFundo mainPanel = new PainelDeFundo(urlImagem);
        mainPanel.setLayout(new BorderLayout());

        // 🔹 Palavra (com sombra)
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

        // 🔹 Dica
        labelDica = new JLabel("💡 Dica: " + dicaAtual, SwingConstants.CENTER);
        labelDica.setFont(new Font("SansSerif", Font.BOLD, 22));
        labelDica.setForeground(Color.BLACK);
        labelDica.setOpaque(true);
        labelDica.setBackground(new Color(255, 255, 255, 160));
        labelDica.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

        // 🔹 Erros
        labelErros = new JLabel("Erros: ");
        labelErros.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelErros.setForeground(Color.WHITE);

        // 🔹 Boneco
        painelBoneco = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharBoneco(g, tentativasErradas);
            }
        };
        painelBoneco.setOpaque(false);
        painelBoneco.setPreferredSize(new Dimension(300, 400));

        // 🔹 Letras
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

        // Botões "Sair" e "Menu"
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSair.setForeground(Color.RED);
        btnSair.setBackground(new Color(255, 220, 220));
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair do jogo?",
                    "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION)
                System.exit(0);
        });
        painelLetras.add(btnSair);

        JButton btnMenu = new JButton("Menu");
        btnMenu.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnMenu.setForeground(Color.BLUE);
        btnMenu.setBackground(new Color(220, 220, 255));
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Deseja voltar ao menu de seleção de tema?", "Menu", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new SelecaoTema(username).setVisible(true);
            }
        });
        painelLetras.add(btnMenu);

        // 🔹 Painel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // palavra centralizada
        JPanel palavraPanel = new JPanel(new GridBagLayout());
        palavraPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        palavraPanel.add(labelPalavra, gbc);

        topPanel.add(palavraPanel, BorderLayout.CENTER);
        topPanel.add(labelDica, BorderLayout.NORTH);

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
        if (letrasCorretas.contains(letra) || letrasErradas.contains(letra))
            return;

        if (palavraSecreta.indexOf(letra) >= 0)
            letrasCorretas.add(letra);
        else {
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
            AudioPlayer.play("vitoria.wav");
            ImageIcon icon = new ImageIcon("resources/happy.png");
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

            JOptionPane.showMessageDialog(this,
                    "<html><center>🎉 Parabéns, <b>" + username + "</b>!<br/>A palavra era: <b>"
                            + palavraSecreta + "</b> 😄</center></html>",
                    "Vitória", JOptionPane.INFORMATION_MESSAGE, icon);
            iniciarProximaPalavraMesmoTema();
        } else if (tentativasErradas >= MAX_ERROS) {
            AudioPlayer.play("derrota.wav");
            ImageIcon icon = new ImageIcon("resources/derrota_carinha.png");
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

            JOptionPane.showMessageDialog(this,
                    "<html><center>💀 Você perdeu, <b>" + username + "</b>!<br/>A palavra era: <b>"
                            + palavraSecreta + "</b> 😔</center></html>",
                    "Derrota", JOptionPane.ERROR_MESSAGE, icon);
            iniciarProximaPalavraMesmoTema();
        }
    }

    private void iniciarProximaPalavraMesmoTema() {
        letrasCorretas.clear();
        letrasErradas.clear();
        tentativasErradas = 0;
        for (JButton btn : botoesLetra)
            btn.setEnabled(true);

        escolherPalavra();
        labelPalavra.setText(formataPalavra());
        labelErros.setText("Erros: ");
        labelDica.setText("💡 Dica: " + dicaAtual);
        painelBoneco.repaint();
    }

    private void desenharBoneco(Graphics g, int erros) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 🔲 Fundo semitransparente atrás do boneco
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(20, 20, 350, 500, 25, 25); // maior

        GradientPaint grama = new GradientPaint(10, 480, new Color(34, 139, 34), 350, 480, new Color(0, 100, 0));
        g2.setPaint(grama);
        g2.fillRoundRect(10, 475, 350, 20, 10, 10);

        GradientPaint madeiraVert = new GradientPaint(70, 50, new Color(139, 69, 19), 80, 480, new Color(101, 45, 0));
        GradientPaint madeiraHori = new GradientPaint(70, 50, new Color(160, 82, 45), 220, 70, new Color(101, 45, 0));
        g2.setPaint(madeiraVert);
        g2.fillRoundRect(70, 50, 12, 430, 10, 10); // tronco maior
        g2.setPaint(madeiraHori);
        g2.fillRoundRect(70, 50, 180, 12, 10, 10); // barra horizontal maior

        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(150, 75, 0));
        g2.drawLine(210, 50, 210, 120); // corda maior

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (erros >= 1)
            g2.drawOval(190, 120, 60, 60); // cabeça maior
        if (erros >= 2)
            g2.drawLine(220, 180, 220, 280); // tronco maior
        if (erros >= 3)
            g2.drawLine(220, 200, 170, 250); // braço esquerdo maior
        if (erros >= 4)
            g2.drawLine(220, 200, 270, 250); // braço direito maior
        if (erros >= 5)
            g2.drawLine(220, 280, 170, 350); // perna esquerda maior
        if (erros >= 6)
            g2.drawLine(220, 280, 270, 350); // perna direita maior

        // rosto maior para mostrar derrota
        if (erros >= 6) {
            g2.drawLine(200, 135, 208, 143); // olho esquerdo X
            g2.drawLine(208, 135, 200, 143);
            g2.drawLine(232, 135, 240, 143); // olho direito X
            g2.drawLine(240, 135, 232, 143);
            g2.drawArc(200, 155, 40, 20, 0, 180); // boca
        }

        g2.setColor(new Color(0, 0, 0, 50));
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(80, 480, 300, 480); // chão maior
    }
}
