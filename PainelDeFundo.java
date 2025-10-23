import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelDeFundo extends JPanel {

    private Image imagemFundo;

    public PainelDeFundo(String urlImagem) {
        try {
            URL url = new URL(urlImagem);
            ImageIcon icon = new ImageIcon(url);
            this.imagemFundo = icon.getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo: " + e.getMessage());
        }
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
