package com.forged.co_living.client;

import org.apache.http.impl.client.DefaultHttpClient;

import com.forged.co_living.client.servlets.ClientQueryServlet;



public class ConnectionManager {

	public static final String TAG = "ConnectionManager";
	
	public static final int DEFAULT_TIMEOUT = 5000;

	private static ConnectionManager mInstance;

	private static DefaultHttpClient mClient;

	private ConnectionManager() {
		
	}

	public static ConnectionManager getInstance() {
		if(mInstance == null) {
			mInstance = new ConnectionManager();
		}
		return mInstance;
	}

	private void init() {
		try {
			initHttpClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initHttpClient() throws Exception {
		
//		new TestServlet().execute(null, null, null);
		
		new ClientQueryServlet().execute(null, null, null);
		
		
//		mClient = new DefaultHttpClient();
//		 HttpGet httpget = new HttpGet(
//		            "http://www.google.com/search?q=google");
//
//		    HttpResponse response = mClient.execute(httpget);
//
//		    resp.getWriter().println(response.toString());
		
		
//		mClient.setMaxConnectionsPerDestination(10);
//		mClient.setConnectTimeout(30000);
//		mClient.start();

	}

//	public void sendExchange(final HttpExchange exchange) {
//
//		init();
//
//		mClient.newRequest("http://192.168.1.3:8080/admin").send(new Response.CompleteListener() {
//
//			@Override
//			public void onComplete(Result result) {
//				Log.d(TAG, "NCC - GOT RESULT: " + result.toString());
//
//			}
//		});
//
//	}
	
	public void sendExchange() {
		init();
		
		
	}
}
