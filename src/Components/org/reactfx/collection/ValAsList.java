package Components.org.reactfx.collection;

import Components.org.reactfx.Subscription;
import Components.org.reactfx.util.Lists;
import Components.org.reactfx.value.Val;
import javafx.beans.value.ObservableValue;

class ValAsList<T> extends LiveListBase<T> implements UnmodifiableByDefaultLiveList<T> {
    private final ObservableValue<T> underlying;

    ValAsList(ObservableValue<T> underlying) {
        this.underlying = underlying;
    }

    @Override
    public int size() {
        return underlying.getValue() == null ? 0 : 1;
    }

    @Override
    public T get(int index) {
        Lists.checkIndex(index, size());
        return underlying.getValue();
    }

    @Override
    protected Subscription observeInputs() {
        return Val.observeChanges(underlying, (obs, oldVal, newVal) -> {
            if(oldVal == null) {
                assert newVal != null;
                fireElemInsertion(0);
            } else if(newVal == null) {
                assert oldVal != null; // superfluous, just for symmetry
                fireElemRemoval(0, oldVal);
            } else {
                fireElemReplacement(0, oldVal);
            }
        });
    }

}
