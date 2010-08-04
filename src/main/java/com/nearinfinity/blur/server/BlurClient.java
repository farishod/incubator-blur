package com.nearinfinity.blur.server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.codehaus.jackson.map.ObjectMapper;

public class BlurClient {

	private static final BlurHits EMTPY_HITS = new BlurHits();;

	private HttpClient httpclient;
	private String scheme = "http";
	private String host;
	private int port;
	private ObjectMapper mapper = new ObjectMapper();
	
	public BlurClient(String connectionStrings) {
		setupConnections(connectionStrings);
		createHttpClient();
	}

	private void setupConnections(String connectionStrings) {
		//@todo
		String[] hosts = connectionStrings.split(",");
		for (String str : hosts) {
			String[] parts = str.split(":");
			host = parts[0];
			port = Integer.parseInt(parts[1]);
		}
	}

	private void createHttpClient() {
		HttpParams params = new BasicHttpParams();
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	    HttpProtocolParams.setContentCharset(params, "UTF-8");
	    SchemeRegistry registry = new SchemeRegistry();
	    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	    ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(params, registry);
		httpclient = new DefaultHttpClient(manager, params);		
	}

	public static void main(String[] args) throws IOException {
		BlurClient blurClient = new BlurClient(args[0]);
		System.out.println(blurClient.search("test", "test.test:value", "", 0, 10));
		BlurHits blurHits = null;
		long totalHits = 0;
		long totalTime = 0;
		int count = 0;
		int max = 25;
		while (true) {
			if (count >= max) {
				System.out.println("hits: " + totalHits + " at " + totalTime / 25.0);
				count = 0;
				totalHits = 0;
				totalTime = 0;
			}
			long s = System.currentTimeMillis();
			blurHits = blurClient.search("test", "test.test:value", "", 0, 10);
			long e = System.currentTimeMillis();
			totalHits += blurHits.getTotalHits();
			totalTime += (e-s);
			count++;
		}
		
	}

	public BlurHits search(String table, String query, String filter, long start, int fetchCount) throws IOException {
		URI uri;
		try {
			uri = URIUtils.createURI(scheme, host, port, getPath(table), getQuery(query, filter, start, fetchCount), null);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		HttpGet get = new HttpGet(uri);
		try {
//			long s = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(get,new BasicHttpContext());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					return mapper.readValue(entity.getContent(), BlurHits.class);
				} finally {
//					System.out.println((System.currentTimeMillis() - s));
				}
			}
		} catch (Exception e) {
			get.abort();
		}
		return EMTPY_HITS;
	}

	private String getQuery(String query, String filter, long start, int fetchCount) {
		return "q=" + query + "&f=" + filter + "&s=" + start + "&c=" + fetchCount;
	}

	private String getPath(String table) {
		return "/" + table;
	}

	public long searchFast(String table, String query, String filter, long minimum) {
		return 0;
	}

}
