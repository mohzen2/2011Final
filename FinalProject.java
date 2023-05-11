import java.util.Scanner;

public class FinalProject {
	static Scanner input = new Scanner(System.in);
	static int currentHP = 200;
	public static void main(String[] args) {
		//Scanner input = new Scanner(System.in);
		//base stats
		final int SPACE = 3;
		int exp = 0;
		final int BASEHP = 200;
		final int BASEATK = 30;
		final int BASEDEF = 50;
		boolean backpack = false;
		int currentHP = BASEHP;


		System.out.print("ENTER YOUR NAME: ");
		String temp = input.nextLine();
		String playerName = temp.toUpperCase();

		String introPrompt = "You have been transported to another world.\n" +"You are in the middle of nowhere and have no idea what just happened or where you are.\n" + "You have stumbled across a scepter which allows you to wield one of the four mystical powers:\n" + "Fire, Water, Air, or Ground.\n" + "Once acquiring the scepter, you have been cursed to defeat [KEENAN] in a one on one fight.\n" + "Defeating [KEENAN] will cause his tyranny across of Eagleterra to end, bringing several years of life and prosperity.\n" + "You must defeat his henchmen to get to him.";

		System.out.println();
		System.out.println(introPrompt);
		System.out.println();

		System.out.println("PICK YOUR ELEMENT:  FIRE, WATER, AIR, OR EARTH");
		String temp2 = input.nextLine();
		String playerElement = temp2.toUpperCase();
		System.out.println();
		System.out.println("YOU'VE SELECTED " + playerElement);
		System.out.println();

		backpackPrompt(playerName);
		//finalBoss(playerName, BOSSBASEHP);
		enemyPrompt("KEENAN", 100, "FIRE");
		attackPrompt(100, "FIRE", "KEENAN",90, 50, 10, 20, playerElement, 25, 35, 50, 100, 25, 40, 50, 69);
	}
	//public static void floorOne(String playerName) {
	//}
	public static void backpackPrompt(String playerName) {
		System.out.println("You just picked up a backpack. It only has 3 spaces.");
		System.out.println(playerName.concat(" - \"I wonder what this can hold.\""));
	}
	//boss level (im working on it rn)
	public static void finalBoss(String playerName, int BOSSBASEHP) {
		int bossHP = BOSSBASEHP;
		System.out.println("");
		while (bossHP >= 0) {
			System.out.println(bossHP);
			bossHP -= 100;
		}
		System.out.println("Objective Completed");
	}

	public static void enemyPrompt(String enemyName, double enemyHP, String enemyElement){
		System.out.println();
		System.out.println("You've encountered a " + enemyElement + " " + enemyName + " !!!");
		System.out.println("It has " + enemyHP + " HP");
		System.out.println();
	}

