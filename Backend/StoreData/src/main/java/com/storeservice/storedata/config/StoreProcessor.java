package com.storeservice.storedata.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;

import com.storeservice.storedata.model.Store;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoreProcessor implements ItemProcessor<Store,Store> {

	@Override
	public Store process(Store store) throws Exception {
		log.info("BEGIN - [Validating store Id]");
			
		if(store.getStore_Id().length()==5) {
			if(validationForStoreId(store.getStore_Id())) {
				log.info("END - [Validation completed]");
				return store;
				
			}
			else {
				log.info("END - [Condition not met]");
				return null;
			}
		}
		else {
			return null;
		}
	}

	private boolean validationForStoreId(String store_Id) {
		log.info("BEGIN - [Store Id validation]");
		Pattern patternForNoSpecialCharacterPattern=Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcherForSpecialCharaacter=patternForNoSpecialCharacterPattern.matcher(store_Id);
		boolean isStringContainsSpecialCharacter=matcherForSpecialCharaacter.find();
		
		Pattern patternForAlphabet=Pattern.compile(".*[a-zA-Z]+.*");
		Matcher matcherForAlphabets=patternForAlphabet.matcher(store_Id);
		boolean isAlphabetPattern=matcherForAlphabets.find();
		
		Pattern patternForNumber=Pattern.compile(".*\\d.*");
		Matcher matcherForNumber=patternForNumber.matcher(store_Id);
		boolean isStringContainsNumber=matcherForNumber.find();
		
		if(!isStringContainsSpecialCharacter&& isAlphabetPattern&&isStringContainsNumber) {
			log.info("END - Store Id validation]");
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
