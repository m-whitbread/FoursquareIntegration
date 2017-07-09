package uk.co.markomeara.whitbread.testrule;

import android.support.test.espresso.IdlingResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.markomeara.whitbread.data.source.FoursquareApi;
import uk.co.markomeara.whitbread.di.AppComponent;
import uk.co.markomeara.whitbread.di.AppModule;
import uk.co.markomeara.whitbread.di.DaggerAppComponent;
import uk.co.markomeara.whitbread.di.Injector;

public class MockServerTestRule implements TestRule {

    private final MockWebServer mServer = new MockWebServer();
    private IdlingResource okHttpIdlingResource;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                mServer.start();
                injectMockedApi();
                base.evaluate();
                mServer.shutdown();
            }
        };
    }

    public void enqueueSuccess(String responseBody) {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(responseBody);
        mServer.enqueue(mockResponse);
    }

    public void enqueueSuccess(Object responseObj) {
        String json = new Gson().toJson(responseObj);
        enqueueSuccess(json);
    }

    public IdlingResource getOkHttpIdlingResource() {
        return okHttpIdlingResource;
    }

    private void injectMockedApi() {
        AppComponent component = DaggerAppComponent.builder().appModule(new MockApiModule()).build();
        Injector.setComponent(component);
    }

    @Module
    private class MockApiModule extends AppModule {

        @Override
        @Provides
        @Singleton
        public FoursquareApi provideFoursquareApi(OkHttpClient okHttpClient) {

            okHttpIdlingResource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mServer.url("/").toString())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(FoursquareApi.class);
        }
    }

}