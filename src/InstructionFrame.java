import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class InstructionFrame
  extends JFrame
  implements ActionListener
{
  private JButton stopReading;
  private JTextArea wallOfText;
  
  public InstructionFrame()
  {
    super("READ THIS IF YOU DON'T KNOW HOW TO PLAY");
    setSize(500, 800);
    setLayout(null);
    setResizable(false);
    getContentPane().setBackground(Color.gray);
    
    this.wallOfText = new JTextArea("In this game, there are 4 quadrants. You spawn randomly in one of the 4\nsquares along with your enemy. You are a circle with a yellow aura and cyan\npowers while your opponent has a red aura and green powers. It is permitted\nto share a square with the enemy though attacks to that location will behave\nthe same. Each turn you have the option to perform one of four actions:\n\nAttack:\n\n\n\n\nCharge:\n\n\n\n\nMove:\n\n\n\n\nUltimate:\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYour goal is to defeat as many enemies as possible.Good Luck...\n(Don't worry the favor of battle is skewed just a little bit towards you to aid you.)");
    





    this.wallOfText.setBounds(25, 50, 450, 650);
    this.wallOfText.setOpaque(false);
    this.wallOfText.setEditable(false);
    add(this.wallOfText);
    
    this.stopReading = new JButton("Back");
    this.stopReading.setBounds(200, 700, 100, 75);
    this.stopReading.addActionListener(this);
    add(this.stopReading);
    
    setVisible(true);
    setDefaultCloseOperation(0);
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    
    g.fillOval(100, 200, 30, 30);
    g.fillRect(300, 200, 30, 30);
    g.fillOval(100, 285, 30, 30);
    g.fillRect(300, 285, 30, 30);
    g.fillOval(100, 370, 30, 30);
    g.fillRect(300, 370, 30, 30);
    g.fillOval(100, 500, 30, 30);
    g.fillRect(300, 500, 30, 30);
    

    g.setColor(Color.yellow);
    
    g.fillOval(100, 200, 5, 30);
    g.fillOval(100, 200, 30, 5);
    g.fillOval(125, 200, 5, 30);
    g.fillOval(100, 225, 30, 5);
    
    g.fillOval(100, 285, 5, 30);
    g.fillOval(100, 285, 30, 5);
    g.fillOval(125, 285, 5, 30);
    g.fillOval(100, 310, 30, 5);
    g.fillOval(120, 285, 30, 30);
    g.fillOval(80, 285, 30, 30);
    g.fillOval(100, 305, 30, 30);
    g.fillOval(100, 265, 30, 30);
    
    g.setColor(Color.cyan);
    for (int i = 0; i < 50; i++)
    {
      g.drawOval(85 - i, 485 - i, 75 - i, 75 - i);
      g.drawOval(85 + i, 485 + i, 55 + i, 55 + i);
    }
    g.setColor(Color.yellow);
    g.fillOval(120, 500, 30, 30);
    g.fillOval(80, 500, 30, 30);
    g.fillOval(100, 520, 30, 30);
    g.fillOval(100, 480, 30, 30);
    g.fillOval(110, 510, 30, 30);
    g.fillOval(90, 510, 30, 30);
    g.fillOval(110, 490, 30, 30);
    g.fillOval(90, 490, 30, 30);
    

    g.setColor(Color.red);
    
    g.fillOval(300, 200, 5, 30);
    g.fillOval(300, 200, 30, 5);
    g.fillOval(325, 200, 5, 30);
    g.fillOval(300, 225, 30, 5);
    
    g.fillOval(300, 285, 5, 30);
    g.fillOval(300, 285, 30, 5);
    g.fillOval(325, 285, 5, 30);
    g.fillOval(300, 310, 30, 5);
    g.fillOval(320, 285, 30, 30);
    g.fillOval(280, 285, 30, 30);
    g.fillOval(300, 305, 30, 30);
    g.fillOval(300, 265, 30, 30);
    
    g.setColor(Color.green);
    for (int i = 0; i < 50; i++)
    {
      g.drawOval(285 - i, 485 - i, 75 - i, 75 - i);
      g.drawOval(285 + i, 485 + i, 55 + i, 55 + i);
    }
    g.setColor(Color.red);
    g.fillOval(320, 500, 30, 30);
    g.fillOval(280, 500, 30, 30);
    g.fillOval(300, 520, 30, 30);
    g.fillOval(300, 480, 30, 30);
    g.fillOval(310, 510, 30, 30);
    g.fillOval(290, 510, 30, 30);
    g.fillOval(310, 490, 30, 30);
    g.fillOval(290, 490, 30, 30);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.stopReading) {
      dispose();
    }
  }
}
