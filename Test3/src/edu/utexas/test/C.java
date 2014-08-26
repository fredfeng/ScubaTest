package edu.utexas.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class C extends A {

	private List<A> subScorer;

	private List<A> queue;

	public C(List<A> list) {
		subScorer = list;
		queue = new ArrayList<A>();
	}

	private void init() {
		queue.clear();
		Iterator<A> si = subScorer.iterator();
		while (si.hasNext()) {
			A se = si.next();
			if (se.next()) {
				queue.add(se);
			}
		}
	}

	@Override
	public boolean skipTo(int i) {
		// TODO Auto-generated method stub
		init();
		return doNext();
	}

	@Override
	public boolean next() {
		// TODO Auto-generated method stub
		init();
		return doNext();
	}

	public boolean doNext() {
		Iterator<A> si = subScorer.iterator();
		while (si.hasNext()) {
			A a = si.next();
			if (a.next())
				return a.skipTo(0);
		}

		return false;
	}

}
