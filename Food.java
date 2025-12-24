import java.awt.*;
import java.util.Random;

public class Food {
    int x, y;
    Random rand = new Random();
    int cols, rows;

    public Food(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        spawn(null);
    }

    public void spawn(Snake snake) {
        boolean onSnake;
        do {
            onSnake = false;
            x = rand.nextInt(cols);
            y = rand.nextInt(rows);

            if (snake != null) {
                for (Point p : snake.body) {
                    if (p.x == x && p.y == y) onSnake = true;
                }
            }
        } while (onSnake);
    }

    public void draw(Graphics g, int tileSize) {
        g.setColor(Color.RED);
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}
