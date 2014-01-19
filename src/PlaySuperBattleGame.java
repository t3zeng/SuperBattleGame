import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PlaySuperBattleGame
  extends JFrame
  implements ActionListener, KeyListener
{
  private Character hero;
  private Character villain;
  private String scoreName;
  private Options options;
  private int score;
  private Timer gameTimer = new Timer(5, this);
  private Image background;
  private Image background2;
  private JLabel backLabel;
  private int level = 1;
  private int counter = 0;
  private int decision = (int)(Math.random() * 4.0D + 1.0D);
  private int AIAction = (int)(Math.random() * 4.0D);
  private int quadVillain = this.decision;
  private Graphics bufferGraphics;
  private boolean openedOptions = false;
  
  public PlaySuperBattleGame()
  {
    super("Super Battle Game");
    try
    {
      JOptionPane.showMessageDialog(this, "You are about to play the best game ever. \nYour goal is to fight and defeat as many enemies as possible. \nPress p while in game to access controls\nYou will NOT survive");
      
      this.scoreName = JOptionPane.showInputDialog("Enter your name!");
      initializeHero(this.scoreName);
      renderBackground();
      addKeyListener(this);
      initializeVillain();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.exit(0);
    }
    new TurnGUI(this);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (TurnGUI.hasMadeMove()) {
      this.gameTimer.start();
    }
    if ((e.getSource() == this.gameTimer) && (TurnGUI.hasMadeMove()))
    {
      this.counter += 1;
      if (this.counter == 251)
      {
        this.counter = 0;
        this.gameTimer.stop();
        TurnGUI.setMadeMove(false);
        TurnGUI.reenableButtons();
        if (this.hero.getEnergy() < 5) {
          TurnGUI.singleDisable(3);
        } else {
          TurnGUI.singleEnable(3);
        }
        if (this.hero.getEnergy() == 0) {
          TurnGUI.singleDisable(0);
        } else {
          TurnGUI.singleEnable(0);
        }
        this.AIAction = ((int)(Math.random() * 4.0D));
        if (this.villain.getEnergy() < 5) {
          this.AIAction = ((int)(Math.random() * 3.0D));
        }
        if (this.villain.getEnergy() == 0) {
          this.AIAction = ((int)(Math.random() * 2.0D + 1.0D));
        }
        if ((this.AIAction == 0) || (this.AIAction == 2))
        {
          this.decision = ((int)(Math.random() * 4.0D + 1.0D));
          this.quadVillain = this.decision;
        }
      }
      villainAI();
      actions();
    }
    repaint();
  }
  
  public void startTimer()
  {
    this.gameTimer.start();
  }
  
  public void villainAI()
  {
    System.out.println(this.villain.getQuadrant() + " " + this.quadVillain);
    if ((this.AIAction == 0) || (this.AIAction == 2)) {
      if (this.villain.getQuadrant() == 1)
      {
        if (this.decision == 2)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() + 1, (int)this.villain.getLocation().getY());
          if (this.counter == 250) {
            this.villain.setQuadrant(2);
          }
        }
        else if (this.decision == 3)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(3);
          }
        }
        else if (this.decision == 4)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() + 1, (int)this.villain.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(4);
          }
        }
      }
      else if (this.villain.getQuadrant() == 2)
      {
        if (this.decision == 1)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() - 1, (int)this.villain.getLocation().getY());
          if (this.counter == 250) {
            this.villain.setQuadrant(1);
          }
        }
        else if (this.decision == 3)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() - 1, (int)this.villain.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(3);
          }
        }
        else if (this.decision == 4)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(4);
          }
        }
      }
      else if (this.villain.getQuadrant() == 3)
      {
        if (this.decision == 1)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(1);
          }
        }
        else if (this.decision == 2)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() + 1, (int)this.villain.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(2);
          }
        }
        else if (this.decision == 4)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() + 1, (int)this.villain.getLocation().getY());
          if (this.counter == 250) {
            this.villain.setQuadrant(4);
          }
        }
      }
      else if (this.villain.getQuadrant() == 4) {
        if (this.decision == 1)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() - 1, (int)this.villain.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(1);
          }
        }
        else if (this.decision == 2)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.villain.setQuadrant(2);
          }
        }
        else if (this.decision == 3)
        {
          this.villain.setLocation((int)this.villain.getLocation().getX() - 1, (int)this.villain.getLocation().getY());
          if (this.counter == 250) {
            this.villain.setQuadrant(3);
          }
        }
      }
    }
    if (this.counter == 250)
    {
      if (this.AIAction == 1) {
        this.villain.setEnergy(this.villain.getEnergy() + 1);
      }
      if (this.AIAction == 0)
      {
        this.villain.setEnergy(this.villain.getEnergy() - 1);
        if ((TurnGUI.getAttack()) || (TurnGUI.getMove()))
        {
          if (this.villain.getQuadrant() == TurnGUI.getQuadrant()) {
            this.hero.setHealth(this.hero.getHealth() - this.level * 5);
          }
        }
        else if (this.villain.getQuadrant() == this.hero.getQuadrant()) {
          this.hero.setHealth(this.hero.getHealth() - this.level * 5);
        }
      }
      if (this.AIAction == 3)
      {
        this.villain.setEnergy(this.villain.getEnergy() - 5);
        if ((TurnGUI.getAttack()) || (TurnGUI.getMove()))
        {
          if (this.quadVillain == TurnGUI.getQuadrant()) {
            this.hero.setHealth(this.hero.getHealth() - this.level * 15);
          }
        }
        else if (this.quadVillain == this.hero.getQuadrant()) {
          this.hero.setHealth(this.hero.getHealth() - this.level * 15);
        }
      }
      if (this.villain.getEnergy() >= 10) {
        this.villain.setEnergy(10);
      }
      if (this.hero.getHealth() <= 0)
      {
        dispose();
        TurnGUI.disableButtons();
        try
        {
          new HighScores(this.level, this.scoreName);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void actions()
  {
    if ((TurnGUI.getAttack()) || (TurnGUI.getMove())) {
      if (this.hero.getQuadrant() == 1)
      {
        if (TurnGUI.getQuadrant() == 2)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() + 1, (int)this.hero.getLocation().getY());
          if (this.counter == 250) {
            this.hero.setQuadrant(2);
          }
        }
        else if (TurnGUI.getQuadrant() == 3)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(3);
          }
        }
        else if (TurnGUI.getQuadrant() == 4)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() + 1, (int)this.hero.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(4);
          }
        }
      }
      else if (this.hero.getQuadrant() == 2)
      {
        if (TurnGUI.getQuadrant() == 1)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() - 1, (int)this.hero.getLocation().getY());
          if (this.counter == 250) {
            this.hero.setQuadrant(1);
          }
        }
        else if (TurnGUI.getQuadrant() == 3)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() - 1, (int)this.hero.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(3);
          }
        }
        else if (TurnGUI.getQuadrant() == 4)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(4);
          }
        }
      }
      else if (this.hero.getQuadrant() == 3)
      {
        if (TurnGUI.getQuadrant() == 1)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(1);
          }
        }
        else if (TurnGUI.getQuadrant() == 2)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() + 1, (int)this.hero.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(2);
          }
        }
        else if (TurnGUI.getQuadrant() == 4)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() + 1, (int)this.hero.getLocation().getY());
          if (this.counter == 250) {
            this.hero.setQuadrant(4);
          }
        }
      }
      else if (this.hero.getQuadrant() == 4) {
        if (TurnGUI.getQuadrant() == 1)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() - 1, (int)this.hero.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(1);
          }
        }
        else if (TurnGUI.getQuadrant() == 2)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() - 1);
          if (this.counter == 250) {
            this.hero.setQuadrant(2);
          }
        }
        else if (TurnGUI.getQuadrant() == 3)
        {
          this.hero.setLocation((int)this.hero.getLocation().getX() - 1, (int)this.hero.getLocation().getY());
          if (this.counter == 250) {
            this.hero.setQuadrant(3);
          }
        }
      }
    }
    if (this.counter == 250)
    {
      if (TurnGUI.getCharge()) {
        this.hero.setEnergy(this.hero.getEnergy() + 1);
      }
      if (TurnGUI.getAttack())
      {
        this.hero.setEnergy(this.hero.getEnergy() - 1);
        if (TurnGUI.getQuadrant() == this.villain.getQuadrant()) {
          this.villain.setHealth(this.villain.getHealth() - 15);
        }
      }
      if (TurnGUI.getKamehameha())
      {
        this.hero.setEnergy(this.hero.getEnergy() - 5);
        if (TurnGUI.getQuadrant() == this.villain.getQuadrant()) {
          this.villain.setHealth(this.villain.getHealth() - (50 + (50 * this.hero.getEnergy() - this.level)));
        }
      }
      if (this.hero.getEnergy() >= 10) {
        this.hero.setEnergy(10);
      }
      if (this.villain.getHealth() <= 0)
      {
        this.level += 1;
        if (this.hero.getHealth() <= 90) {
          this.hero.setHealth(this.hero.getHealth() + 10);
        } else {
          this.hero.setHealth(100);
        }
        if (this.hero.getEnergy() <= 9) {
          this.hero.setEnergy(this.hero.getEnergy() + 1);
        }
        try
        {
          initializeVillain();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void renderBackground()
    throws IOException
  {
    setSize(500, 500);
    setVisible(true);
    setResizable(false);
    this.background = createImage(500, 500);
    this.background2 = ImageIO.read(new File("background.jpg"));
    this.background2 = this.background2.getScaledInstance(500, 500, 4);
    this.bufferGraphics = this.background.getGraphics();
    this.backLabel = new JLabel(new ImageIcon(this.background));
    this.backLabel.setBounds(0, 0, 500, 500);
    setLayout(null);
    add(this.backLabel);
    setDefaultCloseOperation(0);
  }
  
  public void paint(Graphics g)
  {
    if (this.bufferGraphics == null) {
      return;
    }
    this.bufferGraphics.drawImage(this.background2, 0, 0, this);
    this.bufferGraphics.setColor(Color.BLACK);
    try
    {
      this.bufferGraphics.drawString(this.hero.getName(), 20, 45);
      this.bufferGraphics.drawString(this.villain.getName(), 275, 45);
    }
    catch (Exception e)
    {
      System.exit(0);
    }
    this.bufferGraphics.setFont(new Font("Book Antiqua", 1, 20));
    this.bufferGraphics.drawString(this.level+"", 245, 75);
    this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY(), 30, 30);
    this.bufferGraphics.fillRect((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY(), 30, 30);
    if (this.hero.getHealth() >= 0) {
      this.bufferGraphics.setColor(new Color(200 - this.hero.getHealth() * 2, 255 - this.hero.getHealth(), 0));
    }
    this.bufferGraphics.fillRect(20, 50, this.hero.getHealth() * 2, 20);
    

    this.bufferGraphics.setColor(Color.BLUE);
    this.bufferGraphics.fillRect(20, 80, this.hero.getEnergy() * 20, 20);
    this.bufferGraphics.fillRect(275, 80, this.villain.getEnergy() * 20, 20);
    if (this.villain.getHealth() >= 0) {
      this.bufferGraphics.setColor(new Color(200 - this.villain.getHealth() * 2, 255 - this.villain.getHealth(), 0));
    }
    this.bufferGraphics.fillRect(275, 50, this.villain.getHealth() * 2, 20);
    

    this.bufferGraphics.setColor(Color.BLACK);
    this.bufferGraphics.drawString(this.hero.getHealth()+"", 100, 67);
    this.bufferGraphics.drawString(this.villain.getHealth()+"", 360, 67);
    this.bufferGraphics.setColor(Color.WHITE);
    this.bufferGraphics.drawString(this.hero.getEnergy()+"", 100, 97);
    this.bufferGraphics.drawString(this.villain.getEnergy()+"", 360, 97);
    if (this.gameTimer.isRunning())
    {
      heroGraphics();
      villainGraphics();
    }
    g.drawImage(this.background, 0, 0, this);
  }
  
  public void heroGraphics()
  {
    this.bufferGraphics.setColor(Color.cyan);
    if (TurnGUI.getKamehameha())
    {
      if (TurnGUI.getQuadrant() != this.hero.getQuadrant()) {
        for (int i = 0; i < 20; i++) {
          if (TurnGUI.getQuadrant() == 1)
          {
            if (this.hero.getQuadrant() == 4) {
              this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() - i, (int)this.hero.getLocation().getY() + i, 125 - i, 125 + i);
            } else {
              this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() + i, (int)this.hero.getLocation().getY() + i, 125 + i, 125 + i);
            }
          }
          else if (TurnGUI.getQuadrant() == 2) {
            this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() + i, (int)this.hero.getLocation().getY() + i, 375 + i, 125 + i);
          } else if (TurnGUI.getQuadrant() == 3) {
            this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() + i, (int)this.hero.getLocation().getY() + i, 125 + i, 375 + i);
          } else if (TurnGUI.getQuadrant() == 4) {
            if (this.hero.getQuadrant() == 1) {
              this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() - i, (int)this.hero.getLocation().getY() + i, 375 - i, 375 + i);
            } else {
              this.bufferGraphics.drawLine((int)this.hero.getLocation().getX() + i, (int)this.hero.getLocation().getY() + i, 375 + i, 375 + i);
            }
          }
        }
      } else {
        for (int i = 0; i < 50; i++)
        {
          this.bufferGraphics.drawOval((int)this.hero.getLocation().getX() - i - 15, (int)this.hero.getLocation().getY() - i - 15, 75 - i, 75 - i);
          this.bufferGraphics.drawOval((int)this.hero.getLocation().getX() + i - 15, (int)this.hero.getLocation().getY() + i - 15, 55 + i, 55 + i);
        }
      }
      this.bufferGraphics.setColor(Color.yellow);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 20, (int)this.hero.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() - 20, (int)this.hero.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() - 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 10, (int)this.hero.getLocation().getY() + 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() - 10, (int)this.hero.getLocation().getY() + 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 10, (int)this.hero.getLocation().getY() - 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() - 10, (int)this.hero.getLocation().getY() - 10, 30, 30);
    }
    else if (TurnGUI.getAttack())
    {
      this.bufferGraphics.setColor(Color.yellow);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY(), 30, 5);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 25, (int)this.hero.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 25, 30, 5);
    }
    else if (TurnGUI.getCharge())
    {
      this.bufferGraphics.setColor(Color.yellow);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY(), 30, 5);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 25, (int)this.hero.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 25, 30, 5);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() + 20, (int)this.hero.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX() - 20, (int)this.hero.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() + 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.hero.getLocation().getX(), (int)this.hero.getLocation().getY() - 20, 30, 30);
    }
  }
  
  public void villainGraphics()
  {
    this.bufferGraphics.setColor(Color.green);
    if ((this.villain.getQuadrant() == this.hero.getQuadrant()) && (TurnGUI.getKamehameha())) {
      this.bufferGraphics.setColor(Color.PINK);
    }
    if (this.AIAction == 3)
    {
      if (this.decision != this.villain.getQuadrant()) {
        for (int i = 0; i < 20; i++) {
          if (this.decision == 1)
          {
            if (this.villain.getQuadrant() == 4) {
              this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() - i, (int)this.villain.getLocation().getY() + i, 125 - i, 125 + i);
            } else {
              this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() + i, (int)this.villain.getLocation().getY() + i, 125 + i, 125 + i);
            }
          }
          else if (this.decision == 2) {
            this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() + i, (int)this.villain.getLocation().getY() + i, 375 + i, 125 + i);
          } else if (this.decision == 3) {
            this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() + i, (int)this.villain.getLocation().getY() + i, 125 + i, 375 + i);
          } else if (this.decision == 4) {
            if (this.villain.getQuadrant() == 1) {
              this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() - i, (int)this.villain.getLocation().getY() + i, 375 - i, 375 + i);
            } else {
              this.bufferGraphics.drawLine((int)this.villain.getLocation().getX() + i, (int)this.villain.getLocation().getY() + i, 375 + i, 375 + i);
            }
          }
        }
      } else {
        for (int i = 0; i < 50; i++)
        {
          this.bufferGraphics.drawOval((int)this.villain.getLocation().getX() - i - 15, (int)this.villain.getLocation().getY() - i - 15, 75 - i, 75 - i);
          this.bufferGraphics.drawOval((int)this.villain.getLocation().getX() + i - 15, (int)this.villain.getLocation().getY() + i - 15, 55 + i, 55 + i);
        }
      }
      this.bufferGraphics.setColor(Color.red);
      if ((this.villain.getQuadrant() == this.hero.getQuadrant()) && (TurnGUI.getKamehameha())) {
        this.bufferGraphics.setColor(Color.MAGENTA);
      }
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 20, (int)this.villain.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() - 20, (int)this.villain.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() - 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 10, (int)this.villain.getLocation().getY() + 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() - 10, (int)this.villain.getLocation().getY() + 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 10, (int)this.villain.getLocation().getY() - 10, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() - 10, (int)this.villain.getLocation().getY() - 10, 30, 30);
    }
    else if (this.AIAction == 0)
    {
      this.bufferGraphics.setColor(Color.red);
      if ((this.villain.getQuadrant() == this.hero.getQuadrant()) && (TurnGUI.getAttack())) {
        this.bufferGraphics.setColor(Color.MAGENTA);
      }
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY(), 30, 5);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 25, (int)this.villain.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 25, 30, 5);
    }
    else if (this.AIAction == 1)
    {
      this.bufferGraphics.setColor(Color.red);
      if ((this.villain.getQuadrant() == this.hero.getQuadrant()) && (TurnGUI.getCharge())) {
        this.bufferGraphics.setColor(Color.MAGENTA);
      }
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY(), 30, 5);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 25, (int)this.villain.getLocation().getY(), 5, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 25, 30, 5);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() + 20, (int)this.villain.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX() - 20, (int)this.villain.getLocation().getY(), 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() + 20, 30, 30);
      this.bufferGraphics.fillOval((int)this.villain.getLocation().getX(), (int)this.villain.getLocation().getY() - 20, 30, 30);
    }
  }
  
  public void update(Graphics g)
  {
    paint(g);
  }
  
  public void initializeHero(String n)
  {
    this.hero = new Character(n);
  }
  
  public int getHeroEnergy()
  {
    return this.hero.getEnergy();
  }
  
  public int getHeroQuadrant()
  {
    return this.hero.getQuadrant();
  }
  
  public void initializeVillain()
    throws IOException
  {
    IO.openInputFile("SuperVillainOrder.txt");
    for (int i = 0; i < this.level - 1; i++) {
      IO.readLine();
    }
    this.villain = new Character(IO.readLine());
    System.out.println("originial quad " + this.villain.getQuadrant());
    IO.closeInputFile();
  }
  
  public void setHighScore()
    throws IOException
  {
    new HighScores(this.score, this.scoreName);
  }
  
  public String getScoreName()
  {
    return this.scoreName;
  }
  
  public void keyPressed(KeyEvent e)
  {
    if ((e.getKeyChar() == 'p') || (e.getKeyChar() == 'P'))
    {
      TurnGUI.disableButtons();
      this.gameTimer.stop();
      if (!this.openedOptions) {
        this.options = new Options(this);
      } else {
        this.options.openWindow();
      }
      this.openedOptions = true;
    }
    if (e.getKeyChar() == '~')
    {
      this.villain.setHealth(0);
      this.hero.setHealth(100);
      this.hero.setEnergy(10);
    }
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}
