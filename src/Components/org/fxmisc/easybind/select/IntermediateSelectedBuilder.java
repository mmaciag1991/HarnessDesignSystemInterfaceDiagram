package Components.org.fxmisc.easybind.select;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

class IntermediateSelectedBuilder<T, U> implements ParentSelectedBuilder<U> {
    private final ParentSelectedBuilder<T> parent;
    private final Function<? super T, ObservableValue<U>> selector;

    public IntermediateSelectedBuilder(
            ParentSelectedBuilder<T> parent,
            Function<? super T, ObservableValue<U>> selector) {
        this.parent = parent;
        this.selector = selector;
    }

    @Override
    public <V> MonadicBinding<V> create(
            NestedSelectionElementFactory<U, V> nestedSelectionFactory) {
        NestedSelectionElementFactory<T, V> intermediateSelectionFactory = onInvalidation -> {
            return new IntermediateSelectionElement<T, U, V>(onInvalidation, selector, nestedSelectionFactory);
        };
        return parent.create(intermediateSelectionFactory);
    }

}