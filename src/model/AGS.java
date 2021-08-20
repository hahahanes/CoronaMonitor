package model;

public class AGS {

	String name;
	String art;
	String key;
	String bundesland;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArt() {
		return art;
	}
	public void setArt(String art) {
		this.art = art;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBundesland() {
		return bundesland;
	}
	public void setBundesland(String bundesland) {
		this.bundesland = bundesland;
	}
	public AGS(String name, String art, String key, String bundesland) {
		super();
		this.name = name;
		this.art = art;
		this.key = key;
		this.bundesland = bundesland;
	}
	@Override
	public String toString() {
		return "AGS [name=" + name + ", art=" + art + ", key=" + key + ", bundesland=" + bundesland + "]";
	}
	
	
}
