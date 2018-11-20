package monster;

import java.io.Serializable;
import java.util.ArrayList;

public class EnemyControl implements Serializable {
	/*
	 * Kontollklassen för alla våra aliens
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private ArrayList <Alien> enemyArray = new ArrayList<Alien>();
		private Alien temp;
		
		public EnemyControl () { //Tom konstruktor
		}
		
		
		public void ticktock() { //Flyttar varje alien i array
			for (int i = 0; i < enemyArray.size(); i ++) {
				temp = enemyArray.get(i);
				
				if (temp.getYpos() < 0) {
					removeEnemy(temp);
				}
				
				temp.ticktock(); 
			}
		}
		
		public void addEnemy(Alien monster) { //Lägger till given alien i array
			enemyArray.add(monster);
		}
		
		public void removeEnemy(Alien monster) { //Tar bort given ur array
			enemyArray.remove(monster);
		}
		
		public int getArrayLength() { //returnar storlek på array
			return enemyArray.size();
		}
		
		public Alien getAlienAt(int i) { //Hämtar alien på plats i
			return enemyArray.get(i);
		}

		public boolean emptyList() { //Koll om lsitan är tom
			return enemyArray.isEmpty();
		}


		public void damageEnemy(int i) { //Skadar alien på plats i i array
			Alien alien = enemyArray.get(i);
			alien.takeDamage();
			if (alien.isDead()) {
				enemyArray.get(enemyArray.indexOf(alien)).setVisible(false);
				enemyArray.remove(alien);
			}			
		}


		public void changeVisibility() { //Ändrar synlighet på alla i array
			for(int i = 0; i < getArrayLength(); i++) {
				enemyArray.get(i).setVisible(!enemyArray.get(i).isVisible());
			}			
		}


		public void makeAllVisible() { //Gör alla synliga
			for(int i = 0; i < getArrayLength(); i++) {
				enemyArray.get(i).setVisible(false);
			}
		}


		public void removeAll() { //Tömmer array
			enemyArray.removeAll(enemyArray);
		}
}
