package concurrent_programming.beatles;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by anirudh on 25/9/16.
 */
@Immutable //dont know if right immutable
public final class Beatles {
    private final Set<String> beatles = new HashSet<String>();
    public Beatles() {
        beatles.add("Paul");
        beatles.add("John");
        beatles.add("George");
        beatles.add("Ringo");
    }
    public boolean isBeatle(String name) {
        return beatles.contains(name);
    }
}
