class Token {
	public static final int period    = 0;
	public static final int plusop    = 1;	
	public static final int minusop   = 2;
	public static final int timesop   = 3;
	public static final int divideop  = 4;
	public static final int equalsop  = 5;
	public static final int leftP     = 6;
	public static final int rightP    = 7;
	public static final int number    = 8;
	private static String[] symbol = {
		".", "+", "-", "*", "/", "=", "(", ")",
		 "number"};

	public static String toString (int i) {
		if (i < 0 || i > number)
			return "";
		return symbol[i];
	}
} 
