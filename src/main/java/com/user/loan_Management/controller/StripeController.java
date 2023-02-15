package com.user.loan_Management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.model.BankAccount;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentSource;
import com.stripe.model.Source;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerRetrieveParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentSourceCollectionCreateParams;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.StripeCustomerDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.model.Pan;
import com.user.loan_Management.model.StripeCustomer;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.repository.StripeCustomerRepository;

@RestController
@RequestMapping("/stripe")
public class StripeController {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	StripeCustomerRepository stripeCustomerRepository;

	@Autowired
	ModelMapper modelMapper;

	@Value("${stripe.apiKey}")
	String apiKey;

	@PostMapping("/create-customer/{loanApplicationNo}")
	public DataResponse createCustomer(@PathVariable long loanApplicationNo) {

		try {

			Stripe.apiKey = apiKey;

			Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(loanApplicationNo);
			if (!(loanApplication.isPresent()
					&& loanApplication.get().getApplicationStatus().equals(ConstantMessage.APPROVED)))
				throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);

			// to attach card details to a customer we need Customer with source
			// using source we will create CustomerRetrieveParams
			// Using CustomerRetrieveParams we will retrieve customer which will have
			// customer with sources
			// then we will create Card object using retrieveCustomer by adding cardToken in
			// source

			CustomerRetrieveParams customerRetrieveParams = CustomerRetrieveParams.builder().addExpand("sources")
					.build();
			Customer retrieveCustomer;
			// BankAccount bankAccount;
			Card cardObj=null;
			Token cardToken;
			Map<String, Object> source = new HashMap<String, Object>();

			if (stripeCustomerRepository.findByLoanApplicationNo(loanApplicationNo) == null) {
				Map<String, Object> customerParams = new HashMap<String, Object>();
				customerParams.put("name", loanApplication.get().getName());
				customerParams.put("email", loanApplication.get().getMail());
				Customer customer = Customer.create(customerParams);

				Map<String, Object> card = new HashMap<String, Object>();
				Map<String, Object> cardParams = new HashMap<String, Object>();
				card.put("number", "4242424242424242");
				card.put("exp_month", 1);
				card.put("exp_year", 2024);
				card.put("cvc", "123");
				cardParams.put("card", card);

				 cardToken = Token.create(cardParams);
//			System.out.println("==============CARD TOKEN===============");
//			System.out.println(cardToken);

//			Map<String, Object> bankAcc = new HashMap<>();
//			bankAcc.put("country", "US");
//			bankAcc.put("currency", "usd");
//			bankAcc.put(
//			  "account_holder_name",
//			  "Arnav jaiswal"
//			);
//			bankAcc.put(
//			  "account_holder_type",
//			  "individual"
//			);
//			bankAcc.put("routing_number", "110000000");
//			bankAcc.put("account_number", "000123456789");
//			Map<String, Object> bankPar = new HashMap<>();
//			bankPar.put("bank_account", bankAcc);

//			Token bankToken = Token.create(bankPar);

				StripeCustomer stripeCustomer = new StripeCustomer();
				stripeCustomer.setStripeCustomerId(customer.getId());
				stripeCustomer.setMailId(customer.getEmail());
				stripeCustomer.setName(customer.getName());
				stripeCustomer.setLoanApplicationNo(loanApplicationNo);
				stripeCustomer.setCardToken(cardToken.getId());
				stripeCustomerRepository.save(stripeCustomer);

				retrieveCustomer = Customer.retrieve(customer.getId(), customerRetrieveParams, null);

				// attaching card with customer

				
				source.put("source", cardToken.getId());
				cardObj = (Card) retrieveCustomer.getSources().create(source);

			} else {

				retrieveCustomer = Customer.retrieve(
						stripeCustomerRepository.findByLoanApplicationNo(loanApplicationNo).getStripeCustomerId(),
						customerRetrieveParams, null);
				System.out.println(retrieveCustomer);
				Map<String, Object> card = new HashMap<String, Object>();
				Map<String, Object> cardParams = new HashMap<String, Object>();
				card.put("number", "4111111111111111");
				card.put("exp_month", 1);
				card.put("exp_year", 2024);
				card.put("cvc", "123");
				cardParams.put("card", card);

				
				cardToken = Token.create(cardParams);
				
				
				List<PaymentSource> paymentSourceDatas = retrieveCustomer.getSources().getData();
				 
				for(PaymentSource paymentSourceData: paymentSourceDatas) {
					Card cardObject=(Card) paymentSourceData;
					if(cardObject.getFingerprint().equals(cardToken.getCard().getFingerprint()))
					{
						cardObj = (Card) retrieveCustomer.getSources().retrieve(cardObject.getId());
						break;
					}
				}
				if(cardObj==null) {
						source.put("source", cardToken.getId());
				cardObj = (Card) retrieveCustomer.getSources().create(source);}
				//Card cardSource = (Card) data.get(0);
//				System.out.println(  cardSource.getFingerprint());
				//Map<String, Object> source = new HashMap<String, Object>();
		//		source.put("source", cardToken.getId());
		//		cardObj = (Card) retrieveCustomer.getSources().create(source);
				
//				Object number = card.get("number");
//
//				String last4 = number.toString().substring(12);
				
				
//			Token existingCardToken = Token
//						.retrieve(stripeCustomerRepository.findByLoanApplicationNo(loanApplicationNo).getCardToken());
//				if(!(existingCardToken.getCard().getFingerprint().equals(cardToken.getCard().getFingerprint()))) {
					
				
			//	if (!(existingCardToken.getCard().getLast4().equals(last4))) {
				//	Token newCardToken = Token.create(cardParams);
				//	System.out.println("==============CARD TOKEN===============");
				//	System.out.println(existingCardToken);
				//	cardObj = (Card) retrieveCustomer.getSources().retrieve(newCardToken.getCard().getId());
//					cardObj = (Card) retrieveCustomer.getSources().create(source);
//				}

//				cardObj = (Card) retrieveCustomer.getSources().retrieve(existingCardToken.getCard().getId());

			}

//			
//			PaymentIntentCreateParams param =
//					  PaymentIntentCreateParams
//					    .builder()
//					    .setAmount(1099L)
//					    .setCurrency("usd")
//					    .setCustomer(retrieveCustomer.getId())
//					    .addPaymentMethodType("card")
//					    .build();

			PaymentIntentCreateParams param = PaymentIntentCreateParams.builder().setAmount(1099L).setCurrency("INR")
					.setCustomer(retrieveCustomer.getId()).addPaymentMethodType("card").setConfirm(true).build();

			PaymentIntent paymentIntent = PaymentIntent.create(param);

			System.out.println(paymentIntent);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, null);

		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("/get-customer/{applicationNo}")
	public DataResponse getCustomer(@PathVariable long applicationNo) {

		try {
			Stripe.apiKey = apiKey;
			StripeCustomer stripeCustomer = stripeCustomerRepository.findByLoanApplicationNo(applicationNo);

			if (stripeCustomer == null)
				throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);

			Customer customer = Customer.retrieve(stripeCustomer.getStripeCustomerId());
			System.out.println(customer);
			return new DataResponse(StatusCode.SUCCESS,ConstantMessage.OK,null);

		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());

		}

	}
}
