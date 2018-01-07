package miage.m2sid.dicegame.miage.m2sid.dicegame.metier;

public class Result {

	private Dice dice1;
	private Dice dice2;
	private int score;
	private int meilleurScore;
	private String pseudoMeilleurScore;

	public Result(Dice dice1, Dice dice2, int score) {
		super();
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.score = score;
	}

	public Result() {
	}

	public String getPseudoMeilleurScore() {
		return pseudoMeilleurScore;
	}

	public Dice getDice1() {
		return dice1;
	}

	public void setDice1(Dice dice1) {
		this.dice1 = dice1;
	}

	public Dice getDice2() {
		return dice2;
	}

	public void setDice2(Dice dice2) {
		this.dice2 = dice2;
	}

	public void setPseudoMeilleurScore(String pseudoMeilleurScore) {
		this.pseudoMeilleurScore = pseudoMeilleurScore;
	}

	public int getMeilleurScore() {
		return meilleurScore;
	}

	public void setMeilleurScore(int meilleurScore) {
		this.meilleurScore = meilleurScore;
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Result [dice1=" + dice1.getState() + ", dice2=" + dice2.getState() + ", score=" + score + "]";
	}

}
