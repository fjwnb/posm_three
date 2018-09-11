package com.posm.Shopp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//存放购物项
    private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();

    public Collection getCartItems(){
        return map.values();
    }
    
   /* public Map<Integer,CartItem> getCartItems(){
        return map;
    }*/
    //总计
    private Double total = 0d ;

    public Double getTotal() {
        return total;
    }

    //将购物项添加到购物车中
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

    //将购物项从购物车中移除
    public void removeCart(Integer id){
        CartItem cartItem = map.remove(id);
        total -= cartItem.getSubtotal();
    }

    //清空购物车
    public void clearCart(){
        map.clear();
        total = 0d;
    }
}
