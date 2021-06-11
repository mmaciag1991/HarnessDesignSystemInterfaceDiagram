package Components.org.reactfx.collection;

import Components.org.reactfx.Guard;
import Components.org.reactfx.Suspendable;

@SuppressWarnings("deprecation")
public interface SuspendableList<E>
extends LiveList<E>, Suspendable, Components.org.reactfx.inhibeans.collection.ObservableList<E> {
    @Override @Deprecated
    default Guard block() { return suspend(); }
}