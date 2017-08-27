package amar.rx.jdbcInteraction.dto;

import amar.rx.jdbcInteraction.dto.Customer;

import java.util.List;

/**
 * Created by amarendra on 22/10/16.
 */
public class Product extends CustomerRelatedData{

    private Long id;
    private String name;

    public Product(long customerId,Long id , String name) {
        super(customerId);
        this.id = id;
        this.name = name;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}