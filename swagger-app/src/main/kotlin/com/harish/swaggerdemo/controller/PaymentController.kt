package com.harish.swaggerdemo.controller

import com.harish.api.PaymentsApi
import com.harish.model.CardPaymentResponse
import com.harish.model.PaymentRequestBase
import com.harish.model.PaymentResponseBase
import com.harish.model.PaypalPaymentRequest
import com.harish.model.PaypalPaymentResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/kotlin")
class PaymentController : PaymentsApi {

    override fun createPayment(paymentRequestBase: PaymentRequestBase): ResponseEntity<PaymentResponseBase> {

        if (paymentRequestBase.paymentType == "card") {
            return ResponseEntity.status(201).body(CardPaymentResponse("card", UUID.randomUUID().toString(), "Done", "2133"))
        }
        return ResponseEntity.status(201).body(PaypalPaymentResponse("paypal", UUID.randomUUID().toString(), "Done", "test@gmail.com"))
    }

    @GetMapping("hello")
    fun hello(): ResponseEntity<String> = ResponseEntity.ok("Hello World")
}