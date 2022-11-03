package prac01;

public class Ex10_gun {

	public static void main(String[]args){

		Soldier soldier = new Soldier(new Gun("K2"), "람보");
		
		soldier.reload(3);
		
		soldier.shoot();
		soldier.shoot();
		soldier.shoot();
		
		soldier.info();
		
	}

}
