package com.danielcunha.topseries.ui.popularTv

import androidx.fragment.app.viewModels
import com.danielcunha.topseries.R
import com.danielcunha.topseries.databinding.FragmentPopularTvListBinding
import com.danielcunha.topseries.ui.base.BaseFragment

class PopularTvListFragment: BaseFragment<PopularTvListViewModel, FragmentPopularTvListBinding>() {

    override val viewModel: PopularTvListViewModel by viewModels()
    override fun layoutResId() = R.layout.fragment_popular_tv_list
}