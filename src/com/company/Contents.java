package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Contents extends JPanel implements ActionListener {


    private static int x = 300, y = 400, z = 930, z2= 1430, score1 = 0;
    public static boolean playerFailed = false;


    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();
    }

    static KeyListener listener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent event) {
            if(event.getKeyChar()==KeyEvent.VK_SPACE && y >= 400){
                yV = -6;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_ENTER){
                z = 930;
                z2 = 1430;
                zV = 5;
                playerFailed = false;
                score1 = 0;
                event.consume();
            }


        }

        @Override
        public void keyReleased(KeyEvent event) {
        }

        @Override
        public void keyTyped(KeyEvent event) {
        }

    };

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.fillRect(z,406,25,25);
        g2d.fillRect(z2, 406,25,25);


        g2d.drawOval(x, y, 25, 25);

        g2d.drawString( "CONTROLS:", 10, 450);
        g2d.drawString( "SPACE: JUMP", 10, 475);

        g2d.drawString("SCORE: " + score1/10, 700, 25);

        if(playerFailed)
            g2d.drawString("GAME OVER :(   - Press ENTER to try again", 400, 300);

    }


    private static int zV = 5;
    private static int yV = 0;



    public void move(){
        z = z - zV;
        z2 = z2 - zV;
        y = y + yV;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (y <= 300)
            yV = 6;
        if(y > 400)
            yV = 0;


        if(z <= 0)
            z = (int)(Math.random() * 200) + 931;

        if(z2 <= 0)
            z2 = z + (int)(Math.random() * 300) + 200;

        if((x + 25 >= z && x <= z + 25 && y >= 375) || (x + 25 >= z2 && x <= z2 + 25 && y >= 375)){
            zV = 0;
            playerFailed = true;
        }
        if(!playerFailed)
            score1++;

        repaint();
    }
}