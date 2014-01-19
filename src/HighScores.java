import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HighScores
  extends JFrame
  implements ActionListener
{
  private ArrayList<Integer> scores = new ArrayList();
  private ArrayList<String> scoreNames = new ArrayList();
  private ArrayList<JLabel> scoreLabels = new ArrayList();
  private JLabel titleLabel = new JLabel("HIGHSCORES!!!!");
  private JButton clear = new JButton("Clear Scores");
  
  public HighScores()
    throws IOException
  {
    sortScores();
    getScores();
    getScoreNames();
    setScores(this.scores, this.scoreNames);
    labelScores();
    displayHighScore();
  }
  
  public HighScores(int stbs, String sn)
    throws IOException
  {
    sortScores();
    getScores();
    getScoreNames();
    addHighScore(stbs, sn);
    setScores(this.scores, this.scoreNames);
    labelScores();
    displayHighScore();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.clear)
    {
      clearScores();
      
      dispose();
      try
      {
        new HighScores();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
  }
  
  public ArrayList<String> getScoreNames()
    throws IOException
  {
    int counter = 0;
    IO.openInputFile("HighScores.txt");
    String s = IO.readLine();
    do
    {
      this.scoreNames.add(counter, s.substring(0, s.indexOf(" ")));
      counter++;
      s = IO.readLine();
    } while (s != null);
    IO.closeInputFile();
    return this.scoreNames;
  }
  
  public ArrayList<Integer> getScores()
    throws IOException
  {
    int counter = 0;
    IO.openInputFile("HighScores.txt");
    String s = IO.readLine();
    do
    {
      this.scores.add(counter, Integer.valueOf(Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1))));
      counter++;
      s = IO.readLine();
    } while (s != null);
    IO.closeInputFile();
    return this.scores;
  }
  
  public void labelScores()
  {
    for (int i = 0; i < this.scores.size(); i++) {
      this.scoreLabels.add(i, new JLabel((String)this.scoreNames.get(this.scores.size() - (i + 1)) + "     " + this.scores.get(this.scores.size() - (i + 1))));
    }
  }
  
  public void setScores(ArrayList<Integer> s, ArrayList<String> sn)
  {
    IO.createOutputFile("HighScores.txt");
    IO.openInputFile("HighScores.txt");
    for (int i = 0; i < s.size(); i++) {
      IO.println((String)sn.get(i) + "     " + s.get(i));
    }
    IO.closeOutputFile();
  }
  
  public void displayHighScore()
  {
    setSize(200, this.scoreLabels.size() * 20 + 175);
    setLayout(null);
    setResizable(false);
    getContentPane().setBackground(new Color(255, 153, 0));
    for (int i = 0; i < this.scoreLabels.size(); i++)
    {
      ((JLabel)this.scoreLabels.get(i)).setBounds(10, i * 20 + 20, 200, 30);
      add((Component)this.scoreLabels.get(i));
    }
    this.titleLabel.setBounds(50, 15, 100, 10);
    add(this.titleLabel);
    this.clear.addActionListener(this);
    this.clear.setBounds(0, this.scoreLabels.size() * 20 + 50, 200, 100);
    add(this.clear);
    setVisible(true);
    setDefaultCloseOperation(3);
  }
  
  public void clearScores()
  {
    IO.createOutputFile("HighScores.txt");
    IO.println("Tian     9001");
    IO.closeOutputFile();
  }
  
  public void addHighScore(int s, String sn)
  {
    this.scores.add(Integer.valueOf(s));
    Organizer.insertionSort(this.scores);
    this.scoreNames.add(Organizer.binarySearch(this.scores, s), sn);
  }
  
  public void sortScores()
  {
    for (int i = 1; i < this.scores.size(); i++)
    {
      int j = i;
      int currentElement = ((Integer)this.scores.get(j)).intValue();
      String currentNamement = (String)this.scoreNames.get(j);
      while ((j > 0) && (((Integer)this.scores.get(j - 1)).intValue() > currentElement))
      {
        this.scores.set(j, (Integer)this.scores.get(j - 1));
        this.scoreNames.set(j, (String)this.scoreNames.get(j - 1));
        j--;
      }
      this.scores.set(j, Integer.valueOf(currentElement));
      this.scoreNames.set(j, currentNamement);
    }
  }
}
