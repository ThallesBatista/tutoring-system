package br.com.sembous.emconsumerapi.gateway.implementation;

import br.com.sembous.emconsumerapi.gateway.UntilValue;

final class UrlUtil {

	public static String getOneUrl(String baseUrl, Integer id) {
		return baseUrl + "/" + id.toString();
	}
	public static String getOneUrl(String baseUrl, Integer id, UntilValue until) {
		return baseUrl + "/" + id.toString() + "?until=" + until.toString();
	}
	public static String getAllUrl(String baseUrl) {
		return baseUrl;
	}
	public static String getAllUrl(String baseUrl, UntilValue until) {
		return baseUrl + "/getTrees" + "?until=" + until.toString();
	}
	
}
