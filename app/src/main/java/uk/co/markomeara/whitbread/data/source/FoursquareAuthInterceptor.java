package uk.co.markomeara.whitbread.data.source;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FoursquareAuthInterceptor implements Interceptor {

    private static final String PUBLIC_KEY_PARAM_NAME = "client_id";
    private static final String PRIVATE_KEY_PARAM_NAME = "client_secret";
    private static final String VERSION_PARAM_NAME = "v";

    private String publicKey;
    private String privateKey;
    private String apiVersion;

    public FoursquareAuthInterceptor(String publicKey, String privateKey, String apiVersion) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.apiVersion = apiVersion;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        HttpUrl url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter(PUBLIC_KEY_PARAM_NAME, publicKey)
                    .addQueryParameter(PRIVATE_KEY_PARAM_NAME, privateKey)
                    .addQueryParameter(VERSION_PARAM_NAME, apiVersion)
                    .build();

        Request request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request);

    }
}