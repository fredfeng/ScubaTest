package scuba.test.virtualcall;
//http://indefinitestudies.org/2011/10/03/survey-of-call-graph-and-points-to-algorithms-in-java/
public class Bldg {
	public MyList events, floors;

	public Bldg() {
		this.events = new MyList();
		this.floors = new MyList();
		for (int i = 0; i < events.elems.length; i++)
			events.elems[i] = new Object();
		for (int i = 0; i < floors.elems.length; i++)
			floors.elems[i] = new Object();
	}
}
