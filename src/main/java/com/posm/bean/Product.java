package com.posm.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Product {
	private Integer id;//��Ʒid;
	private String name;//��Ʒ����
	private double market_price;//�г���
	private double shop_price;//�ۼ�
	private String image;//ͼƬ
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date pdate;//��Ʒ����
	private Integer is_hot;//�Ƿ�����
	private Integer flag;//�Ƿ��¼� 1��2��
	private String pdesc;//��Ʒ����
	private String cname;//��������
	private Integer cid;
	private MultipartFile file;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", market_price=" + market_price + ", shop_price=" + shop_price
				+ ", image=" + image + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc + ", cname="
				+ cname + ", cid=" + cid + ", file=" + file + "]";
	}
	
}
