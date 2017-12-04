package ru.android.monstrici.monstrici.domain.core.dagger.module;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.domain.ConnectionManager;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.ApplicationContext;
import ru.android.monstrici.monstrici.presentation.model.MonsterContainer;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */
@Module
public class CoreModule {


    public CoreModule() {

    }

    @Provides
    @Singleton
    ConnectionManager provideConnectionManager(@ApplicationContext Context context) {
        return new ConnectionManager(context);
    }

    @SuppressLint("Recycle")
    @Provides
    @Singleton
    MonsterContainer provideMonsterContainer(@ApplicationContext Context context) {
        TypedArray bodyList = context.getResources().obtainTypedArray(R.array.monster_body_list);
        TypedArray eyesList = context.getResources().obtainTypedArray(R.array.monster_eye_list);
        TypedArray mouthList = context.getResources().obtainTypedArray(R.array.monster_mouth_list);
       // List<String> bodyList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.monster_body_list)));
     //   List<String> eyesList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.monster_eye_list)));
       // List<String> mouthList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.monster_mouth_list)));
        return new MonsterContainer(bodyList, eyesList, mouthList);
    }
}
