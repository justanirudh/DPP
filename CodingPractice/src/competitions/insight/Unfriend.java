package competitions.insight;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paanir on 7/4/17.
 */
public class Unfriend {
    @JsonProperty("event_type")
    public String eventType;

    @JsonProperty("timestamp")
    public String timestamp;

    @JsonProperty("id1")
    public String id1;

    @JsonProperty("id2")
    public String id2;
}
