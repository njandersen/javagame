package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    
    // Screen Settings 
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    // Player Default Position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Sleep Method
    // @Override
    // public void run() {

    //     double drawInterval = 1000000000/FPS;
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    
    //    // Game Loop
    //    while (gameThread != null) {
    //        update();

    //        repaint();

    //        double remainingTime = nextDrawTime - System.nanoTime();
    //        remainingTime = remainingTime/1000000;

    //        if (remainingTime < 0) {
    //            remainingTime = 0;
    //        }

    //        try {
    //        Thread.sleep((long) (remainingTime));

    //        nextDrawTime += drawInterval;
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
           
    //    }
    // }
    @Override
    public void run() {

         double drawInterval = 1000000000/FPS;
         double delta = 0;
         long lastTime = System.nanoTime();
        long currentTime;

        // Game Loop
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
            update();

            repaint();
            delta--;
            }

           
           
        }
    }

    public void update() {
        // Update Game
        if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
        }
        else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);

        g2d.fillRect(playerX, playerY, tileSize, tileSize);

        g2d.dispose();
        
    }
}
