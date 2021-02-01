package com.rs.ed.api.doolspract;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
public class MegaOfferController {

	private MegaOfferDetails offerDetails;

	public MegaOfferController(MegaOfferDetails offerDetails) {
		super();
		this.offerDetails = offerDetails;
	}

	@PostMapping("/bill")
	public ResponseEntity<PaymentDetails> getOfferPayment(@RequestBody @NonNull TrxSummary trx) {
		PaymentDetails paymentDetails = offerDetails.executeDroolEngine(trx);
		return new ResponseEntity<PaymentDetails>(paymentDetails, HttpStatus.OK);

	}

}
