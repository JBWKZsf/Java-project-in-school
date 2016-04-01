

//Name:Zhonghao Wang
//CS 455 PA4
//Fall 2015


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/*
   class RandomTextGenerator
   Randomly generate the successive word, and responsible for writing the words into file. 
 */


public class RandomTextGenerator {

	private Random ranNumGenerator;
	private Prefix prefix;
	private int numWords;
	private boolean debugMode;
	
	private static final int NUM_CHAR_OF_PER_LINE = 80;
	
	
	public RandomTextGenerator(Prefix prefix,int numWords,boolean debugMode){
		this.prefix=prefix;
		this.numWords=numWords;
		this.debugMode=debugMode;
		if(debugMode){
			ranNumGenerator=new Random(1);//if the debugMode is on, set the seed to 1
		}
		else{
			ranNumGenerator=new Random();
		}
    }
	
	
	/**
	 * Write the text into the file.
	 * @param printout: is a PrintWriter type parameter to write the words into output file.
	 */
	public void genNewText(PrintWriter printout){
		prefix.initialPre();
		String nxtWord;
		int numCharALine=0;
		int tempnumWords=numWords;
		ArrayList<String> successors=new ArrayList<String>();
        
		for(int i=1;i<=tempnumWords;i++){
     
        	successors=prefix.findSuccessor();
        	if (successors.size()==0){
        		//increase the loop to ensure that the number of the output words is numWords
        		tempnumWords=tempnumWords+1;
        		continue;
             }
        	
        	nxtWord=this.genNextword(successors);
        	
        	//write the words to the output file
        	if(i==1){
        	    printout.print(nxtWord);
        	    numCharALine=numCharALine+nxtWord.length();
        	 }
        	else{
        		//ensure that each line contain most 80 characters
        		numCharALine=numCharALine+1+nxtWord.length();
        		if(numCharALine>NUM_CHAR_OF_PER_LINE){
        			printout.println();
        			printout.print(nxtWord);
        			numCharALine=nxtWord.length();
        		}
        		else{
        			printout.print(" "+nxtWord);
        		}
             }
        	
        	 prefix.refreshPrefix(nxtWord);
        	
        }
		
		printout.close();
		
	}
	
	
	
	/**
	 * Randomly choose a word from the successors and return it as a string.
	 * @param successor: the ArralyList that contains the successors
	 * @return word chosen from the sucessors and should be print after the prefix.
	 */
	public String genNextword(ArrayList<String> successor){
		
		int ranNum=ranNumGenerator.nextInt(successor.size());
		if(debugMode){
			System.out.println("DEBUG: word generated: "+successor.get(ranNum));
		}
		return successor.get(ranNum);
		
	}
	
	
}
