import java.util.ArrayList;

public class Organizer
{
  public static void insertionSort(ArrayList<Integer> numbers)
  {
    for (int i = 1; i < numbers.size(); i++)
    {
      int j = i;
      int currentElement = ((Integer)numbers.get(j)).intValue();
      while ((j > 0) && (((Integer)numbers.get(j - 1)).intValue() > currentElement))
      {
        numbers.set(j, (Integer)numbers.get(j - 1));
        j--;
      }
      numbers.set(j, Integer.valueOf(currentElement));
    }
  }
  
  public static int binarySearch(ArrayList<Integer> a, int key)
  {
    int low = 0;
    
    int high = a.size() - 1;
    while (low <= high)
    {
      int mid = (low + high) / 2;
      if (((Integer)a.get(mid)).intValue() > key) {
        high = mid - 1;
      } else if (((Integer)a.get(mid)).intValue() < key) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
}
