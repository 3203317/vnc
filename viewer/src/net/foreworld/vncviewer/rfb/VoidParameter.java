package net.foreworld.vncviewer.rfb;

abstract public class VoidParameter {

	protected String name;
	protected String desc;

	public VoidParameter(String name, String desc) {
		name = name;
		desc = desc;
	}

	VoidParameter next;

	final public String getName() {
		return name;
	}

	final public String getDescription() {
		return desc;
	}

	abstract public boolean setParam(boolean value);
}
