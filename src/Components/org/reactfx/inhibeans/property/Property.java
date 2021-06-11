package Components.org.reactfx.inhibeans.property;

import Components.org.reactfx.inhibeans.value.ObservableValue;
import Components.org.reactfx.value.SuspendableVar;

/**
 * @deprecated Superseded by {@link SuspendableVar}.
 */
@Deprecated
public interface Property<T>
extends javafx.beans.property.Property<T>, ObservableValue<T> {}
