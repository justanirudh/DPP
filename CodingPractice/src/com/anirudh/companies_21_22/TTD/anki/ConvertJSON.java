package com.anirudh.companies_21_22.TTD.anki;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class Foo { //need all fields public
    public Integer i;
    public String str;

    public Foo(Integer i, String str) {
        this.i = i;
        this.str = str;
    }
    public Foo() {} // need dummy constructor for deserializing
}

public class ConvertJSON {

    public static void main(String[] args) throws IOException {
        Foo obj = new Foo(1, "foo");
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(obj);
        System.out.println(json);
        Foo obj2 = mapper.readValue(json, Foo.class);
        System.out.println(obj2.i);
        System.out.println(obj2.str);
    }
}
