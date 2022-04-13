package com.davidepani.presentation.ui.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.davidepani.androidextensions.utils.imageloader.ImageLoader
import com.davidepani.androidextensions.views.loadImageFromUrl
import com.davidepani.architectures.viewbinding.BaseViewBindingHandlerFragment
import com.davidepani.presentation.databinding.CoinFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinFragment : BaseViewBindingHandlerFragment() {

    private val binding: CoinFragmentBinding get() = requireViewBinding()
    private val viewModel: CoinViewModel by viewModels()

    @Inject lateinit var imageLoader: ImageLoader


    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return CoinFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        viewModel.getCoin()
    }

    private fun observeData() {
        with(viewModel) {

            coinLD.observe(viewLifecycleOwner) {
                with(binding) {
                    tvCoinName.text = it.name
                    tvCoinMarketCap.text = it.marketCap
                    ivCoinImage.loadImageFromUrl(
                        url = it.imageUrl,
                        imageLoader = imageLoader
                    )
                }
            }

            errorLD.observe(viewLifecycleOwner) {
                binding.tvCoinName.text = it
            }

            isProgressVisible.observe(viewLifecycleOwner) {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }

            isCoinContentVisible.observe(viewLifecycleOwner) {
                binding.grCoinContent.visibility = if (it) View.VISIBLE else View.GONE
            }

        }
    }

}