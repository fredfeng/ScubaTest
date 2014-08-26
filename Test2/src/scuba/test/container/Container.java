package scuba.test.container;

public class Container {
	public Item item;

	public void apply(Visitor v, Object arg) {
		v.visitContainer(this, arg);
	}
}
