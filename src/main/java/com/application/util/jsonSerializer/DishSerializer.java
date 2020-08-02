package com.application.util.jsonSerializer;

import com.application.entity.Dish;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DishSerializer extends StdSerializer<Dish> {

    public DishSerializer(){
        this(Dish.class);
    }

    public DishSerializer(Class<Dish> t) {
        super(t);
    }

    @Override
    public void serialize(Dish dish, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("dishId", String.valueOf(dish.getDishId()));
        jsonGenerator.writeStringField("quantityOrdered",String.valueOf(dish.getQuantityOrdered()));

        jsonGenerator.writeObjectFieldStart("price");
        jsonGenerator.writeStringField("priceId",String.valueOf(dish.getPrice().getPriceId()));
        jsonGenerator.writeStringField("priceValue",String.valueOf(dish.getPrice().getPriceValue()));
        jsonGenerator.writeEndObject();


        jsonGenerator.writeObjectFieldStart("menuItem");
        jsonGenerator.writeStringField("menuItemId",String.valueOf(dish.getMenuItem().getMenuItemId()));
        jsonGenerator.writeStringField("menuItemName",(dish.getMenuItem().getMenuItemName()));
        jsonGenerator.writeStringField("menuItemCategory",String.valueOf(dish.getMenuItem().getMenuCategory().getCategoryName()));
        jsonGenerator.writeStringField("menuItemImageLink",dish.getMenuItem().getImageLink());
        jsonGenerator.writeEndObject();


        jsonGenerator.writeObjectFieldStart("order");
        jsonGenerator.writeStringField("orderId",String.valueOf(dish.getOrder().getOrderId()));
        jsonGenerator.writeStringField("address",dish.getOrder().getAddress().toString());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("dishStatus");
        jsonGenerator.writeStringField("dishStatusId",String.valueOf(dish.getDishStatus().getDishStatusId()));
        jsonGenerator.writeStringField("dishStatusName",dish.getDishStatus().getDishStatusName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("userId",String.valueOf(dish.getOrder().getUser().getUserId()));

        jsonGenerator.writeEndObject();

    }
}
