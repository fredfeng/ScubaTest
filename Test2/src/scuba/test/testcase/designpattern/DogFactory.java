package scuba.test.testcase.designpattern;

public class DogFactory {
	public static Dog getDog(String criteria) {
		if (criteria.equals("small"))
			return new Poodle();
		else if (criteria.equals("big"))
			return new Rottweiler();

		return null;
	}
}