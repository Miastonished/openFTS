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
import java.math.*;

// Open Find The Silvers v1.0.5
//  Created by Miastonished
// https://github.com/Miastonished/openFTS

// Silver created by Silver
// https://silvershorthair.github.io/

public class FTS extends JFrame {

    // ** SETTINGS ** //
    static boolean visibleScore = true;   // if the score GUI element is visible or not                              [default: false]
    static boolean visibleHitbox = false;  // if the hitboxes for silvers are visible                                 [default: false]
    static boolean resizable = true;       // if the window can be resized or not (true/false)                        [default: true]
    static int silverAmount = 1;           // the amount of silvers (more silvers = takes longer to load the game up) [default: 1]
    
    
    
    //static int scale = 2; [DEPRECATED] // change the scale or whatever of the window (small = 1, don't set it to 0 that crashes it obviously lmfao, bigger sizes may be slower startup) [default: 2]
    // ** SETTINGS ** //

    static String splash = """   
    
            ▄█    ████████ ████████ ████████      ▄██▄ 
       ▄██████    ██          ██    ██          ▄██████▄ 
      ████  ██    ███████     ██    ████████    ████████   
      ██    ██    ██          ██          ██    ████████    
      ████████ ██ ██  v1.0.5  ██    ████████ ██ ▀██████▀
      
      ----- https://github.com/Miastonished/openFTS <---""";


    static BufferedImage bg = null;
    static BufferedImage egg = null;
    static BufferedImage bas = null;
    static BufferedImage sil = null;
    //  bg: background
    // egg: the three eggs
    // bas: basket of eggs
    // sil: silver!!!!!!
    
    static JFrame frame;
    static ImageLabel bgImg;
    static ImageLabel basketImg;
    static ImageLabel eggsImg;
    static JLabel scoreDisplay;
    
    static ImageIcon sillysilver;

