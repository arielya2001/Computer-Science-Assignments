package Ex6;

public class Card {
    private int _val, _shape;
    public Card(int v, int s) {
        if (v < 1 || v > 13 || s < 1 || s > 4) {
            throw new RuntimeException("invalid");
        }
        _val = v;
        _shape = s;
    }


    public int getValue()
    {
        return _val;
    }
    public int getShape()
    {
        return _shape;
    }


        }
