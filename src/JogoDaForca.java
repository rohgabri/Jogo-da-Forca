import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JogoDaForca {
    private static String palavraSecreta = "Obrigado";
    private static String tema = "Linguagem de Programação";
    private static int tentativasRestantes = 6;
    private static ArrayList<Character> letrasUsadas = new ArrayList<>();
    private static char[] progresso = new char[palavraSecreta.length()];

    private static JTextArea asciiArtArea;
    private static JLabel palavraLabel;
    private static JLabel tentativasLabel;
    private static JLabel letrasUsadasLabel;

    public static void main(String[] args) {
        for (int i = 0; i < progresso.length; i++) {
            progresso[i] = '_';
        }

        JFrame frame = new JFrame("Jogo da Forca");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        asciiArtArea = new JTextArea();
        asciiArtArea.setEditable(false);
        asciiArtArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        asciiArtArea.setForeground(Color.WHITE); // Texto branco
        asciiArtArea.setBackground(Color.BLACK); // Fundo preto
        asciiArtArea.setText(desenhaForca());

        palavraLabel = new JLabel("Palavra: " + String.valueOf(progresso));
        palavraLabel.setForeground(Color.WHITE); // Texto branco

        tentativasLabel = new JLabel("Tentativas restantes: " + tentativasRestantes);
        tentativasLabel.setForeground(Color.WHITE); // Texto branco

        letrasUsadasLabel = new JLabel("Letras usadas: " + letrasUsadas);
        letrasUsadasLabel.setForeground(Color.WHITE); // Texto branco

        JTextField inputField = new JTextField();
        inputField.setBackground(Color.BLACK); // Fundo preto
        inputField.setForeground(Color.WHITE); // Texto branco
        inputField.addActionListener(e -> {
            String entrada = inputField.getText().toUpperCase();
            if (entrada.length() == 1) {
                char letra = entrada.charAt(0);
                jogar(letra);
                inputField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(Color.BLACK); // Fundo preto
        panel.add(palavraLabel);
        panel.add(tentativasLabel);
        panel.add(letrasUsadasLabel);
        panel.add(inputField);

        frame.add(asciiArtArea, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.getContentPane().setBackground(Color.BLACK); // Fundo preto para o frame
        frame.setVisible(true);
    }

    private static void jogar(char tentativa) {
        if (letrasUsadas.contains(tentativa)) {
            JOptionPane.showMessageDialog(null, "Você já tentou essa letra!");
            return;
        }

        letrasUsadas.add(tentativa);

        if (palavraSecreta.indexOf(tentativa) >= 0) {
            for (int i = 0; i < palavraSecreta.length(); i++) {
                if (palavraSecreta.charAt(i) == tentativa) {
                    progresso[i] = tentativa;
                }
            }
        } else {
            tentativasRestantes--;
        }

        atualizarInterface();

        if (String.valueOf(progresso).equals(palavraSecreta)) {
            JOptionPane.showMessageDialog(null, "Parabéns! Você acertou a palavra: " + palavraSecreta);
            System.exit(0);
        }

        if (tentativasRestantes == 0) {
            JOptionPane.showMessageDialog(null, "Você perdeu! A palavra era: " + palavraSecreta);
            System.exit(0);
        }
    }

    private static void atualizarInterface() {
        asciiArtArea.setText(desenhaForca());
        palavraLabel.setText("Palavra: " + String.valueOf(progresso));
        tentativasLabel.setText("Tentativas restantes: " + tentativasRestantes);
        letrasUsadasLabel.setText("Letras usadas: " + letrasUsadas);
    }

    private static String desenhaForca() {
        String[] boneco = {
                "+---+\n    |\n    |\n    |\n   ===",
                "+---+\n O  |\n    |\n    |\n   ===",
                "+---+\n O  |\n |  |\n    |\n   ===",
                "+---+\n O  |\n/|  |\n    |\n   ===",
                "+---+\n O  |\n/|\\ |\n    |\n   ===",
                "+---+\n O  |\n/|\\ |\n/   |\n   ===",
                "+---+\n O  |\n/|\\ |\n/ \\ |\n   ==="
        };
        return boneco[6 - tentativasRestantes];
    }
}