/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package com.kurento.tutorial.one2onecall;

import org.kurento.client.factory.KurentoClient;
import org.kurento.client.factory.KurentoClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Video call 1 to 1 demo (main).
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @since 4.3.1
 */
@Configuration
@EnableWebSocket
@EnableAutoConfiguration
public class One2OneCallApp implements WebSocketConfigurer {

	@Bean
	public CallHandler callHandler() {
		return new CallHandler();
	}

	@Bean
	public UserRegistry registry() {
		return new UserRegistry();
	}

	@Bean
	public KurentoClient kurentoClient() {
		return KurentoClientFactory.createKurentoClient();
	}

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(callHandler(), "/call");
	}

	public static void main(String[] args) throws Exception {
		new SpringApplication(One2OneCallApp.class).run(args);
	}

}