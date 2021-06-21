package com.example.github.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class License{

	@SerializedName("name")
	private String name;

	@SerializedName("spdx_id")
	private String spdxId;

	@SerializedName("key")
	private String key;

	@SerializedName("url")
	private String url;

	@SerializedName("node_id")
	private String nodeId;

	public String getName(){
		return name;
	}

	public String getSpdxId(){
		return spdxId;
	}

	public String getKey(){
		return key;
	}

	public String getUrl(){
		return url;
	}

	public String getNodeId(){
		return nodeId;
	}


}