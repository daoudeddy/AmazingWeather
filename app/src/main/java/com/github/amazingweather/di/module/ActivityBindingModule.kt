package com.github.amazingweather.di.module

import com.github.amazingweather.di.scope.ActivityScoped
import com.github.amazingweather.presentation.ui.activity.FragmentContainerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [FragmentContainerModule::class])
    internal abstract fun fragmentContainerActivity(): FragmentContainerActivity
}