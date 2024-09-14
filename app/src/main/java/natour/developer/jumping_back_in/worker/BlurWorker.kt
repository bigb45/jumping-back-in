package natour.developer.jumping_back_in.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import natour.developer.jumping_back_in.DELAY_TIME_MILLIS
import natour.developer.jumping_back_in.KEY_BLUR_LEVEL
import natour.developer.jumping_back_in.KEY_IMAGE_URI
import natour.developer.jumping_back_in.R

private const val TAG = "BlurWorker"
class BlurWorker (ctx: Context, params: WorkerParameters ): CoroutineWorker(ctx, params){
    override suspend fun doWork(): Result {
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)
        makeStatusNotification("BlurWorker has started blurring your image!",
            applicationContext)
        //        TODO: blur image here

        return withContext(Dispatchers.IO){
            delay(DELAY_TIME_MILLIS)
            return@withContext try {
                require(!resourceUri.isNullOrBlank()){
                    val errorMessage = "The resource uri must not be null or empty"
                    errorMessage
                }

                val resolver = applicationContext.contentResolver
                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )
                val output = blurBitmap(picture, blurLevel)
                val outputUri = writeBitmapToFile(applicationContext, output)
                Log.d(TAG, "Wrote bitmap to uri $outputUri")

                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
                Result.success(outputData)

            }catch (throwable: Throwable){

                Log.e(TAG, "An error occurred while blurring the image,${throwable.cause} ${throwable.message}")
                Result.failure()

            }
        }
    }


}