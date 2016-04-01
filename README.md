#README  

##Overview
The program I wrote here will write an essay automatically! Or put less grandly, it will generate random text. To make the result sound more like real English, than, say just choosing random words from the dictionary, we're going to use some sample text to give us information about likely words and word sequences.

##Steps
1. Download the files except for the tester.java (you can download and modify it if you like).

2. Use the command line to change the directory to the file you placed.
3. Compile the program by typing:

        javac GenText.java
 
4.  Run of your program:

        java GenText [-d] prefixLength numWords sourceFile outFile 
 	generates numWords words of prefixLength-order text using sourceFile as the source document. The generated words are written to outFile (not the console).
	
	The square brackets ([ ]) above denote optional arguments to your program (the square brackets themselves are not part of the command line). 
You have one optional argument here, "-d", the presence of which means the program will be run in debugging mode.