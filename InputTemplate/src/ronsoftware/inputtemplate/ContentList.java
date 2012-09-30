package ronsoftware.inputtemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list of template content elements.
 */
public class ContentList extends Content 
	implements List<Content> {
	
	private List<Content> contents = new ArrayList<Content>();
	
	@Override
	protected Content doClone() {
		ContentList newList = new ContentList();
		for (Content item : this) {
			newList.add(item.clone());
		}
		return newList;
	}
	
	protected void notifyWhenAdd(Content item) {
		
		if (item == null) {
			return;
		}
		
		if (item.isList()) {
			ContentList itemList = item.asList();
			for (Content c : itemList) {
				if (c != null) {
					notifyWhenAdd(c);
				}
			}
		}
		
		notifyUpdateId(null, item);
	}
	
	protected void notifyWhenRemove(Object o) {
		
		if (o == null || !(o instanceof Content)) {
			return;
		}
		
		Content item = (Content) o;
		
		if (item.isList()) {
			ContentList itemList = item.asList();
			for (Content c : itemList) {
				if (c != null) {
					notifyWhenRemove(o);
				}
			}
		}
		
		notifyUpdateId(item.getId(), null);
	}
	
	@Override
	public ContentList clone() {
		return (ContentList) super.clone();
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		if (contents == null)
			return result;
		
		for (Content content : contents) {
			result = result ^ content.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ContentList other = (ContentList) obj;
		if (other.size() != this.size())
			return false;
		
		Iterator<Content> i = this.iterator();
		Iterator<Content> j = other.iterator();
		
		while (i.hasNext()) {
			Content x = i.next();
			Content y = j.next();
			if (!x.equals(y)) {
				return false;
			}
		}
		
		return true;
	}
	

	@Override
	public Object[] toArray() {
		return contents.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return contents.toArray(a);
	}
	
	@Override
	public boolean add(Content e) {
		boolean isDone = contents.add(e);
		if (isDone) {
			appendObserversTo(e);
			notifyWhenAdd(e);
		}
		return isDone;
	}

	@Override
	public boolean remove(Object o) {
		boolean isDone = contents.remove(o);
		if (isDone) {
			notifyWhenRemove(o);
			removeObserversFrom(o);
		}
		return isDone;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		return contents.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Content> c) {
		
		boolean isDone = true;
		for (Content item : c) {
			isDone = isDone && add(item);
		}
		return isDone;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		boolean isDone = true;
		for (Object o : c) {
			isDone = isDone && remove(o);
		}
		return isDone;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		for (Content item : this) {
			notifyWhenRemove(item);
			removeObserversFrom(item);
		}
		contents.clear();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Content> c) {
		boolean isDone = contents.addAll(index, c);
		if (isDone) {
			for (Content item : c) {
				appendObserversTo(item);
				notifyWhenAdd(item);
			}
		}
		return isDone;
	}

	@Override
	public Content get(int index) {
		return contents.get(index);
	}

	@Override
	public Content set(int index, Content element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, Content element) {
		contents.add(index, element);
		appendObserversTo(element);
		notifyWhenAdd(element);
	}

	@Override
	public Content remove(int index) {
		Content item = contents.remove(index);
		notifyWhenRemove(item);
		removeObserversFrom(item);
		return item;
	}

	@Override
	public int indexOf(Object o) {
		return contents.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return contents.lastIndexOf(o);
	}

	@Override
	public ListIterator<Content> listIterator() {
		return
			new ContentReadOnlyListIterator(
				contents.listIterator());
	}

	@Override
	public ListIterator<Content> listIterator(int index) {
		return
			new ContentReadOnlyListIterator(
				contents.listIterator(index));
	}

	@Override
	public ContentList subList(int fromIndex, int toIndex) {
		ContentList newList = this.clone();
		newList.contents = newList.contents.subList(fromIndex, toIndex);
		return newList;
	}
	
	@Override
	public int size() {
		return contents.size();
	}

	@Override
	public boolean isEmpty() {
		return contents.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return contents.contains(o);
	}

	@Override
	public Iterator<Content> iterator() {
		return
			new ContentReadOnlyIterator(
				contents.iterator());
	}
	
	private static class ContentReadOnlyIterator implements Iterator<Content> {
		
		private Iterator<Content> innerIter;
		
		public ContentReadOnlyIterator(Iterator<Content> iter) {
			innerIter = iter;
		}
		
		@Override
		public boolean hasNext() {
			return innerIter.hasNext();
		}

		@Override
		public Content next() {
			return innerIter.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}
	
	private static class ContentReadOnlyListIterator implements ListIterator<Content> {
		
		private ListIterator<Content> innerIter;
		
		public ContentReadOnlyListIterator(ListIterator<Content> iter) {
			innerIter = iter;
		}
		
		@Override
		public boolean hasNext() {
			return innerIter.hasNext();
		}

		@Override
		public Content next() {
			return innerIter.next();
		}

		@Override
		public boolean hasPrevious() {
			return innerIter.hasPrevious();
		}

		@Override
		public Content previous() {
			return innerIter.previous();
		}

		@Override
		public int nextIndex() {
			return innerIter.nextIndex();
		}

		@Override
		public int previousIndex() {
			return innerIter.previousIndex();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Content e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(Content e) {
			throw new UnsupportedOperationException();
		}
		
	}
}
