package ru.android.monstrici.monstrici.domain.core.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Remote {
}