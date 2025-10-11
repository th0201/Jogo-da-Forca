import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JogoForca {
    private String palavra;
    private String dica;
    private char[] palavraOculta;
    private Set<Character> letrasTentadas;
    private RedePetri rede;

    private static final String[][] palavrasEDicas = {
        {"NATUREZA", "Tudo o que existe sem intervenção humana"},
        {"RECICLAGEM", "Reutilização de materiais para evitar desperdício"},
        {"PRESERVAÇÃO", "Ato de proteger o meio ambiente"},
        {"ÁRVORE", "Planta essencial para o oxigênio"},
        {"SUSTENTABILIDADE", "Equilíbrio entre desenvolvimento e natureza"},
        {"POLUIÇÃO", "Problema causado pelo descarte inadequado"},
    };

    public JogoForca() {
        int idx = new Random().nextInt(palavrasEDicas.length);
        this.palavra = palavrasEDicas[idx][0].toUpperCase();
        this.dica = palavrasEDicas[idx][1];
        this.palavraOculta = new char[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) palavraOculta[i] = '_';
        this.letrasTentadas = new HashSet<>();
        this.rede = new RedePetri(palavra.length());
    }

}