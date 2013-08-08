import java.io.*;

public class RDParsing{

    private Scanner scanner;


public RDParsing(Scanner scanner) {
    this.scanner = scanner;
}


public void run () {
    scanner.getToken();
    while(scanner.token!=Token.period)
    {
    	int value=group();
        System.out.println("= "+value);
        System.out.println("The group fits within the rules.");
        scanner.start=true;
        scanner.getToken();
    }
} 


private int group () {
    //group ::= '(' expression ')'
    if (scanner.token== Token.leftP)
	{
    	scanner.getToken();
    }
    int value=expression();
    if (scanner.token!= Token.rightP)
		scanner.error("Missing ')'");
    scanner.start=false;
    scanner.getToken();
    return value;
    
} 


private int expression () {
    //expression ::= term (( '+' term | ('-' term))*

	int left = term( );
    while (scanner.token == Token.plusop || scanner.token == Token.minusop) {
    	int saveToken = scanner.token;
    	scanner.getToken( );
    	switch (saveToken) {
	    	case Token.plusop:
	    		left += term( );
	    		break;
	    	case Token.minusop:
	    		left -= term( );
	    		break;
    	} 
    }

    return left;
} 


private int term () {
    //term ::=  factor (( '*' factor) | ( '/' factor))*

    int left = factor( );
    while (scanner.token == Token.timesop || 
	   scanner.token == Token.divideop) {
	int saveToken = scanner.token;
	scanner.getToken( );
	switch (saveToken) {
	    case Token.timesop:
		left *= factor( );
		break;
	    case Token.divideop:
		left /= factor( );
		break;
	}
    }

    return left;
}


private int factor ( ) {
    //factor ::=  (integer) | (group)

    int value = 0;
    switch (scanner.token) {
	case Token.number:
	    value = scanner.number( );
	    scanner.getToken( );
	    break;
	case Token.leftP:
	    value = group();
	    break;
	default:
	    scanner.error("Expecting number or (");
	    break;
    }

    return value;
}

} 
