package com.jaque.httptools;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class httpRequestUtils {

	public static void main(String[] args) throws Exception {
		get("http://www.baidu.com");
	}
	
	
	
	public static String get(String url) throws Exception {
		 String responseBody = "";
		 try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
	             HttpGet httpget = new HttpGet(url);

	            System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getUri());

	            // Create a custom response handler
	            final HttpClientResponseHandler<String> responseHandler = new HttpClientResponseHandler<String>() {
	                @Override
	                public String handleResponse(
	                        final ClassicHttpResponse response) throws IOException {
	                    final int status = response.getCode();
	                    if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
	                        final HttpEntity entity = response.getEntity();
	                        try {
	                            return entity != null ? EntityUtils.toString(entity) : null;
	                        } catch (final ParseException ex) {
	                            throw new ClientProtocolException(ex);
	                        }
	                    } else {
	                        throw new ClientProtocolException("Unexpected response status: " + status);
	                    }
	                }

	            };
	            responseBody = httpclient.execute(httpget, responseHandler);
	            System.out.println("----------------------------------------");
	            System.out.println(responseBody);
	        }
		 return responseBody;
	}
	
}
