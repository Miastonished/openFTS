import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;

// Open Find The Silvers v1.0.3
//  Created by Miastonished
// https://github.com/Miastonished/openFTS

// Silver created by silver
// https://silvershorthair.github.io/

public class FTS extends JFrame {

    // ** SETTINGS ** //
    static boolean visibleScore = true;   // if the score GUI element is visible or not                              [default: false]
    static boolean resizable = true;       // if the window can be resized or not (true/false)                        [default: true]
    static int silverAmount = 100;           // the amount of silvers (more silvers = takes longer to load the game up) [default: 1]
    static int scale = 2;                  // change the scale or whatever of the window (small = 1, don't set it to 0 that crashes it obviously lmfao, bigger sizes may be slower startup) [default: 2]
    // ** SETTINGS ** //

    static BufferedImage bg = null;
    static BufferedImage egg = null;
    static BufferedImage bas = null;
    static BufferedImage sil = null;
    //  bg: background
    // egg: the three eggs
    // bas: basket of eggs
    // sil: silver!!!!!!
    
    
    static JFrame frame;
    static JLabel bgImg;
    static JLabel basketImg;
    static JLabel eggsImg;
    static JLabel scoreDisplay;

    static ImageIcon sillysilver;

    static java.util.List<JButton> silverList = new ArrayList<JButton>();
    
    
    static Random rand = new Random();   
    static int r;
    static int r2;
    
    
    static int score;
    
    
    public static void main(String[] args) {
        
        System.out.println("---Open Find The Silvers v1.0.3---\n---------By Miastonished---------");
        // ^ splash text
        
        
        try {
            bg = ImageIO.read(new File("assets/landscape.png"));
            egg = ImageIO.read(new File("assets/ester.png"));
            bas = ImageIO.read(new File("assets/ester2.png"));
            sil = ImageIO.read(new File("assets/silver.png"));
        } catch (IOException e) {
            System.out.println("Your goof is: damn "+e);
        }
        // ^ loads all the images
        
        sillysilver = new ImageIcon(new ImageIcon(sil).getImage().getScaledInstance(100 * scale, 100 * scale, Image.SCALE_DEFAULT));
        
        frame = new JFrame("openFindTheSilvers | 0 Silvers Found");
        frame.setSize(691 * scale, 350 * scale);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(resizable);
        // ^ creates the window
        
        
        scoreDisplay = new JLabel("Score: 0");
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 25 * scale));
        scoreDisplay.setBounds(0 * scale, 0 * scale, 200 * scale, 50 * scale);
        scoreDisplay.setVisible(visibleScore);
        frame.add(scoreDisplay);
        // ^ creates text that displays the score
        
        
        eggsImg = new JLabel(new ImageIcon(new ImageIcon(egg).getImage().getScaledInstance(250 * scale, 150 * scale, Image.SCALE_DEFAULT)));         
        eggsImg.setBounds(50 * scale, 150 * scale, 250 * scale, 150 * scale);
        // ^ creates the eggs
        
        
        frame.add(eggsImg);
        // ^ adds the eggs to the frame first so that the eggs are above the silver lmao
        
        
        for (int i = 0; i < silverAmount; i++) {
            createSilver(i);
        }
        // ^ creates the silvers
        
        
        basketImg = new JLabel(new ImageIcon(new ImageIcon(bas).getImage().getScaledInstance(250 * scale, 150 * scale, Image.SCALE_DEFAULT)));         
        basketImg.setBounds(400 * scale, 150 * scale, 250 * scale, 150 * scale);
        // ^ creates the basket of eggs
        
        
        bgImg = new JLabel(new ImageIcon(new ImageIcon(bg).getImage().getScaledInstance(691 * scale, 350 * scale, Image.SCALE_DEFAULT)));
        bgImg.setBounds(0 * scale, 0 * scale, 691 * scale, 350 * scale);
        // ^ creates the background
        

        frame.add(basketImg);
        frame.add(bgImg);
        frame.revalidate();
        // ^ shoves all the stuff into the frame
    } 
    
    
    
    
    
    
    
    public static void createSilver(int i)
    {
        r = rand.nextInt(592 * scale); r2 = rand.nextInt(251 * scale); // creates a random x and y for the silvers
        
        silverList.add(i, new JButton(sillysilver));
        JButton silver = silverList.get(i);
        silver.setBounds(r,r2, 70 * scale,98 * scale);
        silver.setContentAreaFilled(false);
        silver.setBorderPainted(false);
        silver.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(MouseInfo.getPointerInfo().getLocation().x >= silver.getLocationOnScreen().x && MouseInfo.getPointerInfo().getLocation().x <= silver.getLocationOnScreen().x + silver.getWidth() && MouseInfo.getPointerInfo().getLocation().y >= silver.getLocationOnScreen().y && MouseInfo.getPointerInfo().getLocation().y <= silver.getLocationOnScreen().y + silver.getHeight())
                    {
                        r = rand.nextInt(592 * scale); r2 = rand.nextInt(251 * scale); 
                        // ^ creates a random x and y for the silvers
                        
                        silver.setBounds(r,r2, 70 * scale,98 * scale);
                        frame.revalidate();
                        // ^ moves silver and silver's silver
                        
                        score++;
                        System.out.println("Score changed to: "+score);
                        scoreDisplay.setText("Score: "+score);
                        frame.setTitle("openFindTheSilvers | "+score+" Silvers Found");
                        // ^ updates the score and the score displayss 
                    } 
                }
            }
        );
        // ^ creates a silver
        frame.add(silverList.get(i));
        // ^ places the silvers in the frame
        System.out.println("Created Silver "+(i+1)+"/"+silverAmount);
        // ^ debug, feel free to ccomment out if you don't want the flood lmao
    }
}
