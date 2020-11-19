package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nodes.Node;

@Getter@Setter@Builder
public class Job {
    Node startNode;
}
