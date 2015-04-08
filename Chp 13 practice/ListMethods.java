import java.util.*;

public class ListMethods
{
   public static ArrayList<Integer> makeList(int n)
   {
      ArrayList<Integer> tempList = new ArrayList<Integer>();
      tempList.add(n);

      if (n <= 0)  // The smallest list we can make
      {
          return null;
      }
      else        // All other size lists are created here
      {
          if (tempList.size()==n)
          {return tempList;}
          else
          { 
              tempList = makeList(n-1);
              tempList.add(n-1, n);       
          }
      }      
      return tempList;
   }
   
   public static void main (String[] args)
   {
       System.out.println(makeList(120));
    }
}