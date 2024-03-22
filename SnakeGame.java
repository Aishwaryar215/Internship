import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int GRID_SIZE = 20;
    private static final int CELL_SIZE = 30;

    private int snakeSize;
    private int score;

    private Point snakeHead;
    private Point snack;
    private List<Point> snakeBody;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction currentDirection;

    public SnakeGame() {
        snakeSize = 1;
        score = 0;

        snakeHead = new Point(GRID_SIZE / 2, GRID_SIZE / 2);
        snakeBody = new ArrayList<>();
        snakeBody.add(snakeHead);
        snack = generateRandomPoint();

        currentDirection = Direction.RIGHT;

        setTitle("Snake Game");
        setSize(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        });

        setFocusable(true);
        requestFocus();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveSnake();
                checkCollision();
                checkSnack();
                repaint();
            }
        }, 0, 200);
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (currentDirection != Direction.DOWN)
                    currentDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if (currentDirection != Direction.UP)
                    currentDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                if (currentDirection != Direction.RIGHT)
                    currentDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if (currentDirection != Direction.LEFT)
                    currentDirection = Direction.RIGHT;
                break;
        }
    }

    private void moveSnake() {
        Point newHead = new Point(snakeHead);
        switch (currentDirection) {
            case UP:
                newHead.y = (newHead.y - 1 + GRID_SIZE) % GRID_SIZE;
                break;
            case DOWN:
                newHead.y = (newHead.y + 1) % GRID_SIZE;
                break;
            case LEFT:
                newHead.x = (newHead.x - 1 + GRID_SIZE) % GRID_SIZE;
                break;
            case RIGHT:
                newHead.x = (newHead.x + 1) % GRID_SIZE;
                break;
        }

        snakeBody.add(0, newHead);
        if (snakeBody.size() > snakeSize) {
            snakeBody.remove(snakeBody.size() - 1);
        }

        snakeHead = newHead;
    }

    private void checkCollision() {
        if (snakeSize > 1) {
            for (int i = 1; i < snakeSize; i++) {
                if (snakeHead.equals(snakeBody.get(i))) {
                    gameOver();
                }
            }
        }
    }

    private void checkSnack() {
        if (snakeHead.equals(snack)) {
            score++;
            snakeSize++;
            snack = generateRandomPoint();
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over! Your score is: " + score);
        System.exit(0);
    }

    private Point generateRandomPoint() {
        return new Point((int) (Math.random() * GRID_SIZE), (int) (Math.random() * GRID_SIZE));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, getWidth(), getHeight());

        // Draw snack
        g.setColor(Color.blue);
        g.fillRect(snack.x * CELL_SIZE, snack.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw snake
        g.setColor(Color.black);
        for (Point bodyPart : snakeBody) {
            g.fillRect(bodyPart.x * CELL_SIZE, bodyPart.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeGame().setVisible(true);
        });
    }
}
