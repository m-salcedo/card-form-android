package com.mercadolibre.android.cardform.network.interceptor

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class InterceptRequest : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        System.out.println("NMAT - Intercepted request = ${Gson().toJson(request)}")

        val response: Response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            System.out.println("NMAT - failed")
            throw e
        }
        System.out.println("NMAT - Intercepted response = ${Gson().toJson(response)}")

        return response
    }
}
