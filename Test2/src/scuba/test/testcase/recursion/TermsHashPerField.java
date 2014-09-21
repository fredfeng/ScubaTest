package scuba.test.testcase.recursion;

public class TermsHashPerField {
	IntBlockPool intPool;
	
	int[] intUptos;
	
	TermsHashPerField nextField;
	
	public TermsHashPerField(){
		nextField = new TermsHashPerField();
		DocWriter doc = new DocWriter();
		intPool = new IntBlockPool(doc);
	}
	
	void add2() {
		intPool.nextBuffer();
		intUptos = intPool.buffer;
		int v = intUptos[0];
	}
	
	void add1() {
		intPool.nextBuffer();
		intUptos = intPool.buffer;
		nextField.add2();
	}
}
