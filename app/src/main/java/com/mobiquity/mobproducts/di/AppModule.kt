package com.mobiquity.mobproducts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/* Key used to associate ViewModel types with providers */
@MapKey
@Target(AnnotationTarget.FUNCTION)

annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)

@Module
class AppModule() {

    @Provides
    @Singleton
    fun viewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val t = providers[modelClass] ?: providers
                .asIterable()
                .firstOrNull { it.key.isAssignableFrom(modelClass) }
                ?.value

            return requireNotNull(t).get() as T
        }
    }


}