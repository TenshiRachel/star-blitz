package com.mygdx.game.GameLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordFactory {
	private List<String> WordList;
	private List<String> SpaceWordList = new ArrayList<>(Arrays.asList("Asteroid", "Moon", "Star",
			"Earth", "Sun", "Orion belt", "Big Bang", "Mars"));
	private List<String> NonSpaceWordList = new ArrayList<>(Arrays.asList("Tree", "Apple", "Water",
			"Dog", "Burger", "Air", "Ball"));
	Random random = new Random();
	
	public WordFactory() {
		WordList = new ArrayList<>(SpaceWordList);
		WordList.addAll(NonSpaceWordList);
	}
	
	public List<String> getWordList() {
		return WordList;
	}
	
	public List<String> getSpaceWords(){
		return SpaceWordList;
	}
	
	public List<String> getNonSpaceWords(){
		return NonSpaceWordList;
	}
	
	public void setSpaceWords(List<String> SpaceWords) {
		SpaceWordList = SpaceWords;
	}
	
	public void setNonSpaceWords(List<String> NonSpaceWords) {
		NonSpaceWordList = NonSpaceWords;
	}
	
	public String getRandomWord(String enemyType) {
		int rand;
		String word = "";
//		System.out.println(SpaceWordList);
//		System.out.println(SpaceWordList.size());
//		System.out.println(NonSpaceWordList);
//		System.out.println(NonSpaceWordList.size());
		
		if (enemyType == "space") {
			rand = random.nextInt(SpaceWordList.size());
			word = SpaceWordList.get(rand);
			SpaceWordList.remove(rand);
		}
		
		else if (enemyType == "nonSpace") {
			rand = random.nextInt(NonSpaceWordList.size());
			word = NonSpaceWordList.get(rand);
			NonSpaceWordList.remove(rand);
		}

		return word;
	}
	
}
