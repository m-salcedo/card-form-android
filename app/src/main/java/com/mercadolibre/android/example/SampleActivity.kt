package com.mercadolibre.android.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.android.cardform.CardForm
import com.mercadolibre.android.cardform.CardForm.Companion.RESULT_CARD_ID_KEY
import com.mercadolibre.android.cardform.data.model.body.CardInfoDto
import com.mercadolibre.android.cardform.data.model.body.ItemDto
import com.mercadolibre.android.cardform.internal.CardFormWeb
import com.mercadolibre.android.cardform.service.CardFormIntent
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val userId = "813980246"
        val siteId = "MLB"
        val token = "APP_USR-7092-091712-fa94e7eb26e58720ffab294da5a5730c-813980246"
        val flowId = "checkout-on"
        val itemId = "MLB1935672467"

        val items = arrayListOf(ItemDto(itemId))

        val cardInfoDto = CardInfoDto(
            flowId,
            flowId,
            flowId,
            "",
            userId,
            "7092",
            siteId,
            true,
            items
        )

        val cardForm = CardForm.Builder.withAccessToken(token, siteId, flowId)
            .setCardInfo(cardInfoDto).build()

        cardForm.start(this, REQUEST_CODE)

        fromOneTap.setOnClickListener {
            OneTapActivity.start(this)
        }

        fromWebView.setOnClickListener {
            CardFormWeb
                .Builder
                .withAccessToken(
                    token,
                    "MLB", "test_flow")
                .setCardFormHandler(CardFormIntent(this, SampleService::class.java))
                .build()
                .start(this, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            data?.let { result ->
                val cardId = result.getStringExtra(RESULT_CARD_ID_KEY)
                Toast.makeText(this, "Card Id: $cardId", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 213
    }
}