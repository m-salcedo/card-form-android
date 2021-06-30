package com.mercadolibre.android.cardform.presentation.viewmodel.webview

object FlowRetryProvider {

    var retry: () -> Unit = {}
    private set

    fun setRetryFunction(block: () -> Unit) {
        retry = block
    }
}