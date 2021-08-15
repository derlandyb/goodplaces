package br.com.derlandybelchior.goodplaces.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.derlandybelchior.goodplaces.BuildConfig
import br.com.derlandybelchior.goodplaces.domain.FetchLocationUseCase
import br.com.derlandybelchior.goodplaces.domain.Resource
import br.com.derlandybelchior.goodplaces.presentation.home.ViewState
import br.com.derlandybelchior.goodplaces.presentation.model.CommentPresentationModel
import br.com.derlandybelchior.goodplaces.presentation.model.PlaceDetailPresentationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class PlaceDetailViewModel(
    private val fetchLocationUseCase: FetchLocationUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _viewState: MutableLiveData<ViewState<PlaceDetailPresentationModel>> = MutableLiveData()
    val viewState: LiveData<ViewState<PlaceDetailPresentationModel>>
        get() = _viewState

    fun fetch(id: Int) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(dispatcher) {

            when(val placeDetail = fetchLocationUseCase.fetch(id)) {
                is Resource.Success -> {
                        val placeDetailPresentation =  PlaceDetailPresentationModel(
                            id = placeDetail.data.id,
                            name = placeDetail.data.name,
                            review = placeDetail.data.review,
                            type = placeDetail.data.type,
                            about = placeDetail.data.about,
                            phone = placeDetail.data.phone,
                            address = placeDetail.data.address,
                            schedule = placeDetail.data.schedule,
                            gallery = placeDetail.data.gallery,
                            comments = placeDetail.data.comments.map {

                                CommentPresentationModel(
                                    it.title,
                                    it.author,
                                    it.comment,
                                    buildUrlForRandomUserPhoto(it.photoId),
                                    it.review
                                )
                            }
                        )

                    _viewState.postValue(ViewState.Success(placeDetailPresentation))
                }
                else -> _viewState.postValue(ViewState.Error("Ocorreu um erro. Tente novamente!"))
            }
        }
    }

    private fun buildUrlForRandomUserPhoto(photoId: Int): String {
        val maleOrFemale = arrayOf("men", "women")
        val photoExtension = ".jpg"
        maleOrFemale.shuffle()
        val baseUrl = BuildConfig.RANDOMUSER_BASE_URL
        val stringBuilder = StringBuilder(baseUrl)
        stringBuilder.append(maleOrFemale.first())
            .append("/")
            .append(photoId)
            .append(photoExtension)

        return stringBuilder.toString()
    }
}