package com.danielcunha.topseries.ui.base

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import org.koin.core.KoinComponent

open class BaseViewModel(open val app: Application) : AndroidViewModel(app), KoinComponent {

    val navigateTo = SingleLiveEvent<NavDirections>()
    val back = SingleLiveEvent<Any>()

    val isLoading = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()
    val title = MutableLiveData("")

    // Validation
    private val validationRules: ArrayList<() -> Boolean> by lazy {
        ArrayList()
    }

    private val validationStateMap: LinkedHashMap<MutableLiveData<String>, Boolean> by lazy {
        LinkedHashMap()
    }

    private val validationRulesMap: HashMap<MutableLiveData<String>, (String) -> Boolean> by lazy {
        HashMap()
    }

    val valid: MediatorLiveData<Boolean> by lazy {
        MediatorLiveData<Boolean>().also { mediator ->
            validationStateMap.forEach { validationState ->
                mediator.addSource(validationState.key) { fieldValue ->
                    validationStateMap[validationState.key] =
                        validationRulesMap[validationState.key]?.invoke(fieldValue) ?: false
                    mediator.value =
                        validationStateMap.filter { validField -> !validField.value }.isEmpty()
                }
            }
        }
    }

    protected fun setupValidMediator(vararg validations: Pair<MutableLiveData<String>, (String) -> Boolean>) {
        validations.forEach { validationPair ->
            validationStateMap[validationPair.first] = false
            validationRulesMap[validationPair.first] = validationPair.second
        }

        valid.value = false
    }

    fun addValidationRules(vararg validationRules: () -> Boolean) {
        this.validationRules.addAll(validationRules)
    }

    fun validate(): Boolean {
        return !validationRules.map { rules ->
            rules.invoke()
        }.any { !it }
    }

    // End validation

    fun onBackClick() {
        back.call()
    }

    protected fun getString(@StringRes resId: Int): String {
        return app.getString(resId)
    }

    protected fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return app.getString(resId, *formatArgs)
    }

    protected fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(app, resId)
    }
}