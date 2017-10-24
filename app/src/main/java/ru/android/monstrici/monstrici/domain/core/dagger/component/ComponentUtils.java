package ru.android.monstrici.monstrici.domain.core.dagger.component;

import android.content.Context;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

public final class ComponentUtils {

    private ComponentUtils() {
        throw new IllegalStateException("No instances!");
    }

    @SuppressWarnings("unchecked")
    public static <T> T getComponent(Context context, Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>) context.getApplicationContext()).getComponent());
    }
}
