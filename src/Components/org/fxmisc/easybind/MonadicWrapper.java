package Components.org.fxmisc.easybind;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import Components.org.fxmisc.easybind.monadic.PropertyBinding;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;
import java.util.function.Predicate;

class MonadicWrapper<T> extends PreboundBinding<T> {
    private final ObservableValue<T> delegate;

    public MonadicWrapper(ObservableValue<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    protected T computeValue() {
        return delegate.getValue();
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public MonadicBinding<T> orElse(T other) {
        return EasyBind.orElse(delegate, other);
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public MonadicBinding<T> orElse(ObservableValue<T> other) {
        return EasyBind.orElse(delegate, other);
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public MonadicBinding<T> filter(Predicate<? super T> p) {
        return EasyBind.filter(delegate, p);
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public <U> MonadicBinding<U> map(Function<? super T, ? extends U> f) {
        return EasyBind.map(delegate, f);
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public <U> MonadicBinding<U> flatMap(
            Function<? super T, ? extends ObservableValue<U>> f) {
        return EasyBind.flatMap(delegate, f);
    }

    // More efficient than the default, eliminates this wrapper from the chain.
    @Override
    public <U> PropertyBinding<U> selectProperty(
            Function<? super T, ? extends Property<U>> f) {
        return EasyBind.selectProperty(delegate, f);
    }
}
