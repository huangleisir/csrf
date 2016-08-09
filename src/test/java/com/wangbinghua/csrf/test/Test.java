package com.wangbinghua.csrf.test;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gary on 16-8-8.
 */
public class Test {

    class Person{
        int id;
        String name;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @org.junit.Test
    public void testMapRef(){
        List<Person> personList = new LinkedList<Person>();
        Person person = new Person();
        personList.add(person);
        person.id = 12;
        person.name = "wangbinghua";
        System.out.print(personList);
    }

    @org.junit.Test
    public void testRandom(){
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] randoms = new byte[100];
            secureRandom.nextBytes(randoms);
            String token = new BASE64Encoder().encode(randoms);
            System.out.println(token);
            token = token.replace("/","_");
            System.out.println(token);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void testBase64() throws IOException {
        String base = new BASE64Encoder().encode("wangbinghuaaiyaa1234".getBytes());
        System.out.println(base);
    }
}
