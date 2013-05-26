package com.forged.co_living.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class TestServlet extends BaseServlet {

	private String uid;
	private String pwd;
	private ByteBuffer data;
	private Exception ex;
	private Hashtable<String, Serializable> dataFromServlet;

	public static final String LoginServiceUri = "http://192.168.1.3:8080/admin";
	int NetworkConnectionTimeout_ms = 5000;

	public TestServlet() {
		uid = "ABCEDFG";
		pwd = "poonani";

		//			init();

	}

	private void init() throws IOException {
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put("uid", uid);
		map.put("pwd", pwd);

		HttpParams params = new BasicHttpParams();

		HttpConnectionParams.setStaleCheckingEnabled(params, false);
		HttpConnectionParams.setConnectionTimeout(params, NetworkConnectionTimeout_ms);
		HttpConnectionParams.setSoTimeout(params, NetworkConnectionTimeout_ms);
		DefaultHttpClient httpClient = new DefaultHttpClient(params);

		// create post method
		HttpPost postMethod = new HttpPost(LoginServiceUri);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(map);
		ByteArrayEntity req_entity = new ByteArrayEntity(baos.toByteArray());
		req_entity.setContentType(MIMETypeConstantsIF.BINARY_TYPE);

		// associating entity with method
		postMethod.setEntity(req_entity);

		// RESPONSE
		httpClient.execute(postMethod, new ResponseHandler<Void>() {
			public Void handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				HttpEntity resp_entity = response.getEntity();
				if (resp_entity != null) {

					try {
						byte[] data = EntityUtils.toByteArray(resp_entity);
						ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
						dataFromServlet = (Hashtable<String, Serializable>) ois.readObject();

						 Set<String> keySet = dataFromServlet.keySet();

						Log.d(getClass().getSimpleName(), "NCC - data size from servlet=" + data.length);

						for(int i = 0; i < dataFromServlet.keySet().size(); i++) {
							String val = dataFromServlet.get((String) keySet.toArray()[i]).toString();
							Log.d(getClass().getSimpleName(), "NCC - data hashtable from servlet=" + val);
						}
					}
					catch (Exception e) {
						Log.e(getClass().getSimpleName(), "problem processing post response", e);
					}

				}
				else {
					throw new IOException(
							new StringBuffer()
							.append("HTTP response : ").append(response.getStatusLine())
							.toString());
				}
				return null;
			}
		});
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
