import java.io.*;
public class Scanner {
	private char ch = ' ';
	private int intValue = 0;
	private Buffer buffer;
	public int token;
	public boolean start = true;
	public Scanner (DataInputStream in) {
	    buffer = new Buffer(in);
	} 


	public int getToken ( ) {
	    while (Character.isWhitespace(ch))
	    {
	    	if(!start)
	    	{
	    		return token;
	    	}
	    	ch = buffer.get();
	    	start = false;
	    }
	    if (Character.isDigit(ch)) {
		intValue = getNumber();
		token = Token.number;
	    } 
	    else {
	    	switch (ch) {

	    	case '.': ch = buffer.get( );
			token = Token.period;
			break;
			
		    case '+': ch = buffer.get( );
			token = Token.plusop;
			break;

		    case '-': ch = buffer.get( );
			token = Token.minusop;
			break;

		    case '*': ch = buffer.get( );
			token = Token.timesop;
			break;

		    case '/': ch = buffer.get( );
			token = Token.divideop;
			break;

		    case '=': ch = buffer.get( );
			token = Token.equalsop;
			break;

		    case '(': ch = buffer.get( );
			token = Token.leftP;
			break;

		    case ')': ch = buffer.get( );
			token = Token.rightP;
			break;

		    default: error ("Illegal character " + ch );
			break;
		}

	    }
	    return token;
	}


	public int number () {
	    return intValue;
	} 

	public void match (int which) {
	    token = getToken( );
	    if (token!= which) {
		error("Invalid token " + Token.toString(token) +
		      "-- expecting " + Token.toString(which));
		System.exit(1);
	    } 
	}


	public void error (String msg) {
	    System.err.println(msg);
	    System.exit(1);
	} 


	private int getNumber ( ) {
	    int rslt = 0;
	    do {
		rslt = rslt * 10 + Character.digit(ch, 10);
		ch = buffer.get( );
	    } while (Character.isDigit(ch));
	    return rslt;
	}

}

class Buffer {
	private String line = "";
	private int column = 0;
	private int lineNo = 0;
	private DataInputStream  in;
	public Buffer (DataInputStream in) {
	    this.in = in;
	} 


	@SuppressWarnings("deprecation")
	public char get () {
	    column++;
	    if (column >= line.length()) {
	    	try {
	    	    line = in.readLine();
	    	} catch (Exception e) {
	    	    System.err.println("Invalid read operation");
	    	    System.exit(1);
	    	} 
	    	if (line == null) 
	    	    {System.exit(0);}
	    	column = 0;
	    	lineNo++;
	    	line = line + "\n";
	    }	
	    return line.charAt(column);
	}

}
