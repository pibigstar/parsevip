package com.pibigstar.parsevip.bean;

public class Song {
	private String songname;
	private String singername;
	private String hash;
	private String download;


	
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getSingername() {
		return singername;
	}
	public void setSingername(String singername) {
		this.singername = singername;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	@Override
	public String toString() {
		return "Song [songname=" + songname + ", singername=" + singername + ", hash=" + hash + ", download=" + download
				+ "]";
	}
	
}
