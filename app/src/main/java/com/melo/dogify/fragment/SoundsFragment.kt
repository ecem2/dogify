package com.melo.dogify.fragment


import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel.SoundsViewModel

class SoundsFragment : BaseFragment<SoundsViewModel, FragmentSoundsBinding>(),
    SoundsAdapter.ItemClickListener {

    private val soundsAdapter: SoundsAdapter by lazy {
        SoundsAdapter(
            requireContext(),
            this@SoundsFragment

        )
    }

    private var selectedCard: CardModel? = null
    private var selectedItemPosition: Int = 0


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_sounds

    override fun onInitDataBinding() {
        setupCardStyle()
        soundsAdapter.submitList(viewModel.cardList)
    }

    private fun setupCardStyle() {
        viewBinding.rvDog.apply {
            adapter = soundsAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemClick(item: CardModel) {
        selectedItemPosition = viewModel.cardList.indexOf(item)
        selectedCard = item
        // item.isSelected = true
        soundsAdapter.notifyDataSetChanged()
    }
}
