package uz.greenwhite.webstore.dto;

import uz.greenwhite.webstore.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private ArrayList<Cart> cartList;

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Cart> cartList) {
        this.cartList = cartList;
    }
}
