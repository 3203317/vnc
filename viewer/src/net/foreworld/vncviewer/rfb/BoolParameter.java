package net.foreworld.vncviewer.rfb;

public class BoolParameter extends VoidParameter {

	protected boolean value;
	protected boolean defValue;

	public BoolParameter(String name, String desc, Boolean v) {
		super(name, desc);
		value = v;
		defValue = v;
	}

	@Override
	public boolean setParam(boolean value) {
		return true;
	}

}
