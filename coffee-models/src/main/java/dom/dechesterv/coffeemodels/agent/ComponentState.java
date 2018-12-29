package dom.dechesterv.coffeemodels.agent;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComponentState {

    private Long id;
    private String name;
    private String host;
    private int port;
    private boolean status;

}
