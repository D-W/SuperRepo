// Dylan Wright
// APCS1 pd5
// HW45 -- Come Together
// 2015-12-9

public class Rational implements Comparable {
    
    private int n; //numerator
    private int d; //denominator

    //default constructor
    public Rational(){
        n = 0;
        d = 1;
    }

    //overloaded constructor
    public Rational(int num, int denom){
        this();
        if (denom == 0)
            System.out.println("invalid denominator value");
        else{
            n = num;
            d = denom;
        }
    }
    
    public int getN() {return n;}
    
    public int getD() {return d;}

    //return string of rational number
    public String toString(){
        String a = Integer.toString(n);
        String b = Integer.toString(d);
        return a + "/" + b;
    }

    public double floatValue(){
        return 1. * n / d;
    }

    //multiplies
    public void multiply(Rational a) {
        n = n * a.n;
        d = d * a.d;	
    }

    //divides
    public void divide(Rational a) {
        n = n * a.d;
        d = d * a.n;	
    }

    //adds
    public void add(Rational a) {
        n = (n * a.d) + (a.n * d);
        d = d * a.d;
    }

    //subtract
    public void subtract(Rational a){
        n = (n * a.d) - (a.n * d);
        d = d * a.d;
    }
    
    //greatest of given numbers, OLD CODE
    public int max(int a, int b){//using integer inputs
        if (a < b)return b;
        return a;
    }
    
    //smallest of given numbers, OLD CODE
    public int min(int a, int b){//using integer inputs
        if (a < b)return a;
        return b;
    }

    //greatest of given numbers, STATIC
    public static int max2(int a, int b){//using integer inputs
        if (a < b)return b;
        return a;
    }
    //smallest of given numbers, STATIC
    public static int min2(int a, int b){//using integer inputs
        if (a < b)return a;
        return b;
    }

    //finds le gcd of n and d, OLD CODE
    public int gcd(){
        if (n == d) return n;
        int greater = max(n,d); //finds greater of two num
        int smaller = min(n,d); //finds lesser of two num
        int GCD = smaller;
        while (greater%GCD != 0 || smaller%GCD != 0){ //if nums arent divisible by same num
            GCD = greater%smaller; //divide greater by smaller
            greater = smaller;
        }
        return GCD;
    }

    //finds le gcd of n and d, STATIC
    public static int gcd2(int num, int denom){
        if (num == denom) return num;
        int greater = max2(num,denom); //finds greater of two num
        int smaller = min2(num,denom); //finds lesser of two num
        int GCD = smaller;
        while (greater%GCD != 0 || smaller%GCD != 0){ //if nums arent divisible by same num
            GCD = greater%smaller; //divide greater by smaller
            greater = smaller;
        }
        return GCD;
    }

    //dividing both numerator and denominator by gcd to simplify
    public void reduce(){
        int GCD = gcd();
        n /= GCD;
        d /= GCD;
    }

    //check to see if two rational numbers are equal
    public int compareTo(Object other){
        if (other instanceof Comparable) {
            if (other instanceof Hexadecimal) {
                return (int)(n / (d * 1.0)) - ((Hexadecimal)other).getDec();
            }
            if (other instanceof Binary) {
                return (int)(n / (d * 1.0)) - ((Binary)other).getDec();
            }
            if (other instanceof Rational) {
                return (int)(n / (d * 1.0)) - (int)(((Rational)other).getN() / (((Rational)other).getD() * 1.0));
            }
        }
        if (other == null) {
            throw new NullPointerException("\ncompareTo() input not a Comparable");
        }
        throw new ClassCastException("\ncompareTo() input not a Comparable");
    }

    //override le equals method
    public boolean equals(Object other){
	//compares floatValue of both objects
        return this.floatValue() == ((Rational)other).floatValue();
    }

    public static void main(String[] args) {
        Rational r = new Rational(2,3); //Stores the rational number 2/3
        Rational s = new Rational(1,2); //Stores the rational number 1/2
        Rational t = new Rational(4,18); //Stores the rational number 4/18

        System.out.println("r = " + r + " = " + r.floatValue());
        //should be 2/3  = 0.6666666666666666
        System.out.println("s = " + s + " = " + s.floatValue());
        //should be 1/2 = 0.5
        
        //test multiply
        System.out.print(r + " x " + s + " = ");
        r.multiply(s); //Multiplies r by s, changes r to 2/6.  s remains 1/2
        System.out.println(r);
        
        System.out.print(s + " x " + r + " = ");
        s.multiply(r); //Multiplies s by r, changes s to 2/12.  r remains 2/6
        System.out.println(s);
        
        System.out.println("r = " + r); //should be 2/6
        System.out.println("s = " + s); //should be 2/12
        
        //test divide
        System.out.print(s + " / " + r + " = ");
        s.divide(r); //Divides s by r, changes s to 12/24.  r remains 2/6
        System.out.println(s);
        
        System.out.print(r + " / " + s + " = ");
        r.divide(s); //Divides r by s, changes r to 48/72.  s remains 12/24
        System.out.println(r);
        
        System.out.println("r = " + r + " = " + r.floatValue());
        //should be 48/72 = 0.6666666666666666
        System.out.println("s = " + s + " = " + s.floatValue());
        //should be 12/24 = 0.5
        
        System.out.println("------------------------------------");
        
        //PHASE 2
        //resets values
        r.n = 2;
        r.d = 3;
        s.n = 1;
        s.d = 2;
        
        System.out.println("r = " + r); //should be 2/3
        r.add(s);  //Adds r to s, changes r to 7/6.  s remains 1/2
        System.out.println("s = " + s); //should be 1/2
        System.out.println("r = " + r); //should be 7/6
        System.out.println("t = " + t); //should be 4/18
        t.reduce(); //Changes t to 2/9
        System.out.println("t = " + t); //should be 2/9
        
        System.out.println("------------------------------------");
        
        //PHASE 3
        //resets values
        r.n = 12;
        r.d = 36;
        s.n = 7;
        s.d = 14;
        t.n = 14;
        t.d = 28;
        
        System.out.println("r = " + r); //should be 12/36
        r.gcd2(r.n,r.d);  //should be 12
        System.out.println("s = " + s); //should be 7/14
        
        System.out.println("t = " + t); //should be 14/28
        System.out.println(t.compareTo(s)); //should be 0 since equal
        
        //PHASE 4
        System.out.println(t.equals(s)); //true (proven by compareTo from above)
        System.out.println(t.equals(r)); //false
    }
}