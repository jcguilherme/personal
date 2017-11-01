package br.com.prestador.servico.personal.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import br.com.prestador.servico.personal.entity.Usuario;

public class Notificacao {

	public final static String AUTH_KEY_FCM = "AIzaSyAf8vyh19RB4uXewIdyS1sycePjggu3TUA";
//	public final static String API_URL_FCM = "https://personal-561f5.firebaseio.com";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	

	
	public String sendPushNotification(Usuario usr) throws IOException {
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

		json.put("to", usr.getToken().trim());
		JSONObject info = new JSONObject();
		info.put("title", usr.getNome()+" está buscando um Treinador"); // Notification title
		info.put("data", "Estou buscando um treinador para treinar peito"); // Notification
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