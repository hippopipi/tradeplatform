package com.zhenglin.tradeplatform.service;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


import com.zhenglin.tradeplatform.grpc.user.CheckUserRequest;
import com.zhenglin.tradeplatform.grpc.user.CheckUserResponse;
import com.zhenglin.tradeplatform.grpc.user.UserServiceGrpc;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class UserServiceTest {
  
  public static void main(String[] args) throws Exception {
	  String host="localhost";
	  int port=9898;
	  
	  String userName = "11";
      String password = "world";
      ManagedChannel channel=null;
	    try {
	    	
	    	  channel=ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
	   	    UserServiceGrpc.UserServiceBlockingStub blockingStub = UserServiceGrpc.newBlockingStub(channel);
	   	    CheckUserRequest request = CheckUserRequest.newBuilder().setUserName(userName).setPassword(password).build();
	   	 System.out.println(request);
	   	    CheckUserResponse response = blockingStub.checkUser(request);
	   	    
	   	    System.out.println(response.getFlag()+""+response.getMessage());
	      
	    } catch (Exception e) {
	      
	      e.printStackTrace();
	    }finally {
	    	channel.shutdown();
	    }
	 
  }
}

