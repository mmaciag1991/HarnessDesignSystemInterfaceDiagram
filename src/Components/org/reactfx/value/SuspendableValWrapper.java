package Components.org.reactfx.value;

import Components.org.reactfx.SuspendableBase;
import Components.org.reactfx.util.AccumulatorSize;
import Components.org.reactfx.util.NotificationAccumulator;

import java.util.function.Consumer;

class SuspendableValWrapper<T>
extends SuspendableBase<Consumer<? super T>, T, T>
implements SuspendableVal<T>, ProperVal<T> {
    private final Val<T> delegate;

    protected SuspendableValWrapper(Val<T> obs) {
        super(
                obs.invalidations(),
                NotificationAccumulator.retainOldestValNotifications());
        this.delegate = obs;
    }

    @Override
    public T getValue() {
        return delegate.getValue();
    }

    @Override
    protected AccumulatorSize sizeOf(T accum) {
        return AccumulatorSize.ONE;
    }

    @Override
    protected T headOf(T accum) {
        return accum;
    }

    @Override
    protected T tailOf(T accum) {
        throw new UnsupportedOperationException("Cannot take a tail of a single value");
    }

}