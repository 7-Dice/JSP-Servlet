/*
 *  Beans使用し、商品を自分で登録しそれを表示させるプログラムを作る
 *  I/OはJSP、処理をサーブレットで行う
 *  登録情報は以下のフィールドの通り
 *  JSPx2、Servletx1、Beansは専用フォルダに仕分け
 *  References:[register.jsp][mgServlet.java][regitered.jsp]
 */

package Beans.web;

import java.io.Serializable;

public class ProductsBeans implements Serializable
{
	private String name;
	private String code;
	private int price;
	private String maker;
	private String size;
	//private String option;
	
	
	//もし特典の有無をBooleanにするなら
	
	private boolean option;
	
	public boolean isOption(){
		return this.option;
		}
	
	public void setOption(boolean option){
	 	this.option = option;
	 	}
	
	/*
	public String getOption() {
		return option;
	}
	
	public void setOption(String option) {
		this.option = option;
	}
	*/
	
	// 出品者情報表示用のフィールド、Marchadise has a Seller
		private String seller;
		private String sellerId;
		
		public String getSeller() {
			return this.seller;
		}
		
		public void setSeller(String seller) {
			this.seller = seller;
		}
		
		public String getSellerId() {
			return this.sellerId;
		}
		
		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}

