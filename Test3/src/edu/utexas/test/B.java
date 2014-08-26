package src.edu.utexas.test;

public class B extends A {

	private A[] scorers;
	
	
	private boolean more;
	
	public B() {
	}
	
	public B(A[] a) {
		scorers = a;
	}
	
	@Override
	public boolean skipTo(int i) {
		// TODO Auto-generated method stub
		init(i);
		return next();
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		boolean first = true;
		if(first)
			return init(0);
		more = scorers[2].next();
		return more;
	}
	
	public boolean init(int tgt) {
		for(int i=0; i< scorers.length; i++) {
			boolean more = tgt > 0? scorers[i].next() : scorers[i].skipTo(i);
			if(more)
				return false;
		}
		return true;
	}

}
