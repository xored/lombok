package lombok;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Used with {@link Data} annotations when trackChanges set to true. Descendant
 * of this class can recognize that field was changed with setter after object
 * created.
 */
public abstract class ChangeTracker {
	
	private final Set<String> changeMarks = new HashSet<String>();
	
	protected void markChanged(String name) {
		Field field;
		try {
			field = this.getClass().getDeclaredField(name);
			if (field.isAnnotationPresent(ReadOnly.class)) {
				throw new ReadOnlyException(name);
			}
			changeMarks.add(name);
		} catch (SecurityException e) {
			// ignore
		} catch (NoSuchFieldException e) {
			// ignore
		}
	}
	
	public boolean isChanged(String name) {
		return changeMarks.contains(name);
	}
	
	public void clearChangeMarks() {
		changeMarks.clear();
	}
}