    static java.util.List<Silver> silverList = new ArrayList<Silver>();
    static java.util.List<Double> silverX = new ArrayList<Double>();
    static java.util.List<Double> silverY = new ArrayList<Double>();
    
    
    static Random rand = new Random();   
    static double r;
    static double r2;
    
    
    static int score;
    
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static void main(String[] args) {
        
        System.out.println("---Open Find The Silvers v1.0.5---\n---------By Miastonished---------\n"+splash);
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
        
        sillysilver = new ImageIcon(sil);
        // ^ prepares the silver graphic
        
        frame = new JFrame("openFindTheSilvers | 0 Silvers Found");
        frame.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(resizable);
        // ^ creates the window
        
        
        scoreDisplay = new JLabel("Score: 0");
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, (int)(frame.getHeight()*0.1)));
        scoreDisplay.setBounds(0, 0, frame.getHeight(), frame.getWidth());
        scoreDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        scoreDisplay.setVerticalAlignment(SwingConstants.TOP);
        scoreDisplay.setVisible(visibleScore);
        frame.add(scoreDisplay);
        // ^ creates text that displays the score
        
        
        eggsImg = new ImageLabel("eggs");         
        eggsImg.setBounds((int)(frame.getWidth()*0.1), (int)(frame.getHeight()*0.45), (int)(frame.getWidth()*0.4), (int)(frame.getHeight()*0.4));        eggsImg.setIcon(new ImageIcon(egg));
        // ^ creates the eggs
        
        
        frame.add(eggsImg);
        // ^ adds the eggs to the frame first so that the eggs are above the silver lmao
        
        
        for (int i = 0; i < silverAmount; i++) {
            createSilver(i);
        }
        // ^ creates the silvers
        
        
        basketImg = new ImageLabel("basket of eggs");         
        basketImg.setIcon(new ImageIcon(bas));
        basketImg.setBounds((frame.getWidth() / 12) * 7, frame.getHeight() / 2, frame.getWidth() / 3, frame.getHeight() / 3);
        // ^ creates the basket of eggs
        
        
        bgImg = new ImageLabel("background wow");
        bgImg.setIcon(new ImageIcon(bg));
        // ^ creates the background
        
        scoreDisplay.setBounds(0, 0, frame.getHeight(), frame.getWidth());
        eggsImg.setBounds((int)(frame.getWidth()*0.1), (int)(frame.getHeight()*0.45), (int)(frame.getWidth()*0.4), (int)(frame.getHeight()*0.4));
        // ^ bug fix attempt?
        
        //frame.getRootPane().setDefaultButton(silverList.get(0)); // <--- funny little cheat code 
        
        
        frame.add(basketImg);
        frame.add(bgImg);
        frame.revalidate();
        frame.repaint();
        // ^ shoves all the stuff into the frame
        System.out.println("Loading Complete\n----------------");
       
        
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                
                Dimension screenSize = frame.getContentPane().getSize();
                basketImg.setBounds((frame.getWidth() / 12) * 7, frame.getHeight() / 2, frame.getWidth() / 3, frame.getHeight() / 3);
                eggsImg.setBounds((int)(frame.getWidth()*0.1), (int)(frame.getHeight()*0.45), (int)(frame.getWidth()*0.4), (int)(frame.getHeight()*0.4));
                bgImg.setSize(screenSize);
                
                scoreDisplay.setFont(new Font("Arial", Font.PLAIN, (int)(frame.getHeight()*0.1)));
                scoreDisplay.setBounds(0, 0, frame.getHeight(), frame.getWidth());
                for (int i = 0; i < silverAmount; i++) {
                    silverList.get(i).setBounds((int)(silverY.get(i) * frame.getWidth()), (int)(silverX.get(i) * frame.getHeight()), frame.getWidth() / 8, frame.getHeight() / 3);
                }
        
                frame.revalidate();
                frame.repaint();
            }
        });
        // everytime the window is resized, it shoves everything back into place (because it all fell over because you moved where they were standing you mosnter)
        
    } 
    
    
    
    
    
    
    
    public static void createSilver(int i)
    {
        //r = rand.nextInt(592 * scale); r2 = rand.nextInt(251 * scale);
        r = rand.nextDouble() * 0.85; r2 = rand.nextDouble() * 0.60;
        // ^ creates a random x and y for the silvers
        //System.out.println(r * frame.getHeight());
        
        silverList.add(i, new Silver("hey"));
        Silver silver = silverList.get(i);
        silver.setIcon(sillysilver);
        silverY.add(r);
        silverX.add(r2);
        silver.setBounds((int)(silverY.get(i) * frame.getWidth()), (int)(silverX.get(i) * frame.getHeight()), frame.getWidth() / 8, frame.getHeight() / 3);
        silver.setContentAreaFilled(false); silver.setBorderPainted(visibleHitbox);
        // ^ creates a silver
        
        silver.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    r = rand.nextDouble() * 0.85; r2 = rand.nextDouble() * 0.60;
                    // ^ creates a random x and y for the silvers
                    
                    silverY.set(i, r);
                    silverX.set(i, r2);
                    
                    silver.setBounds((int)(silverY.get(i) * frame.getWidth()), (int)(silverX.get(i) * frame.getHeight()), frame.getWidth() / 8, frame.getHeight() / 3);
                    frame.revalidate();
                    // ^ moves silver
                        
                    score++;
                    System.out.println("Score changed to: "+score);
                    //System.out.println(r * frame.getWidth());
                    //System.out.println(i);
                    scoreDisplay.setText("Score: "+score);
                    frame.setTitle("openFindTheSilvers | "+score+" Silvers Found");
                    // ^ updates the score and the score displayss 
                }
            }
        );
        // ^ 
        
        
        frame.add(silverList.get(i));
        // ^ places the silvers in the frame
        System.out.println("Created Silver "+(i+1)+"/"+silverAmount);
        // ^ debug, feel free to ccomment out if you don't want the flood lmao
    }
}


class ImageLabel extends JLabel{
    private Image _myimage;

    public ImageLabel(String text){
        super(text);
    }

    public void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
        {
            _myimage = ((ImageIcon) icon).getImage();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(_myimage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}

class Silver extends JButton{
    private Image _myimage;

    public Silver(String text){
        super(text);
    }

    public void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
        {
            _myimage = ((ImageIcon) icon).getImage();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(_myimage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}