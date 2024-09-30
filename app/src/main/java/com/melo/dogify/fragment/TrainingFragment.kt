package com.melo.dogify.fragment


import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.TrainingAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentTrainingBinding
import com.melo.dogify.extensions.navigate
import com.melo.dogify.model.TrainingModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class TrainingFragment : BaseFragment<SoundsViewModel, FragmentTrainingBinding>(),
    TrainingAdapter.ItemClickListener {

    private val trainingAdapter: TrainingAdapter by lazy {
        TrainingAdapter(
            requireContext(),
            this@TrainingFragment

        )
    }

    private var selectedTraining: TrainingModel? = null
    private var selectedItemPosition: Int = 0


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_training

    override fun onInitDataBinding() {
        setupTrainingStyle()
    }

    private fun setupTrainingStyle() {
        viewBinding.rvTraining.apply {
            adapter = trainingAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
            setHasFixedSize(true)
        }
        trainingAdapter.submitList(viewModel.trainingList)

    }

    override fun onItemClick(item: TrainingModel) {
        selectedItemPosition = viewModel.trainingList.indexOf(item)
        selectedTraining = item

        if (selectedItemPosition == 0) {
            navigate(TrainingFragmentDirections.actionTrainingFragmentToFoodFragment())
        }
        if (selectedItemPosition == 1) {
            navigate(TrainingFragmentDirections.actionTrainingFragmentToArticleFragment())
        }
        if (selectedItemPosition == 2) {
            navigate(TrainingFragmentDirections.actionTrainingFragmentToTrainingBitingFragment())
        }
        if (selectedItemPosition == 3) {
            navigate(TrainingFragmentDirections.actionTrainingFragmentToObidenceFragment())
        }
        if (selectedItemPosition == 4) {
            navigate(TrainingFragmentDirections.actionTrainingFragmentToBarkingFragment())
        }
        trainingAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
    }


}
