package com.application.util.jsonSerializer;

import com.application.entity.Order;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(Order.class);
    }

    public OrderSerializer(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("orderId", String.valueOf(order.getOrderId()));
        jsonGenerator.writeStringField("orderedTime", String.valueOf(order.getOrderedTime()));
        jsonGenerator.writeStringField("totalAmount", String.valueOf(order.getTotalAmount()));

        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeStringField("userId", String.valueOf(order.getUser().getUserId()));
        jsonGenerator.writeStringField("userName", order.getUser().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("orderStatus");
        jsonGenerator.writeStringField("orderStatusId", String.valueOf(order.getOrderStatus().getOrderStatusId()));
        jsonGenerator.writeStringField("orderStatusName", (order.getOrderStatus().getOrderStatusName()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("address");
        jsonGenerator.writeStringField("addressId", String.valueOf(order.getAddress().getAddressId()));
        jsonGenerator.writeStringField("addressLine1", order.getAddress().getAddressLine1());
        jsonGenerator.writeStringField("addressLine2", order.getAddress().getAddressLine2());
        jsonGenerator.writeStringField("provinceId", String.valueOf(order.getAddress().getProvince().getProvinceId()));
        jsonGenerator.writeStringField("abbreviationProvince", order.getAddress().getProvince().getAbbreviationProvince());
        jsonGenerator.writeStringField("city", order.getAddress().getCity());
        jsonGenerator.writeStringField("postalCode", order.getAddress().getPostalCode());
        jsonGenerator.writeStringField("country", order.getAddress().getCountry());
        jsonGenerator.writeStringField("deliveryNotes", order.getAddress().getDeliveryNotes());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();

    }


}
