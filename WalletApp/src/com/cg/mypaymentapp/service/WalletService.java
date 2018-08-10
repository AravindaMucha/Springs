
//WalletService Interface

package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.CustomerDetailsNotFoundException;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;


public interface WalletService 
{

	public Customer createAccount(Customer customer);

	public Customer showBalance (String mobileno) throws CustomerDetailsNotFoundException;

	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InsufficientBalanceException;

	public Customer depositAmount (String mobileNo,BigDecimal amount );

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException;
	
	public ArrayList<Customer> getAllCustomers();
	
	public ArrayList<Customer> getCustomerwithMinBalance();
	
	public List<Transactions> recentTransactions(String mobileNumber);
	

}
