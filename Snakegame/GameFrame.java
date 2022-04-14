package Snakegame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame implements ActionListener{
    JRadioButton c1,c2,c3;
    JLabel tf;
    JButton button;
    JPanel p;
    JFrame j=new JFrame();
    JFrame m=new JFrame();
    ImageIcon icon=new ImageIcon("C:\\Users\\OLYMPUS\\Downloads\\snake.png");
    File in=new File("./valorant_main_menu.wav");
    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    int hig=(int)screen.getHeight();
    int wid=(int)screen.getWidth();

    public void music(){
        try {
            AudioInputStream audio=AudioSystem.getAudioInputStream(in);
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
//        AudioPlayer ap=AudioPlayer.player;
//        AudioStream as;
//        AudioData ad;
//        ContinuousAudioDataStream loop=null;
//        try {
//            InputStream str=new FileInputStream("./valorant_main_menu.wav");
//            as=new AudioStream(str);
//            AudioPlayer.player.start(as);
////           ad=as.getData();
////          loop=new ContinuousAudioDataStream(ad);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//      // ap.start(loop);
    }

    GameFrame(){
        music();
        j.setIconImage(icon.getImage());
        m.setIconImage(icon.getImage());
        c1=new JRadioButton("Easy");
        c1.setForeground(Color.white);
        c2=new JRadioButton("Medium");
        c3=new JRadioButton("Hard");
        c2.setForeground(Color.white);
        c3.setForeground(Color.white);
        c1.setFocusable(false);
        c2.setFocusable(false);
        c3.setFocusable(false);
        c1.setBackground(Color.black);
        c2.setBackground(Color.black);
        c3.setBackground(Color.black);
        c1.setBounds(200,200,200,60);
        c2.setBounds(200,250,200,60);
        c3.setBounds(200,300,200,60);
        c3.setFont(new Font("Ink Free",Font.BOLD,40));
        c1.setFont(new Font("Ink Free",Font.BOLD,40));
        c2.setFont(new Font("Ink Free",Font.BOLD,40));
        tf=new JLabel("Choose Difficulty");
        tf.setForeground(Color.white);
        tf.setBounds(200,100,450,60);
        tf.setFont(new Font("Ink Free",Font.BOLD,50));
       // button=new JButton("Lets GOOOO");
        //button.setBounds(200,400,400,60);
        //button.setFont(new Font("Ink Free",Font.BOLD,50));
        p=new JPanel();
        p.setBackground(Color.black);
        p.setFocusable(true);
        p.setLayout(null);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,20,true),"SNAKE"));
        p.setPreferredSize(new Dimension(800,800));
        //button.addActionListener(this);
        c1.addActionListener(this);
        c2.addActionListener(this);
        c3.addActionListener(this);
        p.add(c1);
        p.add(c2);
        p.add(c3);
        p.add(tf);
        //p.add(button);
        j.add(p);
        j.setTitle("Snake Game");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.pack();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setSize(800,800);

//            this.add(new GamePanel(100));
//            this.setTitle("Snake Game");
//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            this.setResizable(false);
//            this.pack();
//            this.setVisible(true);
//            this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (c1.isSelected()) {
            j.dispose();
            m.setTitle("Snake Game");
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.setLocation(hig/2,wid/10);
            m.setResizable(false);
            m.pack();
            m.setVisible(true);
            m.setSize(800, 800);
            m.add(new GamePanel(150));
        }
        if (c2.isSelected()){
            j.dispose();
            m.add(new GamePanel(100));
            m.setTitle("Snake Game");
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.setResizable(false);
            m.pack();
            m.setVisible(true);
            m.setLocation(hig/2,wid/10);
            m.setSize(800, 800);

        }
        if(c3.isSelected())
        {
            j.dispose();
            m.setTitle("Snake Game");
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.setResizable(false);
            m.pack();
            m.setVisible(true);
            m.setLocation(hig/2,wid/50);
            m.setSize(800, 800);
            m.add(new GamePanel(25));
        }



    }


}
