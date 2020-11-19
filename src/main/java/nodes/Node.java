package nodes;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static utils.Constants.OUTPUT;

@Getter
@Setter
public abstract class Node implements Runnable{
    Set<Node> incomingNodes = new HashSet<>();
    Set<Node> outgoingNodes = new HashSet<>();
    Map<String, Object> nodeOutput = new HashMap<>();
    Map<String, Object> nodeInput = new HashMap<>();

    public static synchronized void postProcessing(Node node){

        if (node.getOutgoingNodes() == null){
            return;
        }
        for(Node nextNode : node.outgoingNodes){
            nextNode.incomingNodes.remove(node);
            if(nextNode.incomingNodes.isEmpty()){
                nextNode.execute(node.nodeOutput);
            }
        }
    }

    @SneakyThrows
    public void execute(Map<String, Object> nodeInput){
        this.nodeInput = nodeInput;
        Thread thread = new Thread(this);
        thread.start();
//        thread.join();
    }

    protected abstract Map<String, Object> processTask(Map<String, Object> nodeInput);

    @SneakyThrows
    @Override
    public void run() {
        if(nodeInput.containsKey(OUTPUT) && nodeInput.get(OUTPUT) instanceof Exception){
            System.out.println("exception found returning");
            return;
        }
        System.out.println("processing " + this.getClass().getName());
        this.nodeOutput  = processTask(nodeInput);
        System.out.println("post processing " + this.getClass().getName());
        Node.postProcessing(this);
        System.out.println("done " + this.getClass().getName());
    }

    public static synchronized void connect(List<Pair<Node,Node>> nodePairs){
        for(Pair<Node,Node> nodePair : nodePairs){
            nodePair.getKey().getOutgoingNodes().add(nodePair.getValue());
            nodePair.getValue().getIncomingNodes().add(nodePair.getKey());
        }
    }



}
