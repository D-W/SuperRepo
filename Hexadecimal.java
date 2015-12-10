// Dylan Wright
// APCS1 pd5
// HW45 -- Come Together
// 2015-12-9

//skeleton file for class Hexadecimal

public class Hexadecimal implements Comparable {

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF"; 


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() {
        _decNum = 0;
        _hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to hex equiv string
      =====================================*/
    public Hexadecimal(int n) {
        _decNum = n;
        _hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative Hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal(String s) {
        _decNum = hexToDec(s);
        _hexNum = s;
    }

    public int getDec() {return _decNum;}
    
    public String getHex() {return _hexNum;}
    
    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of hex representing value of this Object
      =====================================*/
    public String toString() {
        return _hexNum;
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to Hexadecimal
      pre:  n >= 0
      post: returns String of hex
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "2"
      decToHex(3) -> "3"
      decToHex(14) -> "E"
      =====================================*/
    public static String decToHex(int n) {
        String retStr = "";
        if (n == 0) {
            return "0";
        }
        while (n != 0) {
            retStr = (HEXDIGITS.substring(n % 16, (n % 16) + 1)) + retStr;
            n /= 16;
        }
        return retStr;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to Hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "2"
      decToHexR(3) -> "3"
      decToHexR(14) -> "E"
      =====================================*/
    public static String decToHexR(int n) {
        if (n < 16) {
            return HEXDIGITS.substring(n, n+1);
        }
        else {
            return decToHexR(n/16) + HEXDIGITS.substring(n%16, (n%16)+1);
        }
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to Hexadecimal
      pre:  s represents non-negative Hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("2") -> 2
      hexToDec("3") -> 3
      hexToDec("E") -> 14
      =====================================*/
    public static int hexToDec(String s) {
        int retNum = 0;
        for (int i = 0; i < s.length(); i++) {
            retNum += HEXDIGITS.indexOf(s.substring(i, i+1)) * ((int)Math.pow(16,s.length()-(i+1)));
        }
        return retNum;
    }

    /*=====================================
      String hexToDecR(String) -- converts base-10 input to Hexadecimal, recursively
      pre:  s represents non-negative Hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("2") -> 2
      hexToDecR("3") -> 3
      hexToDecR("E") -> 14
      =====================================*/
    
    public static int hexToDecR(String s) {
        if (s.length() == 1) {
            return HEXDIGITS.indexOf(s);
        }
        else {
            return 16 * hexToDecR(s.substring(0, s.length()-1)) + HEXDIGITS.indexOf(s.substring(s.length()-1));
        }
    }
    
    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal Hexadecimal values
      =============================================*/
    public boolean equals(Object other) {
        return (this == other) || (other instanceof Hexadecimal && _hexNum.equals(((Hexadecimal) other)._hexNum));
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Comparable objects is greater
      pre:  other is instance of class Comparable
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo(Object other) {
        if (other instanceof Comparable) {
            if (other instanceof Hexadecimal) {
                return _decNum - ((Hexadecimal)other).getDec();
            }
            if (other instanceof Binary) {
                return _decNum - ((Binary)other).getDec();
            }
            if (other instanceof Rational) {
                return _decNum - (int)(((Rational)other).getN() / (((Rational)other).getD() * 1.0));
            }
        }
        if (other == null) {
            throw new NullPointerException("\ncompareTo() input not a Comparable");
        }
        throw new ClassCastException("\ncompareTo() input not a Comparable");
    }


    //main method for testing
    public static void main( String[] args ) {
        System.out.println();
        System.out.println( "Testing ..." );
        
        Hexadecimal b1 = new Hexadecimal(16);
        Hexadecimal b2 = new Hexadecimal(16);
        Hexadecimal b3 = b1;
        Hexadecimal b4 = new Hexadecimal(31);
        
        System.out.println( b1 );
        System.out.println( b2 );
        System.out.println( b3 );       
        System.out.println( b4 );       
        
        System.out.println( "\n==..." );
        System.out.println( b1 == b2 ); //should be false
        System.out.println( b1 == b3 ); //should be true
        
        System.out.println( "\n.equals()..." );
        System.out.println( b1.equals(b2) ); //should be true
        System.out.println( b1.equals(b3) ); //should be true
        System.out.println( b3.equals(b1) ); //should be true
        System.out.println( b4.equals(b2) ); //should be false
        System.out.println( b1.equals(b4) ); //should be false
        
        System.out.println( "\n.compareTo..." );
        System.out.println( b1.compareTo(b2) ); //should be 0
        System.out.println( b1.compareTo(b3) ); //should be 0
        System.out.println( b1.compareTo(b4) ); //should be neg
        System.out.println( b4.compareTo(b1) ); //should be pos
    }//end main()

} //end class