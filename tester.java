
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class tester {
    
	
	public static void main(String args[]) throws FileNotFoundException{
		ArrayList<String> aa=new ArrayList<String>();
		aa.add("I");
		aa.add("like");
		aa.add("the");
		aa.add("big");
		aa.add("blue");
		aa.add("dog");
		aa.add("better");
		aa.add("than");
		aa.add("the");
		aa.add("big");
		aa.add("elephant");
		aa.add("with");
		aa.add("the");
		aa.add("big");
		aa.add("blue");
		aa.add("hat");
		aa.add("on");
		aa.add("his");
		aa.add("tusk");
	    String outfile=new String("Bluedog.out");
		Prefix pre=new Prefix(aa,2,true);
		RandomTextGenerator ha=new RandomTextGenerator(pre,10,true);
		PrintWriter pw=new PrintWriter(outfile);
		ha.genNewText(pw);
		
	}
}
