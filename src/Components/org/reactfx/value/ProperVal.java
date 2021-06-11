package Components.org.reactfx.value;

import Components.org.reactfx.ProperObservable;
import Components.org.reactfx.util.NotificationAccumulator;

import java.util.function.Consumer;

public interface ProperVal<T>
extends Val<T>, ProperObservable<Consumer<? super T>, T> {

    @Override
    default NotificationAccumulator<Consumer<? super T>, T, ?> defaultNotificationAccumulator() {
        return NotificationAccumulator.retainOldestValNotifications();
    }
}
