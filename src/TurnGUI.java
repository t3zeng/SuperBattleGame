import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TurnGUI
  extends JFrame
  implements ActionListener
{
  private static JButton[] actions = new JButton[4];
  static int quadrant;
  static boolean attack;
  static boolean charge;
  static boolean move;
  static boolean kamehameha;
  static boolean madeMove = false;
  private PlaySuperBattleGame bg;
  
  public TurnGUI(PlaySuperBattleGame bg)
  {
    super(bg.getScoreName() + "'s Command Window");
    setSize(205, 78);
    setLayout(null);
    setResizable(false);
    this.bg = bg;
    quadrant = bg.getHeroQuadrant();
    actions[0] = new JButton("A");
    actions[1] = new JButton("C");
    actions[2] = new JButton("M");
    actions[3] = new JButton("!");
    for (int i = 0; i < actions.length; i++)
    {
      actions[i].addActionListener(this);
      actions[i].setBounds(i * 50, 0, 50, 50);
      actions[i].setActionCommand(actions[i].getText());
      add(actions[i]);
    }
    setVisible(true);
    setDefaultCloseOperation(0);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      if (e.getSource() == actions[0])
      {
        do
        {
          String temp = JOptionPane.showInputDialog(this, "Choose quadrant number");
          quadrant = Integer.parseInt(temp);
        } while ((quadrant < 1) || (quadrant > 4));
        attack = true;
        charge = false;
        move = false;
        kamehameha = false;
      }
      else if (e.getSource() == actions[1])
      {
        attack = false;
        charge = true;
        move = false;
        kamehameha = false;
      }
      else if (e.getSource() == actions[2])
      {
        do
        {
          String temp = JOptionPane.showInputDialog(this, "Choose quadrant number");
          quadrant = Integer.parseInt(temp);
        } while ((quadrant < 1) || (quadrant > 4));
        attack = false;
        charge = false;
        move = true;
        kamehameha = false;
      }
      else if (e.getSource() == actions[3])
      {
        do
        {
          String temp = JOptionPane.showInputDialog(this, "Choose quadrant number");
          quadrant = Integer.parseInt(temp);
        } while ((quadrant < 1) || (quadrant > 4));
        attack = false;
        charge = false;
        move = false;
        kamehameha = true;
      }
    }
    catch (Exception g) {}
    do
    {
      String temp = JOptionPane.showInputDialog(this, "Choose quadrant number");
      quadrant = Integer.parseInt(temp);
    } while ((quadrant < 1) || (quadrant > 4));
    madeMove = true;
    if (madeMove) {
      for (int i = 0; i < actions.length; i++) {
        actions[i].setEnabled(false);
      }
    }
    this.bg.actionPerformed(e);
  }
  
  public static boolean getAttack()
  {
    return attack;
  }
  
  public static boolean getCharge()
  {
    return charge;
  }
  
  public static boolean getMove()
  {
    return move;
  }
  
  public static boolean getKamehameha()
  {
    return kamehameha;
  }
  
  public static boolean hasMadeMove()
  {
    return madeMove;
  }
  
  public static void setMadeMove(boolean m)
  {
    madeMove = m;
  }
  
  public static int getQuadrant()
  {
    return quadrant;
  }
  
  public static void reenableButtons()
  {
    for (int i = 0; i < actions.length; i++) {
      actions[i].setEnabled(true);
    }
  }
  
  public static void disableButtons()
  {
    for (int i = 0; i < actions.length; i++) {
      actions[i].setEnabled(false);
    }
  }
  
  public static void singleDisable(int b)
  {
    actions[b].setEnabled(false);
  }
  
  public static void singleEnable(int b)
  {
    actions[b].setEnabled(true);
  }
}
