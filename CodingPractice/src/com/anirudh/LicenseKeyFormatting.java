package com.anirudh;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by paanir on 5/3/17.
 */
//#482
//under-construction
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        String[] woDashRevGroups =
                (new StringBuilder(StringUtils.join(S.split("-"))).reverse().toString()).split("(?<=\\G.{"+K+"})");

        Collections.reverse(Arrays.asList(woDashRevGroups));
        return String.join("-", woDashRevGroups).toLowerCase();

    }
}
