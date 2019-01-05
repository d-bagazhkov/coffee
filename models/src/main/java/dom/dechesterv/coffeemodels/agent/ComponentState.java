package dom.dechesterv.coffeemodels.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComponentState {

    private Integer id;
    private String name;
    private String host;
    private int port;
    private Date date;
    @JsonIgnore
    private Date lastStatusChange;


}
