package com.anirudh.general_algos;


import java.util.Base64;

/**
 * Created by paanir on 4/3/17.
 */
/*
535. Encode and Decode TinyURL
Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it
returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm
should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */
public class EncodeDecodeTinyURL {
    // Encodes a URL to a shortened URL.

    public static String encode(String longUrl) {
        // encode data on your side using BASE64
        byte[] encodedBytes = Base64.getEncoder().encode(longUrl.getBytes());
        return "http://tinyurl.com/" + new String(encodedBytes);
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
        // Decode data on other side, by processing encoded data

        //remove tinyurl prefix;
        String suffix = shortUrl.substring(19);
        byte[] decodedBytes = Base64.getDecoder().decode(suffix);
        return new String(decodedBytes);
    }

    public static void main(String[] args) {
        String url = "http://badge.example.net/beginner.aspx?aftermath=achiever&actor=air";
        System.out.println(encode(url));
        System.out.println(decode(encode(url)));
    }
}
