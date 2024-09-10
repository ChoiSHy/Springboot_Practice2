package kr.co.hanbit.productmanagement.domain;

import lombok.Getter;
@Getter
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Product(String name, Integer price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public void setId(Long id){this.id=id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
