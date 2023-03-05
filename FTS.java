import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random;

// Open Find The Silvers v1.0
//  Created by Miastonished
// https://github.com/Miastonished/openFTS

// Silver created by silver
// https://silvershorthair.github.io/

public class FTS extends JFrame implements ActionListener{

    static BufferedImage bg = null;
    static BufferedImage egg = null;
    static BufferedImage bas = null;
    static BufferedImage sil = null;
    //  bg: background
    // egg: the three eggs
    // bas: basket of eggs
    // sil: silver!!!!!!
    
    static int scale = 2;
    // change the scale or whatever of the thingymajig (small = 1, don't set it to 0 that crashes it obviously lmfao, bigger sizes may be slower startup)
    
    static JFrame frame;
    static JButton hitbox;
    static JLabel silverImg;
    static JLabel bgImg;
    static JLabel basketImg;
    static JLabel eggsImg;
    static JLabel scoreDisplay;
    
    
    static Random rand = new Random();   
    static int r = rand.nextInt(592 * 2);
    static int r2 = rand.nextInt(251 * 2);
    
    static int score;
    
    public static void main(String[] args) {
        
        try {
            bg = ImageIO.read(new File("assets/landscape.png"));
            egg = ImageIO.read(new File("assets/ester.png"));
            bas = ImageIO.read(new File("assets/ester2.png"));
            sil = ImageIO.read(new File("assets/silver.png"));
        } catch (IOException e) {
            System.out.println("Your goof is: damn "+e);
        }
        // ^ loads all the images
        
        
        frame = new JFrame("openFindTheSilvers");
        frame.setSize(691 * scale, 350 * scale);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //creates the window
        
        scoreDisplay = new JLabel("Score: 0");
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 25 * scale));
        scoreDisplay.setBounds(0 * scale, 0 * scale, 200 * scale, 50 * scale);
        //creates text that displays the score
        
        hitbox = new JButton("");         
        hitbox.setBounds(r+(15 * scale),r2, 70 * scale,98 * scale);
        hitbox.setContentAreaFilled(false);
        hitbox.setBorderPainted(false);
        hitbox.addActionListener(new FTS());
        //creates the hitbox for silver
        
        
        eggsImg = new JLabel(new ImageIcon(new ImageIcon(egg).getImage().getScaledInstance(250 * scale, 150 * scale, Image.SCALE_DEFAULT)));         
        eggsImg.setBounds(50 * scale, 150 * scale, 250 * scale, 150 * scale);
        //creates the eggs
        
        
        silverImg = new JLabel(new ImageIcon(new ImageIcon(sil).getImage().getScaledInstance(100 * scale, 100 * scale, Image.SCALE_DEFAULT)));         
        silverImg.setBounds(r,r2,100 * scale,100 * scale);
        //creates silver
        
        
        basketImg = new JLabel(new ImageIcon(new ImageIcon(bas).getImage().getScaledInstance(250 * scale, 150 * scale, Image.SCALE_DEFAULT)));         
        basketImg.setBounds(400 * scale, 150 * scale, 250 * scale, 150 * scale);
        //creates the basket of eggs
        
        
        bgImg = new JLabel(new ImageIcon(new ImageIcon(bg).getImage().getScaledInstance(691 * scale, 350 * scale, Image.SCALE_DEFAULT)));
        bgImg.setBounds(0 * scale, 0 * scale, 691 * scale, 350 * scale);
        //creates the background
        
        // frame.add(scoreDisplay);
        // >UNCOMMENT THIS IF YOU WANT THE SCORE VISIBLE<
        frame.add(hitbox);
        frame.add(eggsImg);
        frame.add(silverImg);
        frame.add(basketImg);
        frame.add(bgImg);
        frame.revalidate();
        //shoves all the stuff into the frame
    }

    public void actionPerformed(ActionEvent e) {
        if(MouseInfo.getPointerInfo().getLocation().x >= hitbox.getLocationOnScreen().x && MouseInfo.getPointerInfo().getLocation().x <= hitbox.getLocationOnScreen().x + hitbox.getWidth() && MouseInfo.getPointerInfo().getLocation().y >= hitbox.getLocationOnScreen().y && MouseInfo.getPointerInfo().getLocation().y <= hitbox.getLocationOnScreen().y + hitbox.getHeight())
        {
            r = rand.nextInt(592 * scale);
            r2 = rand.nextInt(251 * scale);
            // ^ makes a new random value
            
            hitbox.setBounds(r+(15 * scale),r2, 70 * scale,98 * scale);
            silverImg.setBounds(r,r2,100 * scale,100 * scale);
            frame.revalidate();
            // ^ moves silver and silver's hitbox
            
            score++;
            System.out.println(score);
            scoreDisplay.setText("Score: "+score);
            frame.setTitle("openFindTheSilvers | "+score+" Silvers Found");
            //updates the score and the score displayss
        }
    }
    
}