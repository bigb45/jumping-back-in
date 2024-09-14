package natour.developer.jumping_back_in.presentation.ui.camera.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import natour.developer.jumping_back_in.data.WorkerRepository
import javax.inject.Inject

@HiltViewModel
class BlurViewmodel @Inject constructor(private val workerRepository: WorkerRepository) :
    ViewModel() {

        val blurUiState = BlurUiState.Default

    fun applyBlur(blurLevel: Int, imageUri: Uri) {
        workerRepository.applyBlur(imageUri, blurLevel)
    }
}

sealed interface BlurUiState {
    object Default : BlurUiState
    object Loading : BlurUiState
    data class Complete(val outputUri: String) : BlurUiState
}