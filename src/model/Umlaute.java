package model;

public class Umlaute {

	public static String umlauteUmwandeln(String input) {
		String temp = input.replace("�", "ae");
		temp = temp.replace("�", "Ae");
		temp = temp.replace("�", "oe");
		temp = temp.replace("�", "Oe");
		temp = temp.replace("�", "ue");
		temp = temp.replace("�", "Ue");
		temp = temp.replace("�", "ss");
		
		return temp;
	}
	
	
	//Test
	
	public static void main(String [] args) {
		System.out.println(umlauteUmwandeln("H�ll� ��a"));
	}
}
