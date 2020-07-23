import java.util.*;
import java.io.*;

public class Calculator1 {

	static Stack<Double> valStack = new Stack<Double>();
	static Stack<String> opStack = new Stack<String>();
	static boolean finishedOp = false;

	/**
	 * Function which determines if the character is a number or not
	 * @param character the Character to be determined
	 * @return returns if character is a number or not
	 */
	public static boolean isNumber(char character)
	{
		String strCharacter = Character.toString(character);
		try
		{
			Double.parseDouble(strCharacter);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Function which establishes the priorities of operations
	 * @param pemdas String which is an operation
	 * @return returns the number established for the priority
	 */
	public static int precedence(String pemdas)
	{
		int prec = -1;
		if(pemdas.contentEquals("$"))
			prec = 0;
		if(pemdas.contentEquals("(") || pemdas.contentEquals(")"))
			prec = 1;
		else if(pemdas.contentEquals("!"))
			prec = 2;
		else if(pemdas.contentEquals("-"))
			prec = 3;
		else if(pemdas.contentEquals("^"))
			prec = 3;
		else if(pemdas.contentEquals("*") || pemdas.contentEquals("/"))
			prec = 4;
		else if(pemdas.contentEquals("+") || pemdas.contentEquals("-"))
			prec = 5;
		else if(pemdas.contentEquals("<") || pemdas.contentEquals(">") || pemdas.contentEquals("<=") || pemdas.contentEquals(">=="))
			prec = 6;
		else if(pemdas.contentEquals("==") || pemdas.contentEquals("!="))
			prec = 7;
		return prec;
	}

	/**
	 * Function which executes the mathematical expression
	 * @param pw Printwriter to write result
	 */
	public static void doOp(PrintWriter pw)
	{
		//Variable Declarations
		String op = opStack.peek();
		double x = 0;
		double y = 0;
		double answer = 0;
		while(op.equals("(") || op.equals(")"))
		{
			opStack.pop();
			op = opStack.peek();
		}
		if(op.equals("+"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			answer = y + x;
			System.out.println(y + "+" + x + "=" + answer);
			pw.println(answer);
			valStack.push(answer);
		}
		else if(op.equals("-"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			answer = y - x;
			System.out.println(x + "-" +y + "=" + answer);
			pw.println(answer);
			valStack.push(answer);
		}
		else if(op.equals("*"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			answer = y*x;
			System.out.println(x + "*" + y + "=" + answer);
			pw.println(answer);
			valStack.push(answer);
		}
		else if(op.equals("/"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			answer = y/x;
			System.out.println(x + "/" + y + "=" + answer);
			pw.println(answer);
			valStack.push(answer);
		}
		else if(op.equals("^"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			answer = 1;
			for(int j = 0; j < x; j++)
			{
				answer = answer*y;
			}
			if(y == 0)
				answer = 0;
			System.out.println(y + "^" + x + "=" + answer);
			pw.println(answer);
			valStack.push(answer);
		}
		else if(op.equals("<"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y < x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}


		}
		else if(op.equals(">"))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y > x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}
		}
		else if(op.equals("<="))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y <= x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}

		}
		else if(op.equals(">="))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y >= x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}
		}
		else if(op.equals("!="))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y != x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}

		}
		else if(op.equals("=="))
		{
			opStack.pop();
			x = valStack.pop();
			y = valStack.pop();
			if(y == x)
			{
				System.out.println("True");
				pw.println("True");
			}
			else
			{
				System.out.println("False");
				pw.println("False");
			}

		}
		else if(op.equals("!"))
		{
			opStack.pop();
			x = valStack.pop();
			answer = 1;
			for(int i = 2;i <= x; i++)
			{
				answer *= i;
			}
			System.out.println("factorial answer: "+ answer);
			pw.println(answer);
			valStack.push(answer);
		}
		
	}
	
	/**
	 * Function which repeats operations until there is only one left
	 * @param operator String which contains the operation of the expression
	 * @param pw PrintWriter to print to txt file
	 */
	public static void repeatOps(String operator, PrintWriter pw)
	{
		//Execute operation if higher precedence, except if operation stack is empty or if there is only 1 value in value stack
		while(valStack.size() > 1 && precedence(operator) <= precedence(opStack.peek()))
		{
			doOp(pw);
		}
	}
	
	/**
	 * Function which evaluates the expression
	 * @param fileName name of the file which the expressions will be evaluated from
	 */
	public static void EvalExp(String fileName)
	{
		Scanner sc;
		PrintWriter pw;

		try
		{
			//variable declaration
			sc = new Scanner(new FileInputStream(fileName));
			pw = new PrintWriter(new FileOutputStream("Results.txt"));
			String expression;
			
			//Do every expression of the file
			while(sc.hasNextLine())
			{
				//Clear value stack for next line of the text file
				valStack.clear();
				expression = sc.nextLine();
//				System.out.println("\n" + expression);
				pw.println("\n" + expression);
				char character = '.';
				String number = "";
				String op = "";
				int length = expression.length();
				int n = 0;
				//Go character by character for every characters of the expression
				while(n < length)
				{
					number = "";
					op = "";
					character = expression.charAt(n);
					//if spacebar then skip
					if(character == ' ')
					{
						n++;
						continue;
					}
					//if number then add to value stack
					if(isNumber(character))
					{
						while(n < length && isNumber(character))
						{
							number += character;
							n++;
							if(n < length)
								character = expression.charAt(n);
						}
						System.out.println("pushed number: " + number);
						valStack.push(Double.parseDouble(number));
						n--;
					}
					//if operator 
					else
					{
						while(n < length && !isNumber(character))
						{							
							if(character != '<' && character != '>' && character != '!' && character != '=' && !(op.length() > 0))
							{
								op = Character.toString(character);
								break;
							}
							else if(character == '!')
							{

								if(n == length - 1 || expression.charAt(n+1) != '=')
								{
									op = Character.toString(character);
									break;
								}
								else
								{
									op += character;
									n++;
									if(n < length)
										character = expression.charAt(n);
								}
							}
							else
							{
								if(character != '(' && character != ')')
								{
									op += character;
									n++;
									if(n < length)
										character = expression.charAt(n);
								}
								else
									break;
							}
						}
						if(op.equals(">") || op.equals(">=") || op.equals("<=") || op.equals("<") || op.equals("==") || op.equals("!="))
							n--;
						repeatOps(op, pw);
						opStack.push(op);
						if(opStack.peek().equals("!"))
							doOp(pw);
						System.out.println("pushed operator: " + op);
						op = "";

					}
					n++;
				}
				//Execute final operation
				repeatOps("$",pw);
			}
			pw.close();
		}
		catch(IOException e)
		{
			System.out.println("IO error");
		}
	}
	public static void main(String[] args) 
	{
		EvalExp("expressions.txt");
	}

}
