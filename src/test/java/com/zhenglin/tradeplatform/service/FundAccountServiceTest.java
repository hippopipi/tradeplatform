package com.zhenglin.tradeplatform.service;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.zhenglin.tradeplatform.grpc.fundaccount.CheckFundAccountRequest;
import com.zhenglin.tradeplatform.grpc.fundaccount.CheckFundAccountResponse;
import com.zhenglin.tradeplatform.grpc.fundaccount.FundAccountServiceGrpc;


/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class FundAccountServiceTest {
  
  public static void main(String[] args) throws Exception {
	  String host="localhost";
	  int port=9898;
	  
	  String userName = "11";
      String password = "world";
      ManagedChannel channel=null;
	    try {
	    	
	    	 channel=ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
	    	FundAccountServiceGrpc.FundAccountServiceBlockingStub blockingStub = FundAccountServiceGrpc.newBlockingStub(channel);
	   	    CheckFundAccountRequest request = CheckFundAccountRequest.newBuilder().setFundAccountName(userName).setPassword(password).build();
	   	    System.out.println(request);
	   	    CheckFundAccountResponse response = blockingStub.checkFundAccount(request);
	   	    
	   	    System.out.println(response);
	      
	    } catch (Exception e) {
	      
	      e.printStackTrace();
	    }finally {
	    	channel.shutdown();
	    }

	 
  }
}

