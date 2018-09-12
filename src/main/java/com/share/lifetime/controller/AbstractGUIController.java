package com.share.lifetime.controller;

import com.share.lifetime.base.Result;

public class AbstractGUIController extends AbstractController {

	@Override
	protected <T> Result<T> success(T result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected <T> Result<T> success(String msg, T result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected <T> Result<T> failure(String msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
