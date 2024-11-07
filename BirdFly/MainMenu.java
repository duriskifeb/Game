import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;



public class MainMenu extends JPanel implements ActionListener, KeyListener {
   int boardWidth = 360;
   int boardHeight = 640;
   
   //gambar
   Image backgroundImage;
   Image birdImg;
   Image topPipImg;
   Image bottomPipeImg;

   //class burung
   int birdX = boardWidth/8;
   int birdY = boardWidth/2;
   int birdWidth = 34;
   int birdHeight = 24;

   //class burung 
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

   //class pipa
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

   //game Logic run
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

   FlappyBird() {
      setPreferredSize(new Dimension(boardWidth,boardHeight));
      setFocusable(true);
      addKeyListener(this);  

      //load foto
   }




   




   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
   } 
}
