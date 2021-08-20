package model;

public class Umlaute {

	public static String umlauteUmwandeln(String input) {
		String temp = input.replace("ä", "ae");
		temp = temp.replace("Ä", "Ae");
		temp = temp.replace("ö", "oe");
		temp = temp.replace("Ö", "Oe");
		temp = temp.replace("ü", "ue");
		temp = temp.replace("Ü", "Ue");
		temp = temp.replace("ß", "ss");
		
		return temp;
	}
	
	
	//Test
	
	public static void main(String [] args) {
		System.out.println(umlauteUmwandeln("Höllß äÄa"));
	}
}
