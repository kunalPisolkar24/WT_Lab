package com.example.demo2;
import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class Bank {
    private int Amount = 1000;

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public boolean widthDraw(int amount) {
        if(amount > Amount) return false;
        Amount -= amount;
        return true;
    }
}
