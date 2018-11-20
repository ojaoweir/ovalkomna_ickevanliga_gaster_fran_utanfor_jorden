package levels;

	import java.io.Serializable;
import java.util.Random;

	public class RandomInteger implements Serializable {		//vi genererar siffror
		/*
		 * Vår klass för att generera random siffror, används huvudsakligen i level 3 och för spawntime 
		 */
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Random randomGenerator = new Random();
		private int max;
		
		public RandomInteger(int max) {
			this.max = max;
		}
	  
	  public int getRandomInt() {						//hämtar genererad siffra
		  return randomGenerator.nextInt(max + 1);
	  }
	  
}
