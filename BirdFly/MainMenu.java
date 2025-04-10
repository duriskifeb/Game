import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener, KeyListener {
   int boardWidth = 360;
   int boardHeight = 640;

   // gambar
   Image backgroundImage;
   Image birdImg;
   Image topPipImg;
   Image bottomPipeImg;

   // class burung
   int birdX = boardWidth / 8;
   int birdY = boardWidth / 2;
   int birdWidth = 34;
   int birdHeight = 24;

   // class burung
   class Bird {
      int x = birdX;
      int y = birdY;
      int width = birdWidth;
      int height = birdHeight;
      Image img;

      Bird(Image img) {
         this.img = img;
      }
   }

   // class pipa
   int pipeX = boardWidth;
   int pipeY = 0;
   int pipeWidth = 64;
   int pipeHeight = 512;

   class Pipe {
      int x = pipeX;
      int y = pipeY;
      int width = pipeWidth;
      int height = pipeHeight;
      Image img;
      boolean passed = false;

      Pipe(Image img) {
         this.img = img;
      }
   }

   // game Logic run
   Bird bird;
   int velocityX = -4;
   int velocityY = 0;
   int gravity = 1;

   ArrayList<Pipe> pipes;
   
   Random random = new Random();
   Timer gameLoop;
   Timer placePipeTimer;
   boolean gameOver = false;
   double score = 0;

   MainMenu() {
      setPreferredSize(new Dimension(boardWidth, boardHeight));
      setFocusable(true);
      requestFocusInWindow();
      addKeyListener(this);

      // load foto
      backgroundImage = new ImageIcon(getClass().getResource("/image/flappybirdbg.png")).getImage();
      birdImg = new ImageIcon(getClass().getResource("/image/flappybird.png")).getImage();
      topPipImg = new ImageIcon(getClass().getResource("/image/toppipe.png")).getImage();
      bottomPipeImg = new ImageIcon(getClass().getResource("/image/bottompipe.png")).getImage();

      // burung nya :)
      bird = new Bird(birdImg);
      pipes = new ArrayList<>();

      // waktu timer
      placePipeTimer = new Timer(1500, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            placePipes();
         }
      });
      placePipeTimer.start();

      // game timer di mulai
      gameLoop = new Timer(1000 / 60, this);
      gameLoop.start();
   }

   void placePipes() {
      int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
      int openingSpace = boardHeight / 4;

      Pipe topPipe = new Pipe(topPipImg);
      topPipe.y = randomPipeY;
      pipes.add(topPipe);

      Pipe bottomPipe = new Pipe(bottomPipeImg);
      bottomPipe.y = randomPipeY + pipeHeight + openingSpace;
      pipes.add(bottomPipe);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g); // Bukan paintComponents
      draw(g);
   }

   public void draw(Graphics g) {
      // background
      g.drawImage(backgroundImage, 0, 0, null);

      // bird
      g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

      // pipes loop stage img
      for (Pipe pipe : pipes) {
         g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
      }

      // score
      g.setColor(Color.white);
      g.setFont(new Font("Arial", Font.PLAIN, 32));

      if (gameOver) {
         g.drawString("Game Over : " + (int) score, 10, 35);
      } else {
         g.drawString(String.valueOf((int) score), 10, 35);
      }
   }

   public void move() {
      // birds
      velocityY += gravity;
      bird.y += velocityY;
      bird.y = Math.max(bird.y, 0);

      // pipes
      for (int i = 0; i < pipes.size(); i++) {
         Pipe pipe = pipes.get(i);
         pipe.x += velocityX;

         if (!pipe.passed && bird.x > pipe.x + pipe.width) {
            score += 0.5;
            pipe.passed = true;
         }

         if (collision(bird, pipe)) {
            gameOver = true;
         }
      }

      if (bird.y > boardHeight) {
         gameOver = true;
      }
   }

   boolean collision(Bird a, Pipe b) {
      return a.x < b.x + b.width &&
            a.x + a.width > b.x &&
            a.y < b.y + b.height &&
            a.y + a.height > b.y;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (!gameOver) {
         move();
      }
      repaint();

      if (gameOver) {
         placePipeTimer.stop();
         gameLoop.stop();
      }
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
         velocityY = -9;

         if (gameOver) {
            bird.y = birdY;
            velocityY = 0;
            pipes.clear();
            gameOver = false;
            score = 0;
            gameLoop.start();
            placePipeTimer.start();
         }
      }
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyReleased(KeyEvent e) {
   }
}
