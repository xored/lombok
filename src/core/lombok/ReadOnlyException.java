package lombok;

public class ReadOnlyException extends RuntimeException {
	
	private final String fieldName;
	
	public ReadOnlyException(final String fieldName) {
		super("Thying to set read only field: " + fieldName);
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
}
