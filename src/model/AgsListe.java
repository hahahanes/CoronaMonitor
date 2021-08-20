package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Klasse zum erkennen und Einlesen des Allgemeinen Gemeindeschlüssels
 */

public class AgsListe {

	static ArrayList<AGS> stadtListe = new ArrayList<AGS>();
	
	public static void readStadtListe() {
		String file = "C:\\Users\\goldf\\OneDrive\\Dokumente\\Studium\\Programmieren 1\\Wetter_Corona\\data\\ags.txt";
		
		
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			
			
			while (bReader.ready()) {
				
				//Zeile einlesen und evtl. direkt überspringen
				String line = bReader.readLine();
				if(line.equals("")) continue;
				
				String[] ortUncut = line.split("   "); 	//Temporäres String-Array zum Zwischenspeichern der Ergebnisse
				String[] ortFinal = new String[4];		//Temporäres String-Array enthält Daten zur Umwandlung in Ort
				
				int counter = 0; 						//Counter zum Einlesen der Daten in OrtCut
				
				//Daten in Objekt AGS umwandeln
				for (String s : ortUncut) {
					s= s.trim();
					if (s.equals("")) continue;
					ortFinal [counter]= s;
					counter++;
				}
				
				stadtListe.add(new AGS(Umlaute.umlauteUmwandeln(ortFinal[0]), ortFinal[1], ortFinal[2], ortFinal[3]));
				
			}
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class StadtNichtGefundenAusnahme extends Exception {
		public StadtNichtGefundenAusnahme (String s) {
			super(s);
		}
	}
	
	/*
	 * sucht AGS für einen bestimmten Ort
	 * 
	 * @param String Ort Ortsname
	 * 
	 * @return AGS
	 */
	public static String getAGS (String ort) throws StadtNichtGefundenAusnahme {
		
		//Binary Search
		int low = 0;
		int high = stadtListe.size();
		int timeOutCounter = 0;
		
		System.out.println("Suche: " + Umlaute.umlauteUmwandeln(ort));
		
		while (low<=high) {
			int mid = (low+high)/2;
			
			String tempName = stadtListe.get(mid).getName();
			
			int vergleichsWert = tempName.compareTo(Umlaute.umlauteUmwandeln(ort));
			
			System.out.println(tempName + " low = " + low + " high = " + high + " mid = " + mid + " comp = " + tempName.compareTo(ort) );
			
			if(vergleichsWert ==0) {
				return stadtListe.get(mid).getKey();
			}else if (vergleichsWert > 0){
				high = 1+ mid;
			}else if (vergleichsWert < 0) {
				low = mid;
			}
			
			timeOutCounter++;
			if(timeOutCounter > 50) {
				throw new StadtNichtGefundenAusnahme("Stadt nicht gefunden.");
			}
		}
		
		
		return null;
	}
	
	//zum Testen
	
	public static void main (String[] args) {
		System.out.println("Readist");
		readStadtListe();
		System.out.println("Find Köln");
		try {
			System.out.println(getAGS("Köln"));
		} catch (StadtNichtGefundenAusnahme e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
