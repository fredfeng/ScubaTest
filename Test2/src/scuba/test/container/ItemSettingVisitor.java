package scuba.test.container;

public class ItemSettingVisitor implements Visitor {
	public void visitContainer(Container c, Object arg) {
		c.item = (Item) arg;
	}
}
