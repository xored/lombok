package lombok;

import java.util.HashSet;
import java.util.Set;

/**
 * Used with {@link Data} annotations when trackChanges set to true. Descendant
 * of this class can recognize that field was changed with setter after object
 * created.
 */
public abstract class ChangeTracker {
	
	private final Set<String> changeMarks = new HashSet<String>();
	
	public void markChanged(String name) {
		changeMarks.add(name);
	}
	
	public boolean isChanged(String name) {
		return changeMarks.contains(name);
	}
	
	public void clearChangeMarks() {
		changeMarks.clear();
	}
}
