package Ex6;

import java.util.ArrayList;
import java.util.Collections;

public class PackOfCards implements Cards{
   private ArrayList<Card>pack;
    public PackOfCards()
   {
       pack=new ArrayList<Card>(52);
       for (int i = 1; i <=13 ; i++) {
           for (int j = 1; j <=4 ; j++)
           {
               Card c=new Card(i,j);
               pack.add(c);
           }
       }
   }

    @Override
    public void shuffle()
    {
        for (int i = 0; i < pack.size(); i++)
        {
            int r= (int)(Math.random()*(pack.size()-i))+i;
            Collections.swap(pack,i,r);
        }
    }
    public void swap(ArrayList<Card>a,int i, int j)
    {
        Card temp=a.get(i);
       a.set(i,a.get(j));
       a.set(j,temp);
    }

    @Override
    public Card takeTopCard()
    {
        return pack.remove(0);

    }

    @Override
    public int size() {
        return pack.size();
    }
}