	public static void attackPrompt(double enemyHP, String enemyElement, String enemyName, int enemyProbability1, int enemyProbability2, int enemyDamage1, int enemyDamage2, String playerElement, int hitPercentage1, int hitPercentage2, int hitPercentage3, int hitPercentage4, int damageValue1, int damageValue2, int damageValue3, int damageValue4){
		int counter = 3;
		while(enemyHP >= 0 && currentHP >= 0){
			boolean hitMarker = false;
			double damageGiven = 0;
			System.out.println("How do you choose to continue?");
			System.out.println("1) Basic Attack ("+ hitPercentage1 +"%, " + damageValue1 + " Damage)\n2) Elemental Attack (" + hitPercentage2 + "%, " + damageValue2 +" Damage)\n3) Super Attack (" + hitPercentage3 + "%, " + damageValue3 + " Damage) \n4) Critical Attack ("+ hitPercentage4 +"% "+ damageValue4 +" Damage)");
			if(counter%3 == 0){
				System.out.println("Critical Available!");
			}
			else{
				System.out.println("Critical Charging...");
			}
			int attack = input.nextInt();
			if(attack == 1){
				hitMarker = hitProbability(hitPercentage1);
				damageGiven = damageValue1;
			}
			if(attack == 2){
				hitMarker = hitProbability(hitPercentage2);
				damageGiven = damageValue2;
			}
			if(attack == 3){
				hitMarker = hitProbability(hitPercentage3);
				damageGiven = damageValue3;
			}
			if(attack == 4){
				if(counter%3 == 0){
					hitMarker = hitProbability(hitPercentage4);
					damageGiven = damageValue4;
					counter++;
				}
				else{
					System.out.println("Critical Attack is only available every 3rd turn.");
					System.out.println();
					continue;
				}
			}
			if(hitMarker){
				if(playerElement.equals("FIRE") && enemyElement.equals("WATER") || playerElement.equals("WATER") && enemyElement.equals("FIRE") || playerElement.equals("AIR") && enemyElement.equals("EARTH") || playerElement.equals("EARTH") && enemyElement.equals("AIR")){
					enemyHP -= damageGiven * 1.2;
					System.out.println();
					System.out.println("YOUR ATTACK HIT!");
					System.out.println((damageGiven * 1.2) + " DAMAGE WAS DEALT!");
				}
				else{
					enemyHP -= damageGiven;
					System.out.println();
					System.out.println("YOUR ATTACK HIT!");
					System.out.println((damageGiven) + " DAMAGE WAS DEALT!");
				}
			}
			if(!hitMarker){
				System.out.println();
				System.out.println("YOUR ATTACK MISSED! \nNO DAMAGE WAS DEALT!");
			}
			if(enemyHP >= 0){
				System.out.print(enemyName + " HAS ");
				System.out.printf("%.2f",enemyHP);
				System.out.println(" HP REMAINING!");
			}
			if(enemyHP < 0){
				System.out.println(enemyName + " HAS 0 HP REMAINING!");
				System.out.println();
			}
			if(enemyHP > 0){
				hitMarker = false;
				damageGiven = 0;
				boolean enemyHitType = enemyStrikeType();
				System.out.println();
				System.out.println(enemyName + " -Rawr xD :3");

				if(enemyHitType){
					System.out.println(enemyName + " USED THEIR BASIC ATTACK!");
					hitMarker = hitProbability(enemyProbability1);
					damageGiven = enemyDamage1;
				}
				else{
					System.out.println(enemyName + " USED THEIR CRITICAL ATTACK!");
					hitMarker = hitProbability(enemyProbability2);
					damageGiven = enemyDamage2;
				}

				if(hitMarker){
					if(playerElement.equals("FIRE") && enemyElement.equals("WATER") || playerElement.equals("WATER") && enemyElement.equals("FIRE") || playerElement.equals("AIR") && enemyElement.equals("EARTH") || playerElement.equals("EARTH") && enemyElement.equals("AIR")){
						currentHP -= damageGiven * 1.2;
						System.out.println();
						System.out.println(enemyName + " ATTACK HIT!");
						System.out.println((damageGiven * 1.2) + " DAMAGE WAS DEALT! ");
						System.out.println("YOU HAVE " + currentHP + " HP REMAINING");
					}
					else{
						currentHP -= damageGiven;
						System.out.println();
						System.out.println(enemyName + "ATTACK HIT!");
						System.out.println((damageGiven) + " DAMAGE WAS DEALT! ");
						System.out.println("YOU HAVE " + currentHP + " HP REMAINING");
						System.out.println();
					}
				}
				else{
					System.out.println();
					System.out.println(enemyName + " ATTACK MISSED!\n NO DAMAGE WAS DEALT!");
					System.out.println("YOU HAVE " + currentHP + "HP REMAINING");
					System.out.println();
				}


			}
		}
	}

	public static boolean hitProbability(double hitPercentage){
        int randomNumber = (int)(Math.random() * (101 - 1)) * 1;
        if(randomNumber <= hitPercentage){
        	return true;
        }
        else{
            return false;
        }
        //True means the attack hit the enemy, false means you missed.
    }

    public static boolean enemyStrikeType(){
    	return Math.random() < 0.5;
    }



}
