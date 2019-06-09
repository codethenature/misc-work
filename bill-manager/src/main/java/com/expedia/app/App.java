package com.expedia.app;

import java.util.Scanner;

import com.expedia.app.calc.BillManager;
import com.expedia.app.calc.Parser;
import com.expedia.app.domain.Bill;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] lines )
    {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter no. products you want to purchase = ");
    	int n = sc.nextInt();
    	sc.nextLine();
    	String[] input = new String[n];
    	
    	int i=0;
    	while(n--!=0) {
    		input[i++] = sc.nextLine();
    	}
    	sc.close();
    	
    	String output[] = run(input);
    	
    	print(output);
    }
    
    public static String[] run(String[] lines) {
    	
    	Bill bill = Parser.parse(lines);
    	
    	String[] output = BillManager.calculate(bill );
    	
    	return output;
    }
    
    public static void print(String[] lines) {
    	for(String line : lines) {
    		System.out.println(line);
    	}
    }
}
