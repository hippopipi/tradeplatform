package com.zhenglin.tradeplatform.service;

import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhenglin.tradeplatform.grpc.user.CheckUserRequest;
import com.zhenglin.tradeplatform.grpc.user.CheckUserResponse;
import com.zhenglin.tradeplatform.grpc.user.UserServiceGrpc;
import com.zhenglin.tradeplatform.mapper.UserMapper;
import com.zhenglin.tradeplatform.model.User;

import io.grpc.stub.StreamObserver;

@GrpcService(UserServiceGrpc.class)
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void checkUser(CheckUserRequest request, StreamObserver<CheckUserResponse> responseObserver) {

		String _userName = request.getUserName();
		String _password = request.getPassword();

		User user = userMapper.selectByPrimaryKey(Integer.valueOf(_userName));
		String message = "登录失败";
		boolean flag=false;
		if (user != null) {

			String password = user.getPassword();

			if (password != null && password.equals(_password)) {
				flag=true;
				message = "登录成功";
			}
		}

		CheckUserResponse reply = CheckUserResponse.newBuilder().setFlag(flag).setMessage(message).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
