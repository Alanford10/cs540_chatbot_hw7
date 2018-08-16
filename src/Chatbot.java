
import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            //TODO count occurence of w
            for(int i=0;i<corpus.size();i++) {
            	if(corpus.get(i)==w)
            		count++;
            }
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double r = (double)n1/n2;
            
            //TODO generate
            double sum = 0;
            double p = 0.0000000;
            int i=0;
            for(i=0;sum<r;i++) {
            	int count = 0;
            	for(int k=0;k<corpus.size();k++) {
                	if(corpus.get(k)==i)
                		count++;
                }
            	p=count/(double)corpus.size();
            	sum=sum+p;
            }
            System.out.println(i-1);
            System.out.println(String.format("%.7f",sum-p));
            System.out.println(String.format("%.7f",sum));

        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            for(int i=0;i<corpus.size()-1;i++) {
            	if(corpus.get(i)==h && corpus.get(i+1)==w)
            		count++;
            }
            for(int i=0;i<corpus.size()-1;i++) {
            	if(corpus.get(i)==h)
            		words_after_h.add(h+1);
            }

            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            //TODO
            double r = (double)n1/n2;
            int sum=0;
            int c=0;
            int sum2=0;
            for(int k=0;k<corpus.size()-1;k++) {
            	if(h==corpus.get(k)) {c++;}
            	}
            for(int k=0;k<corpus.size();k++) {
            		for(int k1=0;k1<corpus.size()-1;k1++) {
            			if(corpus.get(k1)==h && corpus.get(k1+1)==k) {
            				sum++;
            			}
            		}
            		if((double)sum/c>r) {
            			System.out.println(k);
            			System.out.println(String.format("%.7f",sum2/(double)c));
            			System.out.println(String.format("%.7f",sum/(double)c));
            			break;
            		}
            		sum2=sum;
            
            }
            
            
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO
            for(int i=0;i<corpus.size()-2;i++) {
            	if(corpus.get(i)==h1 && corpus.get(i+1)==h2 && corpus.get(i+2)==w) {
            		count++;
            		}
            }
            for(int i=0;i<corpus.size()-2;i++) {
            	if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            		words_after_h1h2.add(corpus.get(i+2));
            	}
            }
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            double r=n1/(double)n2;
            int counter=0;
            int sum=0;
            int sum2=0;
            for(int i=0;i<corpus.size()-2;i++) {
            		if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            			counter++;
            		}
            }
            if(counter ==0) {
            	System.out.println("undefined");
            }
            else {
            for(int i=0;i<corpus.size()-2;i++) {
            		for(int k=0;k<corpus.size()-2;k++) {
            			if(corpus.get(k)==h1 && corpus.get(k+1)==h2 && corpus.get(k+2)==i) {
            				sum++;
            			}
            		}
            		if((double)sum/counter>r) {
            			System.out.println(i);
            			System.out.println(String.format("%.7f",sum2/(double)counter));
            			System.out.println(String.format("%.7f",sum/(double)counter));
            			break;}
            		sum2=sum;
            }
            }
            
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);
            if(t == 0){
                // TODO Generate first word using r
            		
                double r = rng.nextDouble();
                double sum=0;
        			for(int i=0;i<corpus.size();i++) {
        				int count=0;
        				for(int k=0;k<corpus.size();k++) {
        					if(corpus.get(k)==i) count++;
        				}
        				sum=sum+count/(double)corpus.size();
        				if(sum>r) {
        					h1=i;
        					break;
        				}
        			}
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                int sum1=0;
                int count=0;
                for(int k=0;k<corpus.size()-1;k++) {
                	if(h1==corpus.get(k)) {sum1++;}
                	}
                for(int k=0;k<corpus.size();k++) {
                		for(int k1=0;k1<corpus.size()-1;k1++) {
                			if(corpus.get(k1)==h1 && corpus.get(k1+1)==k) {
                				count++;
                			}
                		}
                		if(count/(double)sum1>r) {
                			h2=k;
                			break;
                		}
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                int sum1=0;
                int count=0;
                for(int k=0;k<corpus.size()-1;k++) {
                	if(h1==corpus.get(k)) {sum1++;}
                	}
                for(int k=0;k<corpus.size();k++) {
                		for(int k1=0;k1<corpus.size()-1;k1++) {
                			if(corpus.get(k1)==h1 && corpus.get(k1+1)==k) {
                				count++;
                			}
                		}
                		if(count/(double)sum1>r) {
                			h2=k;
                			break;
                		}
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                int sum=0;
                int count=0;
                for(int i=0;i<corpus.size()-2;i++) {
            			if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            				sum++;
            			}
                }
                for(int i=0;i<corpus.size()-2;i++) {
            		for(int k=0;k<corpus.size()-2;k++) {
            			if(corpus.get(k)==h1 && corpus.get(k+1)==h2 && corpus.get(k+2)==i) {
            				count++;
            			}
            		}
            		if((double)count/sum>r) {
            			w=i;
            			break;}
            }
                
                
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
}
