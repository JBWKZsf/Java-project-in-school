
//Name:Zhonghao Wang
//CS 455 PA4
//Fall 2015


import java.util.ArrayList;
import java.util.Random;

/*
   class Prefix
   Contains several funcitons to deal with the prefix.Including randomly choose the prefix, 
   refresh the prefix, find the successors of the prefix.
 */

public class Prefix {

	private ArrayList<String> origwords;//store the original words from sourcefile
	private ArrayList<String> prefixwords;
	private int prefixlength;
	private Random ranNumGenerator;
	private boolean debugMode;

	

	public Prefix(ArrayList<String> readinwords,int prefixlength,boolean debugMode){
		origwords=readinwords;
		this.prefixlength=prefixlength;
		this.debugMode=debugMode;
		prefixwords=new ArrayList<String>();
		if(debugMode){
			ranNumGenerator=new Random(1);//if the debugMode is on, set the seed to 1
		}
		else{
			ranNumGenerator=new Random();
		}
		
	}
	

	/**
	 Initial the prefix:choose continuous words randomly as a prefix which length is prefixLength.
	 */
    public void initialPre(){
		prefixwords.clear();
		 
		//randomly choose the start location of the prefix in the ArrayList
		//start location is in the range [0,sizeof(arraylist)-prefixlength]
		int startLoc=ranNumGenerator.nextInt(origwords.size()-prefixlength);
		for(int i=startLoc;i<(startLoc+prefixlength);i++){
			prefixwords.add(origwords.get(i));	
		}

	    if(debugMode){
	    	System.out.println("DEBUG: chose a new initial prefix: "+prefixwords.toString());
	        System.out.println("DEBUG: prefix: "+prefixwords.toString());
	    }
     }

    
	/**
	 * Refresh the prefix by removing the first word and add successor to the end.
	 * @param nxtWord: the word randomly chose from the successors
	 */
	public void refreshPrefix(String nxtWord){
		prefixwords.remove(0);
		prefixwords.add(nxtWord);
		
		if(debugMode){
			System.out.println("DEBUG: prefix: "+prefixwords.toString());
		}
	}
	
	
	/**
	 * Find the all the possible successors.
	 * @return successors: the ArrayList contains all the possible successors.
	 */
	public ArrayList<String> findSuccessor(){
		ArrayList<String> successors=new ArrayList<String>();
		boolean isEndOfFile=false;
		for(int i=0;i<=(origwords.size()-prefixlength);i++){
			int cout=0;
			for(int j=0;j<prefixlength;j++){
				if(origwords.get(i+j)==prefixwords.get(j)){
					cout=cout+1;//count the number of words that match the prefix
				}
			}
		    if(cout==prefixlength){
		    	//if the prefix is the last several words in the source file, the prefix will be initialized
				if((i+prefixlength)==origwords.size()){
					isEndOfFile=true;
					this.initialPre();
				}
				else{
				successors.add(origwords.get(i+prefixlength));
				}
			}
		}
		
	   if(debugMode){
			if(isEndOfFile){
				System.out.println("DEBUG: successors: <END OF FILE>");
		    }
			else{
				System.out.println("DEBUG: successors: "+successors.toString());
			}
	   }
		return successors;
	 }
	
	
	
}
