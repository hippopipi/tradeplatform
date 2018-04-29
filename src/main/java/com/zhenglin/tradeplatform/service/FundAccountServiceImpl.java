package com.zhenglin.tradeplatform.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhenglin.tradeplatform.grpc.fundaccount.CheckFundAccountRequest;
import com.zhenglin.tradeplatform.grpc.fundaccount.CheckFundAccountResponse;
import com.zhenglin.tradeplatform.grpc.fundaccount.FundAccountServiceGrpc;
import com.zhenglin.tradeplatform.mapper.UserMapper;
import com.zhenglin.tradeplatform.model.User;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;


@GrpcService(FundAccountServiceGrpc.class)
public class FundAccountServiceImpl extends FundAccountServiceGrpc.FundAccountServiceImplBase {
	 @Autowired
	 private UserMapper userMapper;

    @Override
    public void checkFundAccount(CheckFundAccountRequest request,
            StreamObserver<CheckFundAccountResponse> responseObserver) {
    	
    	String _userName=request.getFundAccountName();
    	String _password=request.getPassword();
    	
    	User user=userMapper.selectByPrimaryKey(Integer.valueOf(_userName));
    	String valid="false";
    	if(user!=null){
    		
    		String password=user.getPassword();
        	
        	if(password!=null&&password.equals(_password)){
        		valid="true";
        	}
    	}
    	
    	
    	CheckFundAccountResponse reply = CheckFundAccountResponse.newBuilder().setMessage("Hello =============> "+valid).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        }
}