package uz.greenwhite.webstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    NEW_ORDER("N"), WAITING("W"), SUCCESS("S"), CANCELED("C");

    private final String code;

}
