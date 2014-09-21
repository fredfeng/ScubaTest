package scuba.test.testcase.recursion;

public class IntBlockPool {
	
	int[] buffer;
	DocWriter docWriter;
	
	public IntBlockPool(DocWriter doc) {
		docWriter = doc;
	}
	
	void nextBuffer() {
		buffer = docWriter.getIntBlock();
	}
}
