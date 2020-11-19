package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Builder
@Setter
@Getter
public class Butler {
    Job job;
    String name = "generic";
    public void executeJob(){
        System.out.println(name+" butler starting");
        job.startNode.execute(new HashMap<>());
    }
}
