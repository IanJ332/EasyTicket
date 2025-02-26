package application.controller.sort;

import java.util.Comparator;

import content.controller.ProjectContentController;

/*
 * A comparator used to help sort ProjectVBox alphabetically ascending
 */
public class ComparatorAscending<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		String string1 = ((ProjectContentController)o1).getName();
		String string2 = ((ProjectContentController)o2).getName();
        return string1.compareToIgnoreCase(string2);
	}
	
	
}
