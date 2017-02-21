package value;

public class IntegerValue extends Value {
	
	private int value;
	
	public IntegerValue(int value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
}
