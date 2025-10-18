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
