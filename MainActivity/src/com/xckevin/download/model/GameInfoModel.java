package com.xckevin.download.model;

import android.graphics.drawable.Drawable;

/**
 * 
 * @author Houny Chang
 * 
 */
public class GameInfoModel {
	private String id;
	private String name;
	private Drawable ico;
	private String packageName;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getIco() {
		return ico;
	}

	public void setIco(Drawable ico) {
		this.ico = ico;
	}
	
	

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "GameInfoModel [name=" + name + ", packageName=" + packageName
				+ "]";
	}

	

}
