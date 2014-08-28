package edu.utexas.test;

import java.util.ArrayList;
import java.util.List;

public class D extends A {

	private A scorer = null;

	// private List<A> queue = new ArrayList();

	private int x = 3;

	private A first, last;

	private void init(A[] b) {
		if (x > 1) {
			A[] a = new A[1];
			a[0] = new D();
			scorer = new B(a);
		}
		if (x < 1) {
			List<A> l = new ArrayList<A>();
			l.add(new D());
			scorer = new C(l);

			first = last;
		}

		if (scorer.next())
			scorer = new D();
	}

	@Override
	public boolean skipTo(int i) {
		// TODO Auto-generated method stub
		A[] a = new A[2];
		a[0] = new D();
		a[1] = new B();
		init(a);
		last = a[0];
		return scorer.skipTo(i);
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		last.skipTo(0);
		return last.next();
	}

}
