package com.posm.util;
/**
 * 	����Ϊ��װ�̳� ��ѯ�Ͱ������ѯ��Ʒ�� ��չʾ��������Ʒ
 * @author fjw
 *
 */
public class Store {
	private String queryText;
	private Integer id;
	private String hot;
	private String special;
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	@Override
	public String toString() {
		return "Store [queryText=" + queryText + ", id=" + id + ", hot=" + hot + ", special=" + special + "]";
	}	
}
