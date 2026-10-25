import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class primes {

	static boolean write = false;

	public static void main(String[] args) throws IOException {
		PrintWriter pw;
		long start = System.nanoTime();
		int l = Integer.MAX_VALUE-2;
      		if(args.length > 0){
			l=Integer.parseInt(args[0]);
		}
		int n = 0;
		boolean[] b = new boolean[l];
		
	        File file = new File("primes.txt");
	        FileWriter fw = new FileWriter(file);
       		pw = new PrintWriter(fw);
		
		//counting 2
		pw.print(2+" ");

		n++;        	
		//after 2
		for(int i=3;i<l;i+=2) {
	        	if (!b[i]) {
        	        	n++;
		                //System.out.println(n+" : "+i);
				if(write){
					pw.print(i+" ");
				}
                	for (int i0 = i;; i0+=2) {
				long m = (long)i*i0;
				if(m>=l){break;}
        		        	b[(int)m] = true;
		                }
			}
	        }

		System.out.println((System.nanoTime()-start)/1_000_000_000.0+"s");
		System.out.println("Examined : "+l);
		System.out.println("Found : "+n);
		//System.out.println("Writing ...");
		pw.println();
        	pw.println("Examined : "+l);
        	pw.println("Found : "+n);
		pw.close();
		
		//System.out.println((System.nanoTime()-start)/1_000_000_000.0+"s");
	}
}
