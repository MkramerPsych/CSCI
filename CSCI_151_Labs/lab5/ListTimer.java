import java.util.*;
import java.io.*;

class ListTimer {
    // default values for number of iterations and interval size
    static final int NITER=5, INTERVAL=5000;

    public static void doWork(Collection<String> lexicon, List<String> words, int numIter, int interval, String lfile, String wfile)
       throws FileNotFoundException {
 
       System.out.printf("Lexicon: %s  Document: %s  Class: %s\n",lfile,wfile,lexicon.getClass().getCanonicalName());
       System.out.println("==========================================");
       System.out.println(" words      elapsed    pct of");
       System.out.println("searched   time (ms) found words");
       for(int k=1; k<=numIter; k++){
          Scanner lexScanner = new Scanner(new File(lfile));
          lexicon.clear();
          int count = 0;
          while(lexScanner.hasNext()){
             lexicon.add(lexScanner.next());
          }
          
          long startTime = System.currentTimeMillis();
          int wordcount = 0, foundcount = 0;
          Iterator<String> i = words.iterator();
          while(wordcount<interval*k && i.hasNext()){
             String word = i.next();
             wordcount++;
             if(lexicon.contains(word)){
                foundcount++;
             }
          }
          long endTime = System.currentTimeMillis();
          long elapsed = endTime-startTime;
          double pct = Math.round((foundcount/(double)wordcount)*1000)/10.0;
          System.out.printf("%8d    %8d    %6.1f%%\n",wordcount,elapsed,pct);
       }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
       if(args.length < 2){
          System.out.println("Usage: java CheckApp <lex-file> <test-file>");
          System.exit(1);
       }
       List<String> wordList = new ArrayList<String>();
       
       Scanner docScanner = new Scanner(new File(args[1]));
       while(docScanner.hasNext()){
          wordList.add(docScanner.next());
       }
       
       int niter=NITER, interval=INTERVAL;
       
       if(args.length > 2){
          try {
             interval = Integer.parseInt(args[2]);
          }
          catch(NumberFormatException e){
             System.out.println("Usage: java CheckApp <lex-file> <test-file> <step-size>");
             System.exit(1);
          }
       }
       
       if(args.length > 3){
          try {
             niter = Integer.parseInt(args[3]);
          }
          catch(NumberFormatException e){
             System.out.println("Usage: java CheckApp <lex-file> <test-file> <step-size> <number-of-iterations>");
             System.exit(1);
          }
       }
       
       doWork(new DoublyLinkedList<String>(), wordList, niter, interval, args[0], args[1]);
       doWork(new MRUList<String>(), wordList, niter, interval, args[0], args[1]);
    }
}
      