package br.com.derlandybelchior.goodplaces.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.databinding.HomeScreenBinding
import br.com.derlandybelchior.goodplaces.presentation.model.PlacePresentationModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding
    private val viewModel: PlacesViewModel by viewModel()

    private val images = arrayOf(
        R.drawable.istockphoto,
        R.drawable.istockphoto2,
        R.drawable.istockphoto3,
        R.drawable.istockphoto4,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeScreenBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ViewState.Loading -> showAppropriateView(loading = true)
                is ViewState.Success -> {
                    setupRecyclerView(state.data)
                    showAppropriateView(content = true)
                }
                else -> showAppropriateView(error = true)
            }
        }

        viewModel.fetchPlaces()
    }

    private fun showAppropriateView(content: Boolean = false, loading: Boolean = false, error: Boolean = false) {
        binding?.progress?.visibility = if(loading) View.VISIBLE else View.GONE
        binding?.contentList?.visibility = if(content) View.VISIBLE else View.GONE
        binding?.error?.visibility = if(error) View.VISIBLE else View.GONE
    }

    private fun setupRecyclerView(data: List<PlacePresentationModel>) = with(binding) {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        this?.contentList?.layoutManager = staggeredGridLayoutManager
        this?.contentList?.adapter = PlacesRecyclerViewAdapter(data) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment2()
            findNavController().navigate(action)
        }

        this?.contentList?.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_8)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}