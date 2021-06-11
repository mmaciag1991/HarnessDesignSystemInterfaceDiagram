package Components.org.reactfx.collection;

import Components.org.reactfx.ObservableBase;
import Components.org.reactfx.ProperObservable;
import Components.org.reactfx.util.Lists;
import Components.org.reactfx.util.NotificationAccumulator;

import java.util.Collections;
import java.util.List;

/**
 * Trait to be mixed into {@link ObservableBase} to obtain default
 * implementation of some {@link LiveList} methods and get additional
 * helper methods for implementations of <em>proper</em> {@linkplain LiveList}.
 */
public interface ProperLiveList<E>
extends LiveList<E>, ProperObservable<LiveList.Observer<? super E, ?>, QuasiListChange<? extends E>> {

    @Override
    default NotificationAccumulator<Observer<? super E, ?>, QuasiListChange<? extends E>, ?> defaultNotificationAccumulator() {
        return NotificationAccumulator.listNotifications();
    }

    default void fireModification(QuasiListModification<? extends E> mod) {
        notifyObservers(mod.asListChange());
    }

    static <E> QuasiListModification<E> elemReplacement(int index, E replaced) {
        return new QuasiListModificationImpl<E>(
                index, Collections.singletonList(replaced), 1);
    }

    default void fireElemReplacement(int index, E replaced) {
        fireModification(elemReplacement(index, replaced));
    }

    default QuasiListModification<E> contentReplacement(List<E> removed) {
        return new QuasiListModificationImpl<E>(0, removed, size());
    }

    default void fireContentReplacement(List<E> removed) {
        fireModification(contentReplacement(removed));
    }

    static <E> QuasiListModification<E> elemInsertion(int index) {
        return rangeInsertion(index, 1);
    }

    default void fireElemInsertion(int index) {
        fireModification(elemInsertion(index));
    }

    static <E> QuasiListModification<E> rangeInsertion(int index, int size) {
        return new QuasiListModificationImpl<E>(
                index, Collections.emptyList(), size);
    }

    default void fireRangeInsertion(int index, int size) {
        fireModification(rangeInsertion(index, size));
    }

    static <E> QuasiListModification<E> elemRemoval(int index, E removed) {
        return new QuasiListModificationImpl<E>(
                index, Collections.singletonList(removed), 0);
    }

    default void fireElemRemoval(int index, E removed) {
        fireModification(elemRemoval(index, removed));
    }

    static <E> QuasiListModification<E> rangeRemoval(int index, List<E> removed) {
        return new QuasiListModificationImpl<E>(index, removed, 0);
    }

    default void fireRemoveRange(int index, List<E> removed) {
        fireModification(rangeRemoval(index, removed));
    }

    @Override
    default int defaultHashCode() {
        return Lists.hashCode(this);
    }

    @Override
    default boolean defaultEquals(Object o) {
        return Lists.equals(this, o);
    }

    @Override
    default String defaultToString() {
        return Lists.toString(this);
    }
}
