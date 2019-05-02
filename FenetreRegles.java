import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FenetreRegles extends JFrame{
	
	//panel principal
	private JPanel principal;
	private JTextArea textRegles;
    private Path path = Paths.get("Reglesjeu.txt");
    private Charset charset = Charset.forName("ISO-8859-1");
    private String text = "";
	
	//constructeur de la fenêtre
	public FenetreRegles() {
		
		super("Regles du Jeu : Siam");
		setSize(1300,800);
		
		// ====== Instanciation des widgets de la fenetre======
			
        try {
            List<String> lines = Files.readAllLines(path, charset);

            for (String line : lines) {
                text = text + line + "\n";
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
        textRegles = new JTextArea(text);
        textRegles.setFont(new Font("Calibri",Font.BOLD + Font.ITALIC, 12));
        textRegles.setForeground(Color.black);
		
		// ====== Organisation structurelle ======
		
		principal = new JPanel();
        principal.add(textRegles);
		
		add(principal);
        
		this.setLocationRelativeTo(null); // Centre la fenetre à l'écran
        this.setResizable(true); // Permet le redimensionnement
		this.setVisible(true);
	}
}

