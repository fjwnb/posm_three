package com.posm.Shopp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//��Ź�����
    private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();

    public Collection getCartItems(){
        return map.values();
    }
    
   /* public Map<Integer,CartItem> getCartItems(){
        return map;
    }*/
    //�ܼ�
    private Double total = 0d ;

    public Double getTotal() {
        return total;
    }

    //����������ӵ����ﳵ��
    public void addCart(CartItem cartItem){
        Integer id = cartItem.getProduct().getId();
        if(map.containsKey(id)){
            CartItem _cartItem = map.get(id);
            _cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
        }else{
            map.put(id, cartItem);
        }

        total +=cartItem.getSubtotal();
    }

    //��������ӹ��ﳵ���Ƴ�
    public void removeCart(Integer id){
        CartItem cartItem = map.remove(id);
        total -= cartItem.getSubtotal();
    }

    //��չ��ﳵ
    public void clearCart(){
        map.clear();
        total = 0d;
    }
}
