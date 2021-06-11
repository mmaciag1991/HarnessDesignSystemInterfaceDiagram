package Components.org.fxmisc.easybind.select;

import Components.org.fxmisc.easybind.monadic.MonadicBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

interface ParentSelectedBuilder<T> extends SelectBuilder<T> {
    @Override
    default <U> SelectBuilder<U> select(Function<? super T, ObservableValue<U>> selector) {
        return new IntermediateSelectedBuilder<T, U>(this, selector);
    }

    @Override
    default <U> MonadicBinding<U> selectObject(Function<? super T, ObservableValue<U>> selector) {
        NestedSelectionElementFactory<T, U> leafSelectionFactory = onInvalidation -> {
            return new LeafSelectionElement<T, U>(onInvalidation, selector);
        };
        return create(leafSelectionFactory);
    }

    <U> MonadicBinding<U> create(NestedSelectionElementFactory<T, U> nestedSelectionFactory);
}