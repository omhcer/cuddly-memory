import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.startGame();
    }
}
