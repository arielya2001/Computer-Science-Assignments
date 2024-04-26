package Ex6;

public class Fraction {
    private int denominator, nominator;

    public Fraction() {
        this.nominator = 0;
        this.denominator = 1;
    }

    public Fraction(int nominator, int denominator) {
        if(denominator==0)
            throw new RuntimeException("cannot be 0");
        this.nominator = nominator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(Fraction f) {
        this(f.nominator, f.denominator);
    }
    public Fraction plus(Fraction f)
    {
        int n=f.nominator*denominator+f.denominator*nominator;
        int d= f.denominator*denominator;
        return new Fraction(n,d);
    }

    public Fraction minus(Fraction f)
    {
        int n=f.nominator*denominator-f.denominator*nominator;
        int d= f.denominator*denominator;
        return new Fraction(n,d);

    }

    public Fraction mul(Fraction f)
    {
        int n=f.nominator*nominator;
        int d= f.denominator*denominator;
        return new Fraction(n,d);

    }

    public Fraction div(Fraction f)
    {
        int n=f.nominator*denominator;
        int d= f.denominator*nominator;
        return new Fraction(n,d);
    }


    private int gcd(int a, int b) {
        int ans = 1;
        if (a == b) {
            ans = a;
            return ans;
        }
        if (a > b) {
            ans = gcd(a - b, b);
        } else ans = gcd(b, a);

        return ans;
    }

    private void simplify()
    {
        int gcd=gcd(nominator,denominator);
        this.nominator=nominator/gcd;
        this.denominator=denominator/gcd;
    }


}
