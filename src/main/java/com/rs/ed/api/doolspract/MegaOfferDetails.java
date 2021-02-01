package com.rs.ed.api.doolspract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;
import org.springframework.stereotype.Service;

@Service
public class MegaOfferDetails {

//	public static void main(String[] args) throws DroolsParserException, IOException {
//		TrxSummary trx = new TrxSummary();
//		trx.setCardOwner("HDFC");
//		trx.setPurchaseAmt(12000);
//
////		new MegaOfferDetails().executeDroolEngine(trx);
//
//	}
	
	public  PaymentDetails executeDroolEngine(TrxSummary trx){
		
		PaymentDetails payDtls = new PaymentDetails();
		
		String drlFile = "/com/rule/discount-rules.drl";
		InputStream is = getClass().getResourceAsStream(drlFile);
		
		InputStreamReader reader = new InputStreamReader(is);
		
		PackageBuilder pkg = new PackageBuilder();
		try {
			pkg.addPackageFromDrl(reader);
		} catch (DroolsParserException | IOException e) {
			e.printStackTrace();
		}
		
		Package rulesPkg = pkg.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulesPkg);
		
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		workingMemory.insert(trx);
		workingMemory.fireAllRules();
		
		double totalPay = trx.getPurchaseAmt()-(trx.getPurchaseAmt()*trx.getDiscount())/100;
	
		payDtls.setTotalPay(totalPay);
		payDtls.setOffer(trx.getDiscount());
		System.out.println();
		return payDtls;
	}
}
