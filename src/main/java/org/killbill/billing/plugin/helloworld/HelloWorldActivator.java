/*
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014-2015 Groupon, Inc
 * Copyright 2014-2015 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.plugin.helloworld;

import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

import org.killbill.billing.osgi.api.OSGIPluginProperties;
import org.killbill.billing.payment.plugin.api.PaymentPluginApi;
import org.killbill.killbill.osgi.libs.killbill.KillbillActivatorBase;
import org.killbill.killbill.osgi.libs.killbill.OSGIKillbillEventDispatcher.OSGIKillbillEventHandler;
import org.osgi.framework.BundleContext;

public class HelloWorldActivator extends KillbillActivatorBase {

	public static final String PLUGIN_NAME = "killbill-two-servlets";

	private OSGIKillbillEventHandler analyticsListener;

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);

		// Register an event listener (optional)
		analyticsListener = new HelloWorldListener(logService, killbillAPI);
		dispatcher.registerEventHandler(analyticsListener);

		// Register a payment plugin api (optional)
		final PaymentPluginApi paymentPluginApi = new HelloWorldPaymentPluginApi(configProperties.getProperties(), logService);
		registerPaymentPluginApi(context, paymentPluginApi);

		// Register a servlet (optional)
		final ServletOne servletOne = new ServletOne(logService);
		registerServlet(context, servletOne, "_one");

		final ServletTwo servletTwo = new ServletTwo(logService);
		registerServlet(context, servletTwo, "_two");
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);

		// Do additional work on shutdown (optional)
	}

	@Override
	public OSGIKillbillEventHandler getOSGIKillbillEventHandler() {
		return analyticsListener;
	}

	private void registerServlet(final BundleContext context, final HttpServlet servlet, String subPath) {
		final Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME + subPath);
		registrar.registerService(context, Servlet.class, servlet, props);
	}

	private void registerPaymentPluginApi(final BundleContext context, final PaymentPluginApi api) {
		final Hashtable<String, String> props = new Hashtable<String, String>();
		props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
		registrar.registerService(context, PaymentPluginApi.class, api, props);
	}
}