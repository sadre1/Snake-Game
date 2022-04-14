package Snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int Screen_Width=800;
    static final int Screen_Height=800;
    static final int Unit_Size=40;
//    static final int Delay=100;
    static final int Game_Unit=(Screen_Height*Screen_Width)/Unit_Size;
    final int []x=new int[Game_Unit];
    final int []y=new int[Game_Unit];
    int bodyPart=6;
    int appleEaten;
    int appleX;
    int appleY;
    char direction='R';
    Boolean running=false;
    Timer timer;
    Random random;
    int HighestScore;


    GamePanel(int Delay){
        random=new Random();
        this.setPreferredSize(new Dimension(Screen_Width,Screen_Height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyboard());
        StartGame(Delay);
    }

    public void StartGame(int Delay){
        NewApple();
        running=true;
        timer=new Timer(Delay,this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){
        if(!running) {
            gameover(g);
            return;
        }

//                for(int i=0;i<Screen_Height/Unit_Size;i++)
//                {
//                    g.drawLine(i*Unit_Size,0,i*Unit_Size,Screen_Height);
//                    g.drawLine(0,i*Unit_Size,Screen_Width,i*Unit_Size);
//                }
                g.setColor(Color.GREEN);
              //  g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillOval(appleX,appleY,Unit_Size,Unit_Size);


                for(int i=0;i<bodyPart;i++)
                {
                    if(i==0)
                    {
                        g.setColor(new Color(255,255,255));
                        g.fillRect(x[i],y[i],Unit_Size,Unit_Size);
                    }
                    else
                    {
                        g.setColor(new Color(255,40,0));
                      //  g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                        g.fillRect(x[i],y[i],Unit_Size,Unit_Size);
                    }
                }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics=getFontMetrics(g.getFont());
            g.drawString("Score : "+appleEaten,(Screen_Width-metrics.stringWidth("Score : "+appleEaten))/2,g.getFont().getSize());

    }
    public void NewApple(){
        appleX=random.nextInt((int)(Screen_Width/Unit_Size))*Unit_Size;
        appleY=random.nextInt((int)(Screen_Height/Unit_Size))*Unit_Size;

    }
    public void move(){
        for(int i=bodyPart;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction)
        {
            case 'U':
                y[0]=y[0]-Unit_Size;
                break;
            case 'D':
                y[0]=y[0]+Unit_Size;
                break;
            case 'R':
                x[0]=x[0]+Unit_Size;
                break;
            case 'L':
                x[0]=x[0]-Unit_Size;
                break;
        }

    }
    public void checkApple(){

        if((x[0]==appleX)&&(y[0]==appleY)){
            bodyPart++;
            appleEaten++;
            NewApple();
        }

    }
    public void checkCollision(){
        //checks head collids with body
        for(int i=bodyPart;i>0;i--){
            if(x[0]==x[i]&&(y[0]==y[i]))
                running=false;
        }
        //head touchs boundary
        if(x[0]<0){
            running=false;
        }
        if(x[0]>Screen_Width)
        {
            running=false;
        }
        if(y[0]<0)
        {
            running=false;
        }
        if(y[0]>Screen_Height)
        {
            running=false;
        }
        if(!running)
        {
            timer.stop();
        }

    }
    public void gameover(Graphics g){
        try {
            FileInputStream fin=new FileInputStream("./Score.txt");
            byte b[]=new byte[fin.available()];
            fin.read(b);
            String str= new String(b);
            HighestScore=Integer.valueOf(str);
            fin.close();
            if(HighestScore<appleEaten)
            {
                FileOutputStream fout=new FileOutputStream("./Score.txt");
                HighestScore = appleEaten;
                String sts=String.valueOf(HighestScore);
                byte s[]=sts.getBytes();
                for (byte x: s) {
                    fout.write(x);
                }
                fout.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1=getFontMetrics(g.getFont());
        g.drawString("Score : "+appleEaten,(Screen_Width-metrics1.stringWidth("Score : "+appleEaten))/2,g.getFont().getSize());
        g.drawString("Highest Score : "+HighestScore,(Screen_Width-metrics1.stringWidth("Score : "+HighestScore))/2,100);
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2=getFontMetrics(g.getFont());
        g.drawString("Game over",(Screen_Width-metrics2.stringWidth("Game over"))/2,Screen_Height/2);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running)
        {
            move();
            checkApple();
            checkCollision();
        }
        repaint();

    }

    public class MyKeyboard extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){

            switch (e.getKeyCode())
            {
                case KeyEvent.VK_A:
                    if(direction!='R')
                    {
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction!='L')
                    {
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction!='U'){
                        direction='D';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(direction!='D')
                    {
                        direction='U';
                    }
                    break;
            }

        }
    }
}
