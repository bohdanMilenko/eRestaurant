package com.application.util.jsonSerializer;

import com.application.entity.MenuItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MenuItemSerializer extends StdSerializer<MenuItem> {

    public MenuItemSerializer() {
        this(MenuItem.class);
    }

    public MenuItemSerializer(Class<MenuItem> t) {
        super(t);
    }

    @Override
    public void serialize(MenuItem menuItem, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("menuItemId", String.valueOf(menuItem.getMenuItemId()));
        jsonGenerator.writeStringField("menuItemName", menuItem.getMenuItemName());
        jsonGenerator.writeStringField("isCurrentlyAvailable", String.valueOf(menuItem.isCurrentlyAvailable()));
        jsonGenerator.writeStringField("description", menuItem.getDescription());
        jsonGenerator.writeStringField("calories", String.valueOf(menuItem.getDescription()));
        jsonGenerator.writeStringField("prepTime", String.valueOf(menuItem.getPrepTime()));
        jsonGenerator.writeStringField("imageLink", menuItem.getImageLink());

        jsonGenerator.writeObjectFieldStart("menuCategory");
        jsonGenerator.writeStringField("menuItemCategoryId", String.valueOf(menuItem.getMenuCategory().getMenuItemCategoryId()));
        jsonGenerator.writeStringField("categoryName", menuItem.getMenuCategory().getCategoryName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();

    }
}
