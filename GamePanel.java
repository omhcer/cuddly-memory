import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    final int TILE_SIZE = 20;
    final int ROWS = 20;
    final int COLS = 20;
    final int DELAY = 200;
    Timer timer;
    Snake snake;
    Food food;
    boolean running = false;
    int score = 1; // Start with 3 points because snake is 3 segments

    public GamePanel() {
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snake = new Snake(COLS / 2, ROWS / 2);
        food = new Food(COLS, ROWS);
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Optional grid
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= ROWS; i++) g.drawLine(0, i * TILE_SIZE, COLS * TILE_SIZE, i * TILE_SIZE);
        for (int i = 0; i <= COLS; i++) g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, ROWS * TILE_SIZE);

        // Draw food
        food.draw(g, TILE_SIZE);

        // Draw snake
        snake.draw(g, TILE_SIZE);

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();

            if (snake.headEquals(food.x, food.y)) {
                snake.grow();
                food.spawn(snake);
                score++; // Increase score
            }

            if (snake.checkCollision(ROWS, COLS)) {
                running = false;
                timer.stop();

                int choice = JOptionPane.showOptionDialog(this,
                        "Game Over! Do you want to restart?",
                        "Game Over",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Restart"},
                        "Restart");

                if (choice == 0) {
                    restartGame();
                }
            }
        }
        repaint();
    }

    public void restartGame() {
        snake = new Snake(COLS / 2, ROWS / 2);
        food.spawn(snake);
        score = 3; // Reset score
        running = true;
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> snake.setDirection("LEFT");
            case KeyEvent.VK_RIGHT -> snake.setDirection("RIGHT");
            case KeyEvent.VK_UP -> snake.setDirection("UP");
            case KeyEvent.VK_DOWN -> snake.setDirection("DOWN");
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
