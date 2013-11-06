package lombok;

public class NullCheckException extends RuntimeException {
	
	private final String fieldName;

	public NullCheckException(final String fieldName) {
		super("Field '" + fieldName + "' must be not null");
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
}
