import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Options
  extends JFrame
  implements ActionListener
{
  private String currentSong = "music.wav";
  private JButton chooseMusic = new JButton("MUTE");
  private JButton returnToGame = new JButton("Return");
  private JButton quit = new JButton("Quit");
  private JButton instructionsButton = new JButton("-->READ!<--");
  private AudioStream as;
  private PlaySuperBattleGame bg;
  private Timer musicTimer = new Timer(408000, this);
  
  public Options(PlaySuperBattleGame b)
  {
    super("Options");
    this.bg = b;
    displayOptionsBox();
  }
  
  public String getCurrentSong()
  {
    return this.currentSong;
  }
  
  public void setCurrentSong()
  {
    if (this.chooseMusic.getText().equals("MUTE"))
    {
      this.musicTimer.start();
      this.chooseMusic.setText("MUSIC: ON");
      try
      {
        InputStream is = new FileInputStream(this.currentSong);
        this.as = new AudioStream(is);
        


        AudioPlayer.player.start(this.as);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      this.chooseMusic.setText("MUTE");
      this.musicTimer.stop();
      AudioPlayer.player.stop(this.as);
    }
  }
  
  public void closeWindow()
  {
    dispose();
  }
  
  public void openWindow()
  {
    show();
  }
  
  public void quitGame()
  {
    System.exit(0);
  }
  
  public void displayOptionsBox()
  {
    setSize(207, 500);
    setLayout(null);
    setResizable(false);
    JTextArea instructions = new JTextArea("A-Attack a quadrant of your choice.\n\tCosts 1 energy\n\nC-Charge energy on the spot.\n\tNo Cost\n\nM-Move to a quadrant of your choice.\n\tNo Cost\n\n!-Ultimate Attack (Prepare to Die).\n\tCosts 5 energy");
    
    instructions.setBounds(0, 150, 200, 300);
    instructions.setOpaque(false);
    instructions.setEditable(false);
    this.instructionsButton.setBounds(50, 350, 100, 50);
    this.instructionsButton.addActionListener(this);
    this.chooseMusic.setBounds(50, 50, 100, 50);
    this.chooseMusic.addActionListener(this);
    this.returnToGame.setBounds(0, 400, 100, 50);
    this.returnToGame.addActionListener(this);
    this.quit.setBounds(100, 400, 100, 50);
    this.quit.addActionListener(this);
    add(this.chooseMusic);
    add(this.returnToGame);
    add(this.quit);
    add(this.instructionsButton);
    add(instructions);
    getContentPane().setBackground(new Color(255, 153, 0));
    setVisible(true);
    setDefaultCloseOperation(0);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.musicTimer) {
      setCurrentSong();
    }
    if ((e.getSource() == this.chooseMusic) || (e.getSource() == this.musicTimer)) {
      setCurrentSong();
    }
    if (e.getSource() == this.quit) {
      quitGame();
    }
    if (e.getSource() == this.returnToGame)
    {
      closeWindow();
      this.bg.startTimer();
      TurnGUI.reenableButtons();
      if (this.bg.getHeroEnergy() < 5) {
        TurnGUI.singleDisable(3);
      }
      if (this.bg.getHeroEnergy() == 0) {
        TurnGUI.singleDisable(0);
      }
    }
    if (e.getSource() == this.instructionsButton) {
      new InstructionFrame();
    }
  }
}
