import java.awt.*;
import java.util.ArrayList;

public class Snake {
    ArrayList<Point> body = new ArrayList<>();
    String direction = "UP";
    boolean growNext = false;

    public Snake(int startX, int startY) {
        // Start with 3 segments so it looks like a proper snake
        body.add(new Point(startX, startY));
        body.add(new Point(startX, startY + 1));
        body.add(new Point(startX, startY + 2));
    }

    public void setDirection(String dir) {
        // Prevent reversing
        if ((direction.equals("UP") && dir.equals("DOWN")) ||
            (direction.equals("DOWN") && dir.equals("UP")) ||
            (direction.equals("LEFT") && dir.equals("RIGHT")) ||
            (direction.equals("RIGHT") && dir.equals("LEFT"))) return;
        direction = dir;
    }

    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case "UP" -> newHead.y--;
            case "DOWN" -> newHead.y++;
            case "LEFT" -> newHead.x--;
            case "RIGHT" -> newHead.x++;
        }

        body.add(0, newHead);

        if (!growNext) body.remove(body.size() - 1);
        else growNext = false;
    }

    public void grow() {
        growNext = true;
    }

    public boolean headEquals(int x, int y) {
        Point head = body.get(0);
        return head.x == x && head.y == y;
    }

    public boolean checkCollision(int rows, int cols) {
        Point head = body.get(0);
        if (head.x < 0 || head.x >= cols || head.y < 0 || head.y >= rows) return true;
        for (int i = 1; i < body.size(); i++) if (head.equals(body.get(i))) return true;
        return false;
    }

    public void draw(Graphics g, int tileSize) {
        g.setColor(Color.GREEN);
        for (Point p : body) {
            g.fillRect(p.x * tileSize, p.y * tileSize, tileSize, tileSize);
        }
    }
}
