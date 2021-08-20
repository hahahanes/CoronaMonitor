package model;


import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.AgsListe.StadtNichtGefundenAusnahme;


public class Destination implements Serializable {
	String ort;
	String agm;
	//double distanz;
	double inzidenz;
	// String wetter;
	
	public Destination(String ort) throws StadtNichtGefundenAusnahme {
		this.ort= ort;
		this.agm = AgsListe.getAGS(ort);
		updateInzidenz(agm);
	}
	
	

	
	public String getOrt() {
		return ort;
	}


	public void setOrt(String ort) {
		this.ort = ort;
	}


	public double getInzidenz() {
		return inzidenz;
	}


	public void setInzidenz(double inzidenz) {
		this.inzidenz = inzidenz;
	}


	/*
	 * Methode zum aktualisieren des Wetters 
	 * 
	 * !! API Key fehlt!!
	 */
//	public static void updateWetter() throws IOException, InterruptedException {
//		
//		
//		//Build String
//				String url = "api.openweathermap.org/data/2.5/forecast?q=Berlin,DE&appid=";
//				
//				//Http connection
//				HttpClient client = HttpClient.newHttpClient();
//				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
//						url)).build();
//				
//				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//					
//				//Umwandlung in Zahl
//				String  longString = response.body();
//				
//				
//				
//				
//				
//				
//				
//					
//				System.out.println(longString);
//				
//		
//		
//		
//	}
	
	/*
	 * Methode zum Ermitteln der aktuellen Inzidenz
	 * 
	 * @param agm Übergabe Allgemeiner Gemeindeschlüssel
	 */
	public void updateInzidenz(String agm){
		
		String url = buildURL(agm);
		HttpResponse<String> apiResponse = callAPI(url);
		extractInzidenzFromAPIResponse(apiResponse);
			
	}
	
	public void extractInzidenzFromAPIResponse (HttpResponse<String> response) {
		String [] seperatedJSON = response.body().split(",");
		
		for (String str : seperatedJSON) {
			if ( str.contains("weekIncidence")) {
				inzidenz = Math.round(Double.parseDouble(str.substring(16))*10)/10.0;
			}
		}
	}
	
	public String buildURL ( String agm) {
		
		//Berlin requires different API call
		if(agm.equals("11000000")) return "https://api.corona-zahlen.org/states/BE";
		
		return "https://api.corona-zahlen.org/districts/" + agm.substring(0, 5);
	}
	
	public HttpResponse<String> callAPI (String url){
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				url)).build();
	
		try {
			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

	
	
	//Test
	public static void main (String[] args) {
		AgsListe.readStadtListe();
		
		Destination test;
		try {
			test = new Destination("Berlin");
		

			System.out.println(test.getOrt());
			System.out.println(test.agm);
			System.out.println(test.getInzidenz());
		} catch (StadtNichtGefundenAusnahme e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


///*
//* Methode zum erfassen der Distanz zwischen Berlin und Zielort
//*/
//public static void updateDistanz() throws IOException, InterruptedException {
//	
//	//Build String
//	String url = "http://www.distance24.org/route.json?stops=Hamburg|Berlin " ;
//	
//	//Http connection
//	HttpClient client = HttpClient.newHttpClient();
//	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
//			url)).build();
//	
//	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		
//	//Umwandlung in Zahl
//	String  longString = response.body();
//	
//	String [] split = longString.split(",");
//	
//	
//	
//	for (String str : split) {
//		if ( str.contains("weekIncidence")) {
//			inzidenz = Math.round(Double.parseDouble(str.substring(16))*10)/10.0;
//		}
//	}
//	 
//	
//	
//		
//	System.out.println(longString);
//	
//	
//}
