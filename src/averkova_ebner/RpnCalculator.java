package averkova_ebner;

public class RpnCalculator implements Calculator{
	private Stack stack;

	public RpnCalculator(){

	}

	public void setStack(Stack stack){
		this.stack = stack;      
	}

	public static void main(String[] args){	}
	
	/** returns the result or throws an (Runtime-) Exception if anything goes wrong (e.g. illegal input) */
	public double calc(String[] input){
		try{
			if(input.length <= 0){
				throw new RuntimeException("ERROR! No arguments.");
			}

			for(int i = 0; i < input.length; i++){
				if(input[i] == null || input[i].equals("")) {
					throw new IllegalArgumentException("ERROR! The argument"+"\""+input[i]+"\" is null or empty.");
				}
				if(input[i].charAt(0) >= '0' && input[i].charAt(0) <= '9') {
					stack.push(parseToDouble(input[i]));
				}
				else {					
					Double secondNumber = (Double)stack.pop();
					Double firstNumber = (Double)stack.pop();
					Double result;

					switch (input[i]) {
					case "+":
						result = firstNumber + secondNumber;
						break;
					case "-":
						result = firstNumber - secondNumber;
						break;
					case "/":
						if(secondNumber != 0) 
							result = firstNumber / secondNumber;
						else 
							throw new ArithmeticException("ERROR! Division by zero! Program terminated.");
						break;
					case "*":
						result = firstNumber * secondNumber;
						break;
					default:
						throw new RuntimeException("ERROR! Invalid operator: " + input[i]);
					}

					stack.push(result);
				}
			}         
		}
		catch(Exception e){
			throw e;
		}

		return (double)stack.pop();
	}

	private double parseToDouble(String s){
		try {  
			return Double.parseDouble(s);   
		} 
		catch (NumberFormatException e) {  
			throw new NumberFormatException("ERROR! Could not cast input string "+"\"" + s + "\" to double.");   
		}
	}
}