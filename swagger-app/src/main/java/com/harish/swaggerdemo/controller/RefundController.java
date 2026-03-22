package com.harish.swaggerdemo.controller;

import com.harish.java.api.RefundsApi;
import com.harish.java.model.CreateRefund200Response;
import com.harish.java.model.CreateRefundRequest;
import com.harish.java.model.FullRefundResponse;
import com.harish.java.model.PartialRefundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/java")
public class RefundController implements RefundsApi {

    @Override
    public ResponseEntity<CreateRefund200Response> createRefund(CreateRefundRequest createRefundRequest) {
        if ("partial".equalsIgnoreCase(createRefundRequest.getRefundType())) {
            return ResponseEntity.ok(new PartialRefundResponse("partial", "d4324dsvcdef3234").refundedAmount(2000.0));
        }
        return ResponseEntity.ok(new FullRefundResponse("full", "ferf434235432dsv").refundedAt(OffsetDateTime.now()));
    }
}
