package br.com.prestador.servico.personal.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Notificacao {

	public final static String AUTH_KEY_FCM = "AIzaSyCCyvEpZt1rPF3taUfEsP0bIQgxhipYdcw";
//	public final static String API_URL_FCM = "https://personal-561f5.firebaseio.com";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public void teste() {
		try {
			sendPushNotification("eGhbD4esYKM:APA91bHwPYgHquLBC4u76SybGoRBHKQMk8Sw-aWcNG9Z3w9K5qwYXqgUQozCpBFFdKSLiOy2XfA6qMJU-JHjL6uahGS_u4PB609ApMyejtAolGCuAW24cLIU5HM8hv9TUWXPAdD_U1xC");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String sendPushNotification(String deviceToken) throws IOException {
		String result = "";
		URL url = new URL(API_URL_FCM);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();

		json.put("to", deviceToken.trim());
		JSONObject info = new JSONObject();
		info.put("title", "notification title"); // Notification title
		info.put("data", "teste"); // Notification
									// body
		json.put("notification", info);
		try {
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			result = "OK";
		} catch (Exception e) {
			e.printStackTrace();
			result = "ERROR";
		}
		System.out.println("GCM Notification is sent successfully");

		return result;

	}

	
	
	
	
	
	
	 
	    

}

/*
 * curl --header "Authorization: key=" \ --header
 * Content-Type:"application/json" \ https://fcm.googleapis.com/fcm/send \ -d
 * "{\"registration_ids\":[\"\"]}"
 */