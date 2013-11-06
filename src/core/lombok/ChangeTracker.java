package lombok;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Used with {@link Data} annotations when trackChanges set to true. Descendant
 * of this class can recognize that field was changed with setter after object
 * created.
 */
public abstract class ChangeTracker {
	
	private final Set<String> changeMarks = new HashSet<String>();
	
	protected void markChanged(String name, Object value) {
		Field field = findField(name);
		if (field == null) {
			throw new IllegalArgumentException("Field '" + name + "' not fould");
		}
		if (field.isAnnotationPresent(NotNull.class) && value == null) {
			throw new NullCheckException(name);
		}
		if (field.isAnnotationPresent(Size.class) && value instanceof String) {
			String strValue = (String) value;
			Size size = field.getAnnotation(Size.class);
			if (strValue.length() < size.min() || strValue.length() > size.max()) {
				throw new SizeCheckException(name, strValue.length(), size.min(), size.max());
			}
		}
		changeMarks.add(name);
	}
	
	private Field findField(String name) {
		Class<?> clazz = this.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(name);
				if (field != null) {
					return field;
				}
			} catch (SecurityException e) {
				// ignore
			} catch (NoSuchFieldException e) {
				// ignore
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}
	
	public boolean isChanged(String name) {
		return changeMarks.contains(name);
	}
	
	public void clearChangeMarks() {
		changeMarks.clear();
	}
}
