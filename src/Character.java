import java.awt.Point;

public class Character
{
  private int health;
  private Point location = new Point();
  private int energy;
  private int quadrant;
  private String name;
  
  public Character(String n)
  {
    this.health = 100;
    this.energy = 5;
    this.name = n;
    locationGenerator();
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getHealth()
  {
    return this.health;
  }
  
  public Point getLocation()
  {
    return this.location;
  }
  
  public int getEnergy()
  {
    return this.energy;
  }
  
  public void setName(String n)
  {
    this.name = n;
  }
  
  public void setHealth(int h)
  {
    this.health = h;
  }
  
  public void setLocation(int w, int h)
  {
    this.location.setLocation(w, h);
  }
  
  public void setEnergy(int e)
  {
    this.energy = e;
  }
  
  public int getQuadrant()
  {
    return this.quadrant;
  }
  
  public void setQuadrant(int q)
  {
    this.quadrant = q;
  }
  
  public void locationGenerator()
  {
    int rand = (int)(Math.random() * 4.0D);
    if (rand == 0)
    {
      this.location.setLocation(125, 125);
      this.quadrant = 1;
    }
    else if (rand == 1)
    {
      this.location.setLocation(375, 125);
      this.quadrant = 2;
    }
    else if (rand == 2)
    {
      this.location.setLocation(125, 375);
      this.quadrant = 3;
    }
    else if (rand == 3)
    {
      this.location.setLocation(375, 375);
      this.quadrant = 4;
    }
  }
  
  public String toString()
  {
    return 
    

      "Name: " + this.name + "\nHealth: " + this.health + "\nLocation: " + this.location + "\nEnergy: " + this.energy;
  }
}
