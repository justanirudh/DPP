package competitions.insight;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by paanir on 7/4/17.
 */
public class Purchase {
    @JsonProperty("event_type")
    public String eventType;

    @JsonProperty("timestamp")
    public String timestamp;

    @JsonProperty("id")
    public String id;

    @JsonProperty("amount")
    public String amount;
}
