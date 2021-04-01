package com.danielcunha.topseries.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.danielcunha.topseries.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext
import com.danielcunha.topseries.BR

abstract class BaseFragment<T : BaseViewModel, V : ViewDataBinding> :
    Fragment(),
    KoinComponent,
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = SupervisorJob()

    protected abstract val viewModel: T

    @LayoutRes
    protected abstract fun layoutResId(): Int

    protected lateinit var binding: V

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId(), container, false)

        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateTo.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }

        viewModel.back.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showErrorMessage(it)
        }

        setupFragment()
        setupViewModel()

        return binding.root
    }

    open fun setupFragment() {}
    open fun setupViewModel() {}

    protected fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red)).show()
    }

    fun setFragmentResult(key: String, value: Any) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            key,
            value
        )
    }

    fun <T> getFragmentResult(key: String): LiveData<T>? {
        return findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData(key)
    }

    open fun onCameraPermissionGranted() {

    }
}