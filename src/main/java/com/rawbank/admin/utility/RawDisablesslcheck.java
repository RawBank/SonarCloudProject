package com.rawbank.admin.utility;
import java.security.*;
import java.security.cert.X509Certificate;
 
import javax.net.ssl.*;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class RawDisablesslcheck {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(RawDisablesslcheck.class);
 
	public static void disableSslVerification() {
		try {
			TrustManager[] trustAllCerts = { new X509TrustManager() {
 
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
 
				public void checkClientTrusted(X509Certificate[] ax509certificate, String s) {
				}
 
				public void checkServerTrusted(X509Certificate[] ax509certificate, String s) {
				}
 
			} };
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
 
				}
 
			};
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
 
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			LOGGER.error("Error while encrypting:  {}", e.toString());
		}
	}
 
}