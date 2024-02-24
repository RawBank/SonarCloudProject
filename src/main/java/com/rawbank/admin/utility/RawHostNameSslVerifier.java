/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				Sep 15, 2022
 * Fixed critical SonarQube Issues.
 * 
 * Krishna				Jul 1, 2022
 * CSC admin portal Project.
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 1, 2022
 * 
 */
package com.rawbank.admin.utility;

import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class RawHostNameSslVerifier {

	private RawHostNameSslVerifier() {
	    throw new IllegalStateException("Utility class");
	  }

	private static final Logger LOGGER = LoggerFactory.getLogger(RawHostNameSslVerifier.class);
	
	// Set the HostnameVerifier
	public static void sslCheckSetHostname(String authHostName) {
		try {
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
				public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
					return hostname.equals(authHostName);
				}
			});
		} catch (Exception e) {
			LOGGER.error("Error in changeCardstatusPage {}: " , e.getMessage());
		}
	}
	
	/* Set Disable Host Verification (For Tests & Internal Modules) */
	 /*https://stackoverflow.com/questions/42806709/how-to-bypass-ssl-certificate-checking-in-java 
	  * change X509TrustManager to   X509ExtendedTrustManager   */
	public static void disableSslVerification() {

		try {  //X509ExtendedTrustManager 
			// Create a trust manager that does not validate certificate chains X509TrustManager
			TrustManager[] trustAllCerts = new TrustManager[] {  new X509ExtendedTrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
					 throw new UnsupportedOperationException(" UnsupportedOperationException  01");
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
					 throw new UnsupportedOperationException(" UnsupportedOperationException  02");
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1, Socket arg2)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1, SSLEngine arg2)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1, Socket arg2)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1, SSLEngine arg2)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			} };

			// Install the all-trusting trust manager /TLSv1.2
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					 return hostname.equals(session.getPeerHost());
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			LOGGER.error("Error in disableSslVerification {}: " , e.getMessage());
		} 
	}

}



//SSLContextBuilder builder = new SSLContextBuilder();
//builder.loadTrustMaterial(null, new TrustStrategy() {
//     public boolean isTrusted(final X509Certificate[] chain, String authType) throws CertificateException {
//          return true;
//     }
//});
//SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
//
//CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
