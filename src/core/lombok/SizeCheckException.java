package lombok;

public class SizeCheckException extends RuntimeException {	
	
	private final String fieldName;
	private final int size;
	private final int min;
	private final int max;

	public SizeCheckException(final String fieldName, final int size, final int min, final int max) {
		super("Field '" + fieldName + "' not conform to size constraint, size: " + size + ", bounds: (" + min + ", "+ max +")");
		this.fieldName = fieldName;
		this.size = size;
		this.min = min;
		this.max = max;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public int getSize() {
		return size;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
