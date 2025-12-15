import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial ti = new TelaInicial();
            ti.setVisible(true);
        });
    }
}
