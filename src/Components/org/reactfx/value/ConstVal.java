package Components.org.reactfx.value;

import Components.org.reactfx.RigidObservable;

import java.util.function.Consumer;

class ConstVal<T>
extends RigidObservable<Consumer<? super T>>
implements Val<T> {
    private final T value;

    ConstVal(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
