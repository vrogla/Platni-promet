package com.iktpreobuka.platni_promet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.platni_promet.entities.AccountEntity;
import com.iktpreobuka.platni_promet.entities.ClientEntity;
import com.iktpreobuka.platni_promet.repositories.AccountRepository;
import com.iktpreobuka.platni_promet.repositories.ClientRepository;

@Service
public class AccountServiceImpl implements AccountService {

	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	@Override
	public ResponseEntity<?> addNewAccount( Long accountNumber, Double balance, Integer clientId ) {
		AccountEntity account = new AccountEntity();
		ClientEntity client = clientRepository.findOne(clientId);
		if (((accountNumber == null) || (balance == null)) || (clientId == null)){
			String poruka = "Jedan od parametara nije popunjen! Molimo Vas, pregledajte unete parametre. Hvala na razumevanju.";
			return new ResponseEntity<String>(poruka, HttpStatus.BAD_REQUEST);
		}
		
		account.setAccountNumber(accountNumber);
		account.setBalance(balance);
		account.setClient(client);
		if (client == null) {
			String poruka = "Klijent ne postoji. Molim Vas, unesite odgovarajućeg klijenta. Hvala na razumevanju.";
			return new ResponseEntity<String>(poruka, HttpStatus.NOT_FOUND);
		}
		
		accountRepository.save(account);
		return new ResponseEntity<AccountEntity>(account, HttpStatus.OK);

	}
	


}
