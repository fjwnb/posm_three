package com.posm.Shopp;

import com.posm.bean.Product;

public class CartItem {
	//��Ʒ����
    private Product product; 

    //����
    private Integer count =1 ; 
    
    //С��
    private Double subtotal ;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return count*product.getShop_price();
    }

}
