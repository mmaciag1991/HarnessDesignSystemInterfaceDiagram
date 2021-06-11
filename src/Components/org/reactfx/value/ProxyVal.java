package Components.org.reactfx.value;

import Components.org.reactfx.ProxyObservable;

import java.util.function.Consumer;

public abstract class ProxyVal<T, U>
extends ProxyObservable<Consumer<? super T>, Consumer<? super U>, Val<U>>
implements Val<T> {

    protected ProxyVal(Val<U> underlying) {
        super(underlying);
    }
}
