package com.anirudh.system_design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//stateful
public class EncodeDecodeTinyURL2 {

    RandomString rsObj;

    public class RandomString {

        private final List<Character> alphabets; //all alphabets

        private final Random random; //create random number

        private HashMap<String, String> map; //save all generated strings
        
        private int urlLen;
        private int alphabetLen;
        
        private List<Character> getAlphabets() {
            List<Character> alphabets = new ArrayList<>();
            for (char ch = '0'; ch <= '9'; ++ch)
                alphabets.add(ch);
            for (char ch = 'a'; ch <= 'z'; ++ch)
                alphabets.add(ch);
            for (char ch = 'A'; ch <= 'Z'; ++ch)
                alphabets.add(ch);
            alphabetLen = alphabets.size();
            return alphabets;
        }

        public RandomString(int length) {
            if (length < 1)
                throw new IllegalArgumentException("length < 1: " + length);
            this.urlLen = length;
            alphabets = getAlphabets();
            random = new Random();
            map = new HashMap<>();
        }

        public String nextString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < urlLen; ++i)
                sb.append(alphabets.get(random.nextInt(alphabetLen)));
            return sb.toString();
        }
    }

    public EncodeDecodeTinyURL2() {
        rsObj = new RandomString(6); //length of hash 6
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String rs = rsObj.nextString();
        if(rsObj.map.containsKey(rs))
            return encode(longUrl);
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
        System.out.println(e.decode(e.encode(url)).equals(url));
    }

}
