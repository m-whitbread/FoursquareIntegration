package uk.co.markomeara.whitbread.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.markomeara.whitbread.BuildConfig;
import uk.co.markomeara.whitbread.data.source.FoursquareApi;
import uk.co.markomeara.whitbread.data.source.FoursquareAuthInterceptor;

@Module
public class AppModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder
                .addInterceptor(logging)
                .addInterceptor(new FoursquareAuthInterceptor(BuildConfig.PUBLIC_API_KEY, BuildConfig.PRIVATE_API_KEY, BuildConfig.FOURSQUARE_API_VERSION))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    public FoursquareApi provideFoursquareApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.FOURSQUARE_API_BASE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(FoursquareApi.class);
    }

}