package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.CustomerDetailsNotFoundException;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepo1;


@Component(value="walletService")
public class WalletServiceImpl implements WalletService
{
	@Autowired
	private WalletRepo repo;
	@Autowired
	private WalletRepo1 repo1;
	Customer customer;
	Transactions trans;

	public WalletServiceImpl() {

	}
	
	
	@Transactional
	@Override
	public Customer createAccount(Customer customer)
	{
		trans=new Transactions(customer.getMobileNo(),"create Account",customer.getWallet().getBalance(),new Date().toString(),"Success");
		repo1.save(trans);
		return repo.save(customer);

	}	
	@Override
	public Customer showBalance(String mobileno) throws CustomerDetailsNotFoundException
	{

		customer=repo.findOne(mobileno);
		if(customer==null )
			throw new CustomerDetailsNotFoundException("Customer details are not found with given mobile no ");
		else	
			return customer;

	}
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InsufficientBalanceException
	{
		Customer customer1=new Customer();
		customer=new Customer();
		Transactions trans2=new Transactions();
		if(sourceMobileNo.equals(targetMobileNo))
		{
			throw new InsufficientBalanceException("Source and target mobile numbers are equal");
		}

		customer=repo.findOne(sourceMobileNo);
		customer1=repo.findOne(targetMobileNo);
		BigDecimal balance1;
		BigDecimal balance2;

		balance1=customer.getWallet().getBalance();
		balance2=customer1.getWallet().getBalance();

		if((balance1.compareTo(amount)) < 0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		
		else {
			balance1=balance1.subtract(amount);
			customer.setWallet(new Wallet(balance1));
			repo.save(customer);
			trans=new Transactions(customer.getMobileNo(),"fund transferred",customer.getWallet().getBalance(),new Date().toString(),"Success");
			repo1.save(trans);


			balance2=balance2.add(amount);	
			customer1.setWallet(new Wallet(balance2));
			repo.save(customer1);
			trans2=new Transactions(customer1.getMobileNo(),"fund received",customer.getWallet().getBalance(),new Date().toString(),"Success");
			repo1.save(trans2);
		}



		

		return customer ;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {

		customer=new Customer();
		trans=new Transactions();

		customer=repo.findOne(mobileNo);

		BigDecimal dbalance;
		dbalance=customer.getWallet().getBalance();
		
		
		dbalance=dbalance.add(amount);
		customer.setWallet(new Wallet(dbalance));
		repo.save(customer);
		trans=new Transactions(customer.getMobileNo(),"deposit",customer.getWallet().getBalance(),new Date().toString(),"Success");
		repo1.save(trans);
		return customer ;

	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InsufficientBalanceException 
	{
		customer=new Customer();
		trans=new Transactions();

		customer=repo.findOne(mobileNo);
		BigDecimal wbalance;
		wbalance=customer.getWallet().getBalance();
		int res = wbalance.compareTo(amount);
		if(res == -1) {
			throw new InsufficientBalanceException("Amount is greater than Balance");
		}
				
		wbalance=wbalance.subtract(amount);
		customer.setWallet(new Wallet(wbalance));
		repo.save(customer);
		trans=new Transactions(customer.getMobileNo(),"Withdraw",customer.getWallet().getBalance(),new Date().toString(),"Success");
		repo1.save(trans);

		return customer ;

	}
	
	@Override
	public ArrayList<Customer> getAllCustomers() {
		List<Customer> customerList = repo.findAll();
		return (ArrayList<Customer>) customerList;
	}
	
	@Override
	public ArrayList<Customer> getCustomerwithMinBalance() {
		List<Customer> custList = repo.findAll();
		ArrayList<Customer> resList = new ArrayList<>();
		int res;
		for(Customer cust:custList) {
			res = (cust.getWallet().getBalance().compareTo(new BigDecimal("1000")));
			if(res == -1) {
				resList.add(cust);
				
			}
		}
		return resList;
	}
	
	
	@Transactional
	public List<Transactions> recentTransactions(String mobileNumber) 
	{
		if(!isPhoneNumbervalid(mobileNumber))
			throw new CustomerDetailsNotFoundException("Invalid mobile number");

		else
		{
			List<Transactions> trans=repo1.findByMobileNumber(mobileNumber);
			if( trans!=null)
				return trans;

			else
				throw new CustomerDetailsNotFoundException("Invalid input");
		}


	}

	public boolean isPhoneNumbervalid( String phoneNumber )
	{
		if(phoneNumber.matches("[1-9][0-9]{9}")) 
		{
			return true;
		}		
		else 
			return false;
	}
	public boolean isNameValid(String name)
	{
		if(name.matches("^[a-zA-Z]{1,30}$"))
		{
			return true;
		}
		else
			return false;
	}
	public boolean isAmountValid(BigDecimal amount)
	{
		int val=amount.compareTo(new BigDecimal("0"));
		if(val==0|| val<0)
			return false;
		return true;
	}
}
