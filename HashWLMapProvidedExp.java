package F28DA_CW1_2;

public class HashWLMapProvidedExp {

	public static void main(String[] args) {
		runDifferentLoadFactors();
	}

	/** Runs the hash table at different load factors and print out the average probe numbers versus the running time.
	 * The average probe number should go up as the max load factor goes up.
	 */
	private static void runDifferentLoadFactors() {
		System.out.println("Runs the hash table at different load factors and print out the average probe numbers versus the running time.");
		System.out.println("The average probe number should go up as the max load factor goes up.");

		StringHashCode sHC = new StringHashCode();
		float maxLF = (float) 0.5;
		HashWLMap h = new HashWLMap(sHC,maxLF);
		long startTime,finishTime;
		double time;
		String word;
		int line;
		String file;
		WLoc loc;

		while (maxLF < 0.99 ){
			startTime = System.currentTimeMillis();
			h = new HashWLMap(sHC,maxLF);			
			try{
				for (int k = 0; k < 10000; ++k) {
					word = "w" + k;
					line = k + 1;
					file = "f" + k;
					loc = new WLoc(file, line, word);
					h.addLoc(word, loc);
				}
				for ( int k = 0; k < 10000; ++k ){
					word = "w" + k;
					line = k + 1;
					file = "f" + k;
					loc = new WLoc(file, line, word);
					h.removeLoc(word, loc);
				}
				finishTime = System.currentTimeMillis();
				time = finishTime - startTime;
				System.out.println(String.format("For load factor %9f, average num. of  probes is %9f time in milseconds is %9f",maxLF,h.averNumProbes(),time));
				maxLF = maxLF+ (float) 0.05;
			}
			catch (WLException e) {
				System.out.print("Failure");
			}
		}
	}

}
