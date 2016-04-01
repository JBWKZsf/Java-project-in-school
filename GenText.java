

//Name:Zhonghao Wang
//CS 455 PA4
//Fall 2015

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
  Main program GenText
  
  The program we'll create here will write an essay automatically! Or put less grandly, 
  it will generate random text.  This a program which uses a word-level Markov chain to generate
  random text, using data from a sample text documents. That is, generating next word base on the
  current prefix and the probability of the words written after the chosen prefix.
  
  How to call it from commend line:
  
      java GenText [-d] prefixLength numWords sourceFile outFile 
 
  where -d is an optional argument which will turn on the debugging mode if it appears on the 
  commend line, prefixLength is the prefix length you will chose(Conditions:prefixLength>=1, 
  prefixLength should be integer), numWords is the total number of words you will generate in 
  the output file(Conditions:numWords>=0, numWords should be integer),outFile is the name of the 
  output file, if it already exist, the program will override the file, otherwise, this program will
  make a new output file.
*/


public class GenText {
	
	//Statement of the valid arguments in the commend line.
	private static final String VALID_ARGUMENT="A valid arguments format is like:\n"+
	"'java GenText [-d] prefixLength numWords sourceFile outFile'\n"+
	"\n"+
	"[d] is an optional argument which will turn on the debugging mode\n"+
	"Conditions: numWords>=0, prefixLength>=1, both numWords and prefixLength should be integers.";
    
	
	public static void main(String args[]){
		
		
		 /**
	      Representation invariant:
	     *prefixLength>=1
	     *numWords>0
	     *numWords must be integer
	     */
    
    	boolean debugMode=false;
    	boolean satisfiedcommend=false;
    	int prefixLength;
    	int numWords;
    	String sourceFile="";
    	String outFile;
    	ArrayList<String> readinwords=new ArrayList<String>();//store the words of the source file
    	int numWordsinSource;
    	
    	
    	
    	
    	//Only two situations is a satisfied commend line:1.contains -d; 2.doesn't contain -d
    	if(args.length==4||args.length==5){
    		satisfiedcommend=true;
    	}
    	
    	//use try-catch statement to deal with the exceptions
    	if(satisfiedcommend){
    	  try{
    		  //Invalid: number of arguments is 4 and debugMode is on
             if(args.length==4&&args[0].equals("-d")){
    	     throw new IOException();
             }
             //Valid: number of arguments is 4 and debugMode is off
             else if(args.length==4 && !args[0].equals("-d")){
            	prefixLength=Integer.parseInt(args[0]);//automatically throw to NumberFormatException if it crash
            	numWords=Integer.parseInt(args[1]);
            	sourceFile=args[2];
            	outFile=args[3];
             }
             //Valid:number of argument is 4 and debugMode is on
             else if(args.length==5&&args[0].equals("-d")){
            	debugMode=true;
            	prefixLength=Integer.parseInt(args[1]);
            	numWords=Integer.parseInt(args[2]);
            	sourceFile=args[3];
            	outFile=args[4];
             }
             else {
            	throw new IOException();
             }
             
            //add a try-catch statement to distinguish with "can not write to file" exception in FileNotFoundException
            try{
            File infile=new File(sourceFile);
            Scanner in=new Scanner(infile);
            readinwords=readwords(in);
            }
            catch(FileNotFoundException e){
            	throw new FileNotFoundException("Can not find the source file."); 
            }
            
            numWordsinSource=readinwords.size();
            checkAllNumber(prefixLength,numWords,numWordsinSource);//check all the number is valid or not
            
            Prefix prefix=new Prefix(readinwords,prefixLength,debugMode);
            RandomTextGenerator randomGen=new RandomTextGenerator(prefix,numWords,debugMode);
            
          //add a try-catch statement to distinguish with "can not read source file" exception in FileNotFoundException
            try{
            	PrintWriter printout=new PrintWriter(outFile);
            	randomGen.genNewText(printout);//write the text to the outFile
            }
            catch(FileNotFoundException e){
            	throw new FileNotFoundException("Can not write to output file.");
            }
            
    	 }
    	 
    	 catch(FileNotFoundException e){
    		System.out.println("ERROR: "+e.getMessage());
    	 }
    	 catch(InvalidNumberException e){
    		 System.out.println("ERROR: Error with number: "+ e.getMessage());
    	 }
    	 catch(EmptyFileException e){
    		 System.out.println("ERROR: The sourcefile is empty.");
    	 }
    	 catch(NumberFormatException e){
    		 System.out.println("ERROR: Not valid number format.");
    		 System.out.println("The prefixLength and numWords should be integer.");
    	 }
    	 catch(IOException e){
    		 System.out.println("Invalid arguments!");
    		 System.out.println("\n");
    		 System.out.println(VALID_ARGUMENT);
    	 }
    	 
       }
      else{
    		
    		System.out.println("Invalid arguments!");
    		System.out.println("\n");
    		System.out.println(VALID_ARGUMENT);
       }
    }
	
	
	
	/**
	 * 
	 * @param prefixLength: length of the prefix
	 * @param numWords: the number of words will be generated
	 * @param numWordsinSource: total number of words in source file 
	 * @throws InvalidNumberException
	 */
	public static void checkAllNumber(int prefixLength,int numWords,int numWordsinSource) throws InvalidNumberException{
		if(prefixLength<1){
			throw new InvalidNumberException("The prefixLength should >=1");
		}
		if(numWords<0){
			throw new InvalidNumberException("The numWord should >0");
		}
		if(prefixLength>=numWordsinSource){
			throw new InvalidNumberException("prefixlenght shuold be smaller than the number of the words in sourcefile.");
		}
		
	}
	
	
	
	/**
	 * 
	 * @param in: the scanner that scan the source file
	 * @return readinwords: the ArrayList contains the words of the source file
	 * @throws EmptyFileException
	 */
	public static ArrayList<String> readwords(Scanner in) throws EmptyFileException{
		ArrayList<String> readinwords=new ArrayList<String>();
		
		while(in.hasNext()){
			readinwords.add(in.next());	
		}
		if(readinwords.isEmpty()){
			throw new EmptyFileException();
		}
		return readinwords;
	}
	
}
