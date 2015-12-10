// Dylan Wright
// APCS1 pd5
// HW45 -- Come Together
// 2015-12-9

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor â€“ initializes 10-item array
    public SuperArray() 
    {
        _data = new Comparable[10];
        _lastPos = -1; //flag to indicate no lastpos yet
        _size = 0;	
    }
    
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    {
        String foo = "[";
        for( int i = 0; i < _size; i++ ) {
            foo += _data[i] + ",";
        }
        //shave off trailing comma
        if ( foo.length() > 1 )
            foo = foo.substring( 0, foo.length()-1 );
        foo += "]";
        return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
        Comparable[] temp = new Comparable[ _data.length * 2 ];
        for( int i = 0; i < _data.length; i++ )
            temp[i] = _data[i];
        _data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
        Comparable temp = _data[index];
        _data[index] = newVal;
        return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add(Comparable newVal) {
        if (_size == _data.length) {
            expand();
        }
        _data[_size] = newVal;
        _lastPos++;
        _size ++;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add(int index, Comparable newVal) {
        if (index >= _size) {
            System.out.println("[Insert Failed]: Index is out of the range");
        }
        else {
            Comparable[] tempdata = new Comparable[_data.length+1];
            for (int i = 0; i < index; i++) {
                tempdata[i] = _data[i];
            }
            tempdata[index] = newVal;
            for (int i = index; i < _size; i++) {
                tempdata[i+1] = _data[i];
            }
            _data = tempdata;
            _lastPos++;
            _size++;
        }
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove(int index) {
        for (int i = index; i < _lastPos; i++) {
            _data[i] = _data[i+1];
        }
        _data[_lastPos] = new Hexadecimal(0);
        _lastPos--;
        _size--;
    }


    //return number of meaningful items in _data
    public int size() {return _size;}

    //return position of target Comparable in array
    //return -1 if target is not in array
    public int linSearch(Comparable target){
        for (int i = 0; i < _data.length; i++){
            if (_data[i] == target){
                return i;
            }
        }
        return -1;
    }
    
    //return boolean of whether or not array is sorted
    public boolean isSorted() {
        for (int i = 0; i < _size; i++) {
            if (_data[i].compareTo(_data[i+1]) < 0) {
                return false;
            }
        }
        return true;
    }

    //main method for testing
    public static void main( String[] args ) {
        SuperArray mayfield = new SuperArray();
        System.out.println("Printing empty SuperArray mayfield...");
        System.out.println(mayfield);
        System.out.println("Printing SuperArray mayfield size...");
        System.out.println(mayfield.size());
        
        Rational rattest = new Rational(100,1);
        Rational mousetest = new Rational(100,2);
        Binary bintest = new Binary(100);
        Binary chesttest = new Binary(30);
        Hexadecimal hexatest = new Hexadecimal(100);
        Hexadecimal curseatest = new Hexadecimal(100);
        
        mayfield.add(rattest);
        mayfield.add(bintest);
        mayfield.add(hexatest);
        mayfield.add(curseatest);
        mayfield.add(chesttest);
        
        System.out.println("Printing populated SuperArray mayfield...");
        System.out.println(mayfield);
        System.out.println("Printing SuperArray mayfield size...");
        System.out.println(mayfield.size());
        
        mayfield.remove(3);
        System.out.println("Printing SuperArray mayfield post-remove...");
        System.out.println(mayfield);
        mayfield.remove(3);
        System.out.println("Printing SuperArray mayfield post-remove...");
        System.out.println(mayfield);
        
        mayfield.add(3,rattest);
        System.out.println("Printing SuperArray mayfield post-insert...");
        System.out.println(mayfield);
        mayfield.add(2,chesttest);
        System.out.println("Printing SuperArray mayfield post-insert...");
        System.out.println(mayfield);
        mayfield.add(1,curseatest);
        System.out.println("Printing SuperArray mayfield post-insert...");
        System.out.println(mayfield);
    }//end main	
}//end class