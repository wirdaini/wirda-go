import com.example.wirda_go.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String = "laporan warga",
        @Query("language") language: String = "id",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "6ba41164e9684d9bb7fe544b1c1957e4"
    ): NewsResponse
}