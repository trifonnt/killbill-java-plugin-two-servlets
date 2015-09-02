/*
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014 The Billing Project, LLC
 *
 * Ning licenses this file to you under the Apache License, version 2.0
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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.log.LogService;

public class ServletTwo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final LogService logService;

	public ServletTwo(final LogService logService) {
		this.logService = logService;
	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) 
		throws ServletException, IOException 
	{
		// Find me on http://127.0.0.1:8080/plugins/killbill-two-servlets/two
		logService.log(LogService.LOG_INFO, "Hello from Servlet Two");

		// Set response content type
		resp.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello from Servlet Two!</h1>");
	}
}