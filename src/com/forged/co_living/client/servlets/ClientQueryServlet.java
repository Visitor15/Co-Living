package com.forged.co_living.client.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Base64;
import android.util.Log;

import com.forged.co_living.MainActivity;
import com.forged.co_living.client.BaseServlet;
import com.forged.co_living.client.ConnectionManager;
import com.forged.co_living.client.MIMETypeConstantsIF;

public class ClientQueryServlet extends BaseServlet {

	public static final String TAG = "ClientQueryServlet";

	private static final String QUERY_URI = "http://192.168.1.3:8080/query";

	private static final String QUERY_PARAM = "queryParam";

	private static final String TEST_QUERY = "SELECT * FROM users.usernames";

	private Hashtable<String, Serializable> resultsMap;

	private String mQueryString;

	public ClientQueryServlet() {
		resultsMap = new Hashtable<String, Serializable>();
		mQueryString = QUERY_PARAM;
	}

	public void setQueryString(final String query) {
		this.mQueryString = query;
	}

	@Override
	protected Void doInBackground(Void... params) {

		if(this.mQueryString != null) {
			if(mQueryString.trim().length() > 0) {
				try {
					initQuery();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	private void initQuery() throws IOException {
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put("uid", "TEMP_USER");
		map.put("pwd", "TEMP_PASSWORD");
		map.put(QUERY_PARAM, Base64.encodeToString(TEST_QUERY.getBytes(), 0));

		HttpParams params = new BasicHttpParams();

		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, ConnectionManager.DEFAULT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, ConnectionManager.DEFAULT_TIMEOUT);
		DefaultHttpClient httpClient = new DefaultHttpClient(params);

		// create post method
		HttpPost postMethod = new HttpPost(QUERY_URI);

		ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
		ObjectOutputStream objOS = new ObjectOutputStream(byteArrayOS);
		objOS.writeObject(map);
		ByteArrayEntity req_entity = new ByteArrayEntity(byteArrayOS.toByteArray());
		req_entity.setContentType(MIMETypeConstantsIF.BINARY_TYPE);

		// associating entity with method
		postMethod.setEntity(req_entity);

		//Executing post method
		executeHttpClient(httpClient, postMethod);
	}

	private void executeHttpClient(final HttpClient client, HttpPost postMethod) throws ClientProtocolException, IOException {
		Log.d(TAG, "NCC - EXECUTING HTTP CLIENT");
		client.execute(postMethod, new ResponseHandler<Void> () {

			@Override
			public Void handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {

				HttpEntity responseEntity = response.getEntity();
				if(responseEntity != null) {
					try {
						byte[] dataBytes = EntityUtils.toByteArray(responseEntity);
						ObjectInputStream objIS = new ObjectInputStream(new ByteArrayInputStream(dataBytes));

						resultsMap = (Hashtable<String, Serializable>) objIS.readObject();

						writeHashTable(resultsMap);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				return null;
			}



		});
	}

	private void writeHashTable(Hashtable<String, Serializable> resultsMap) {

		ArrayList<String> stringList = new ArrayList<String>();

		Set<String> keySet = resultsMap.keySet();

		for(int i = 0; i < resultsMap.keySet().size(); i++) {

			if(resultsMap.get((String) keySet.toArray()[i]) instanceof ArrayList<?>) {
				stringList = (ArrayList<String>) resultsMap.get((String) keySet.toArray()[i]);
				MainActivity.getInstanceActivity().showResultSet(stringList);
			}

			String val = resultsMap.get((String) keySet.toArray()[i]).toString();

			Log.d(getClass().getSimpleName(), "NCC - data hashtable from servlet = " + val);

		}


	}
}
