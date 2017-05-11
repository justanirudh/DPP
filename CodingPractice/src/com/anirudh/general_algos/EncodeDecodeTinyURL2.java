package com.anirudh.general_algos;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by paanir on 4/4/17.
 */
/*
535-2. Encode and Decode TinyURL
Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it
returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm
should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeDecodeTinyURL2 {

    RandomString rsObj;

    public class RandomString {

        private final char[] symbols;

        private final Random random;

        private final char[] buf;

        HashMap<String, String> map;

        public RandomString(int length) {
            if (length < 1)
                throw new IllegalArgumentException("length < 1: " + length);
            buf = new char[length];
            StringBuilder tmp = new StringBuilder();
            for (char ch = '0'; ch <= '9'; ++ch)
                tmp.append(ch);
            for (char ch = 'a'; ch <= 'z'; ++ch)
                tmp.append(ch);
            symbols = tmp.toString().toCharArray();
            random = new Random();
            map = new HashMap<>();
        }

        public String nextString() {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
            return new String(buf);
        }
    }

    public EncodeDecodeTinyURL2() {
        rsObj = new RandomString(6); //length of hash 6
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String rs = rsObj.nextString();
        rsObj.map.put(rs, longUrl);
        return "http://tinyurl.com/" + rs;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String suffix = shortUrl.substring(19);
        return rsObj.map.get(suffix);
    }

    public static void main(String[] args) {
        EncodeDecodeTinyURL2 e = new EncodeDecodeTinyURL2();
        String url = "http://badge.example.net/beginner.aspx?aftermath=achiever&actor=air";
        System.out.println(e.encode(url));
        System.out.println(e.decode(e.encode(url)));
    }

}
