package Components.org.reactfx.collection;

import Components.org.reactfx.ObservableBase;

public abstract class LiveListBase<E>
extends ObservableBase<LiveList.Observer<? super E, ?>, QuasiListChange<? extends E>>
implements ProperLiveList<E>, AccessorListMethods<E> {}
