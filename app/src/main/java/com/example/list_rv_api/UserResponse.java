package com.example.list_rv_api;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("id")
	private String id;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}