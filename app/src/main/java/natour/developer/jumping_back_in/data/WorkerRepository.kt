package natour.developer.jumping_back_in.data

import android.content.Context
import android.net.Uri
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import natour.developer.jumping_back_in.KEY_BLUR_LEVEL
import natour.developer.jumping_back_in.KEY_IMAGE_URI
import natour.developer.jumping_back_in.worker.BlurWorker

interface WorkerRepository{
    val outputWorkInfo: Flow<WorkInfo>

    fun applyBlur(imageUri: Uri, blurLevel: Int)
    fun cancelWork()
}

class WorkerRepositoryImpl(context: Context): WorkerRepository{

    private val imageUri: Uri = context.getImageUri()
    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo>
        get() = TODO("Not yet implemented")

    override fun applyBlur(imageUri: Uri, blurLevel: Int) {
        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri))
        workManager.enqueue(blurBuilder.build())
    }

    override fun cancelWork() {
        TODO("Not yet implemented")
    }

    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}


fun Context.getImageUri(): Uri{
return Uri.EMPTY

}