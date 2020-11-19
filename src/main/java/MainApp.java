import entities.Butler;
import entities.Job;
import manager.DriverDataManager;
import nodes.CloseWebDriverNode;
import nodes.GetNewWebDriverNode;
import nodes.LinkedinSendConnectionRequestsNode;
import nodes.Node;
import nodes.ValidateLinkedInLoginNode;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;


public class MainApp {

    public static void main(String[] args) {
        DriverDataManager.initalise();
        Node n1 = new GetNewWebDriverNode();
        Node n2 = new ValidateLinkedInLoginNode();
        Node n3 = new LinkedinSendConnectionRequestsNode();
        Node n4 = new CloseWebDriverNode();
        Node.connect(Arrays.asList(Pair.of(n1,n2),Pair.of(n2,n3),Pair.of(n3,n4)));

        Butler butler = Butler.builder().job(Job.builder().startNode(n1).build()).build();
        butler.executeJob();
        System.out.println("done");
        while (true){

        }
    }
}
