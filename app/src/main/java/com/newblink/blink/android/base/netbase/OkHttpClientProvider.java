package com.newblink.blink.android.base.netbase;

import android.text.TextUtils;

import com.newblink.blink.android.base.App;
import com.newblink.blink.android.common.BlinkSecretUtil;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.common.MakeStrUtil;
import com.newblink.blink.android.common.utils.DeviceUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;


public class OkHttpClientProvider {

    private static final String ACCESS_TOKEN = "accessToken";


    public static OkHttpClient client() {
        return Holder.okHttpClient;
    }

    public static OkHttpClient myClient() {
        return MyHolder.myOkHttpClient;
    }

    private static class MyHolder {
        private static OkHttpClient myOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    private static class Holder {
        private static OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .addInterceptor(new HeaderInterceptor())
                        .addInterceptor(new ParamsInterceptor())
                        .addInterceptor(new encryptionInterceptor())
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build();
    }

    /**
     * 服务器传参需要，需在每个url接口添加token等相关信息
     */
    private static class ParamsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            String accessToken = LocalRepository.getInstance().getAccessToken();
            HttpUrl.Builder builder = request.url().newBuilder();

            if (!TextUtils.isEmpty(accessToken)) {
                                builder.addQueryParameter(ACCESS_TOKEN, accessToken);
            }

            HttpUrl httpUrl = builder.build();
            request = request.newBuilder().url(httpUrl).build();

            return chain.proceed(request);
        }
    }


    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            String userAgent = DeviceUtil.getUserAgent(App.getInstance());
            if (!TextUtils.isEmpty(userAgent)) {
                request = request.newBuilder().removeHeader("User-Agent").addHeader("User-Agent", userAgent).build();
            }

            return chain.proceed(request);
        }
    }


    private static class encryptionInterceptor implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            RequestBody oldRequestBody = request.body();
            Buffer requestBuffer = new Buffer();
            oldRequestBody.writeTo(requestBuffer);
            String oldBodyStr = requestBuffer.readUtf8();
            requestBuffer.close();
            byte[] oldRequestBytes = oldBodyStr.getBytes() ;
            byte[] newRequestBytes = BlinkSecretUtil.addSecret(oldRequestBytes, MakeStrUtil.getKey());
            RequestBody requestBody = RequestBody.create(oldRequestBody.contentType(), newRequestBytes);
            HttpUrl.Builder builder = request.url().newBuilder();
            HttpUrl httpUrl = builder.build();
            request = request.newBuilder().post(requestBody).url(httpUrl).build();


            Response response = chain.proceed(request);
            if (response.code() == 200) {//只有约定的返回码才经过加密，才需要走解密的逻辑
                ResponseBody oldResponseBody = response.body();
                byte[] oldResponseBytes = oldResponseBody.bytes();
                byte[] newResponseBytes = BlinkSecretUtil.solutionSecret(oldResponseBytes,MakeStrUtil.getKey());
                oldResponseBody.close();
                //构造新的response
                ResponseBody newResponseBody = ResponseBody.create(oldRequestBody.contentType(), newResponseBytes);
                response = response.newBuilder().body(newResponseBody).build();
            }
            response.close();

            return response;
        }

    }


}
