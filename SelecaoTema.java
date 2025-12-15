import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TemaSelectionFrame extends JFrame {
   private String username;
   private JComboBox<String> temaCombo;
   private JButton iniciarBtn;

   public TemaSelectionFrame(String var1) {
      this.username = var1;
      this.setTitle("Selecione Tema Sustentável");
      this.setSize(400, 200);
      this.setLocationRelativeTo((Component)null);
      this.setDefaultCloseOperation(3);
      this.initComponents();
   }

   private void initComponents() {
      JLabel var1 = new JLabel("Escolha o tema:");
      String[] var2 = new String[]{"Meio Ambiente", "Energia Renovável", "Reciclagem", "Biodiversidade", "Mudança Climática"};
      this.temaCombo = new JComboBox(var2);
      this.iniciarBtn = new JButton("Iniciar Forca");
      JPanel var3 = new JPanel(new GridBagLayout());
      var3.setOpaque(false);
      GridBagConstraints var4 = new GridBagConstraints();
      var4.insets = new Insets(10, 10, 10, 10);
      var4.gridx = 0;
      var4.gridy = 0;
      var3.add(var1, var4);
      var4.gridx = 1;
      var3.add(this.temaCombo, var4);
      var4.gridx = 0;
      var4.gridy = 1;
      var4.gridwidth = 2;
      var3.add(this.iniciarBtn, var4);
      this.getContentPane().add(var3, "Center");
      this.iniciarBtn.addActionListener((var1x) -> {
         this.iniciarForca();
      });
   }

   private void iniciarForca() {
      String var1 = (String)this.temaCombo.getSelectedItem();
      ForcaFrame var2 = new ForcaFrame(this.username, var1);
      var2.setVisible(true);
      this.dispose();
   }
}


