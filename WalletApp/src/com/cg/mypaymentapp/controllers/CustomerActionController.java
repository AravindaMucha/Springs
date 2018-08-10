package com.cg.mypaymentapp.controllers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.CustomerDetailsNotFoundException;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerActionController {
	public static String mobileNo;
	@Autowired
	WalletService walletService;

	@RequestMapping(value="/registerCustomer")
	public ModelAndView registerCustomer(@Valid @ModelAttribute("customer")Customer customer,BindingResult result) {
		if(result.hasErrors())
			return new ModelAndView("registrationPage");
		customer=walletService.createAccount(customer);
		return new ModelAndView("registrationSuccessPage", "customer",customer);

	}
	/*@RequestMapping(value="/loginCustomer")
	public ModelAndView loginCustomer(@ModelAttribute("customer") Customer customer) {
		mobileNo=customer.getMobileNo();
		customer=walletService.showBalance(mobileNo);
		return new ModelAndView("loginSuccessPage", "customer",customer);

	}*/

	@RequestMapping(value = "/loginCustomer", method=RequestMethod.POST)
	public ModelAndView loginCustomer(@RequestParam("mobileNo") String mobileNo) {
	
		try {
			Customer customer = walletService.showBalance(mobileNo);
			return new ModelAndView("loginSuccessPage", "customer", customer);
		} catch (CustomerDetailsNotFoundException e) {
			return new ModelAndView("loginPage", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value="/withdrawAmount", method = RequestMethod.POST)
	public ModelAndView withdrawBalance(@ModelAttribute("customer") Customer customer) {
		try {
			Customer customer1=walletService.withdrawAmount(customer.getMobileNo(),customer.getWallet().getBalance());
			return new ModelAndView("currentbalance", "customer",customer1);
		} catch (InsufficientBalanceException e) {
			return new ModelAndView("withdraw", "errorMessage", e.getMessage());
		}

	}

	@RequestMapping(value="/depositAmount", method = RequestMethod.POST)
	public ModelAndView depositBalance(@ModelAttribute("customer")Customer customer) {
		Customer customer1=walletService.depositAmount(customer.getMobileNo(), customer.getWallet().getBalance());
		return new ModelAndView("currentbalance", "customer",customer1);

	}
	
	
	@RequestMapping(value="/fundTransferAction")
	public ModelAndView fundTransfer(@ModelAttribute("customer")Customer customer,@RequestParam("mobileNo1") String source, @RequestParam("mobileNo2") String target,@RequestParam("wallet.balance")BigDecimal amount) {

		try {
			Customer customer1=walletService.fundTransfer(source, target, customer.getWallet().getBalance());
			return new ModelAndView("currentbalance", "customer",customer1);
		} catch (InsufficientBalanceException e) {
			return new ModelAndView("fundTransfer", "errorMessage", e.getMessage());
		}

	}
	
	@RequestMapping(value="/ViewAllCustomer")
	public ModelAndView ViewAllCustomer() {
			ArrayList<Customer> customerlist = walletService.getAllCustomers();
		return new ModelAndView("detailsPage", "customerlist",customerlist);
		
	}
	
	@RequestMapping(value="/ViewCustomerwithMinBalance")
	public ModelAndView ViewCustomerwithMinBalance() {
			ArrayList<Customer> customerlist = walletService.getCustomerwithMinBalance();
		return new ModelAndView("detailsPage", "customerlist",customerlist);
		
	}
	
	@RequestMapping(value="/printTransaction1")
	public ModelAndView printtrans(@RequestParam("mobileNumber")String mobileNumber) {
		List<Transactions> transaction=walletService.recentTransactions(mobileNumber);
		return new ModelAndView("printTransaction","transactions",transaction);
	}

}
