package br.com.derlandybelchior.goodplaces.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.derlandybelchior.goodplaces.domain.FetchLocationUseCase
import br.com.derlandybelchior.goodplaces.domain.Resource
import br.com.derlandybelchior.goodplaces.presentation.model.PlacePresentationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class PlacesViewModel(
    private val fetchLocationUseCase: FetchLocationUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _viewState: MutableLiveData<ViewState<List<PlacePresentationModel>>> = MutableLiveData()
    val viewState: LiveData<ViewState<List<PlacePresentationModel>>>
        get() = _viewState

    fun fetchPlaces() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch(dispatcher) {

            when(val result = fetchLocationUseCase.fetchAllLocations()) {
                is Resource.Success -> {
                    val list = result.data.map { location ->
                        PlacePresentationModel(
                            location.id,
                            location.name,
                            location.review,
                            location.type,
                            location.imageUrl
                        )
                    }
                    _viewState.postValue(ViewState.Success(list))
                }
                else -> _viewState.postValue(ViewState.Error("Ocorreu um erro. Tente novamente!"))
            }
        }
    }
}