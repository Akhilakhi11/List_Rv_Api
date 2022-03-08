package com.example.list_rv_api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductListresponse{


	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;


	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}



	public static class DataItem implements Parcelable {

		@SerializedName("last_name")
		private String lastName;

		@SerializedName("id")
		private int id;

		@SerializedName("avatar")
		private String avatar;

		@SerializedName("first_name")
		private String firstName;

		@SerializedName("email")
		private String email;

		protected DataItem(Parcel in) {
			lastName = in.readString();
			id = in.readInt();
			avatar = in.readString();
			firstName = in.readString();
			email = in.readString();
		}

		public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
			@Override
			public DataItem createFromParcel(Parcel in) {
				return new DataItem(in);
			}

			@Override
			public DataItem[] newArray(int size) {
				return new DataItem[size];
			}
		};

		public void setLastName(String lastName){
			this.lastName = lastName;
		}

		public String getLastName(){
			return lastName;
		}

		public void setId(int id){
			this.id = id;
		}

		public int getId(){
			return id;
		}

		public void setAvatar(String avatar){
			this.avatar = avatar;
		}

		public String getAvatar(){
			return avatar;
		}

		public void setFirstName(String firstName){
			this.firstName = firstName;
		}

		public String getFirstName(){
			return firstName;
		}

		public void setEmail(String email){
			this.email = email;
		}

		public String getEmail(){
			return email;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeString(lastName);
			parcel.writeInt(id);
			parcel.writeString(avatar);
			parcel.writeString(firstName);
			parcel.writeString(email);
		}
	}
}