package Components.org.reactfx.collection;

import Components.org.reactfx.util.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Common supertype for {@link QuasiListChange} and {@link ListChangeAccumulator}.
 *
 * @param <E> type of list elements
 */
public interface ListModificationSequence<E>
extends AbstractListModificationSequence<E, QuasiListModification<? extends E>> {

    /**
     * May be destructive for this object. Therefore, this object should not
     * be used after the call to this method, unless stated otherwise by the
     * implementing class/interface.
     */
    QuasiListChange<E> asListChange();

    /**
     * May be destructive for this object. Therefore, this object should not
     * be used after the call to this method, unless stated otherwise by the
     * implementing class/interface.
     */
    ListChangeAccumulator<E> asListChangeAccumulator();
}

interface AbstractListModificationSequence<E, M extends ListModificationLike<? extends E>>
extends Iterable<M> {

    List<? extends M> getModifications();

    @Override
    default Iterator<M> iterator() {
        return Lists.readOnlyIterator(getModifications());
    }

    default int getModificationCount() {
        return getModifications().size();
    }
}