package com.application.util.jsonSerializer;

import com.application.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(User.class);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("userId", String.valueOf(user.getUserId()));
        jsonGenerator.writeStringField("name", (user.getName()));
        jsonGenerator.writeStringField("lastName", (user.getLastName()));
        jsonGenerator.writeStringField("email", (user.getEmail()));
        jsonGenerator.writeStringField("birthDate", (user.getBirthDate().toString()));
        jsonGenerator.writeStringField("phoneNumber", (user.getPhoneNumber()));
        jsonGenerator.writeStringField("accountCreationDateTime", (user.getAccountCreationDateTime().toString()));

        jsonGenerator.writeEndObject();

    }
}
