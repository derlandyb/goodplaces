package br.com.derlandybelchior.goodplaces.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.derlandybelchior.goodplaces.R
import br.com.derlandybelchior.goodplaces.databinding.DetailScreenBinding
import br.com.derlandybelchior.goodplaces.presentation.home.ViewState
import br.com.derlandybelchior.goodplaces.presentation.imagetool.LoadImage
import br.com.derlandybelchior.goodplaces.presentation.model.CommentPresentationModel
import br.com.derlandybelchior.goodplaces.presentation.model.PlaceDetailPresentationModel
import br.com.derlandybelchior.goodplaces.presentation.model.formatSchedulePresentation
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class DetailFragment : Fragment() {

    private var _binding: DetailScreenBinding? = null
    private val binding get() = _binding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: PlaceDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailScreenBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ViewState.Success -> {
                    showContent(state.data, args.imageUrl)
                    setupGallery(state.data.gallery)
                    setupComments(state.data.comments)
                    showAppropriateView(content = true)
                }
                is ViewState.Loading -> {
                    showAppropriateView(loading = true)
                }
                is ViewState.Error -> {
                    showAppropriateView(error = true)
                }
            }
        }

        viewModel.fetch(args.placeId)
    }

    private fun showAppropriateView(content: Boolean = false, loading: Boolean = false, error: Boolean = false) {
        binding?.progress?.visibility = if(loading) View.VISIBLE else View.GONE
        binding?.detailContent?.visibility = if(content) View.VISIBLE else View.GONE
        binding?.error?.visibility = if(error) View.VISIBLE else View.GONE
    }

    private fun showContent(presentationModel: PlaceDetailPresentationModel, imageUrl: String?) = with(binding) {
        this?.collapsingToolbarLayout?.title = presentationModel.name
        this?.collapsingToolbarLayout?.setCollapsedTitleTextAppearance(R.style.Detail_TitleCollapsed)
        this?.collapsingToolbarLayout?.setExpandedTitleTextAppearance(R.style.Detail_TitleExpanded)
        this?.expandedImage?.let {LoadImage.loadImageByUrl(imageUrl, expandedImage)}
        this?.aboutContent?.text =  presentationModel.about
        this?.timeContent?.text =  presentationModel.formatSchedulePresentation(requireContext())
        this?.phoneContent?.text =  presentationModel.phone
        this?.addressContent?.text =  presentationModel.address
        this?.ratingText?.text =  presentationModel.review.toString()
        this?.rating?.rating =  presentationModel.review.toFloat()
    }

    private fun setupGallery(gallery: List<String>) = with(binding){
        this?.photoGallery?.apply {
            adapter = PhotosAdapter(gallery)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupComments(comments: List<CommentPresentationModel>) = with(binding){
        this?.commentList?.apply {
            val randomCommentsIndex = Random.nextInt(0, 96)
            adapter = CommentsAdapter(comments.subList(randomCommentsIndex, randomCommentsIndex + 3))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding?.allComments?.text = requireContext().getString(R.string.see_all_reviews, (comments.size - 4))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}