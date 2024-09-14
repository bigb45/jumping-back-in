package natour.developer.jumping_back_in.utils


suspend fun <T> safeApiCall(
    apiRequest: suspend () -> T,
): Result<T> {
    try {
        val res = apiRequest()
        return Result.success(res)
    } catch (e: Exception) {
        return Result.failure(exception = e)
    }
}
