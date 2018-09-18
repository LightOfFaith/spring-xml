package com.share.lifetime.service;

import java.io.IOException;

import com.share.lifetime.domain.User;

public interface RegistrationService {

	void register(User user) throws IOException;

}
