import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BuildAnalyzer {

    private final ModuleGraph graph;
    private final Map<String, String> rebuildReason; // for visualizing

    public BuildAnalyzer(ModuleGraph graph) {
        this.graph = graph;
        this.rebuildReason = new HashMap<>();
    }

    public Set<String> findAffected(String changedModule) {

        Set<String> affectedModules = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        rebuildReason.clear();

        // init
        affectedModules.add(changedModule);
        queue.add(changedModule);
        rebuildReason.put(changedModule, "changed directly");

        // key is module, value is dependsOn
        // call getDependents on value?

        while (!queue.isEmpty()) {
            // for each item in queue cal getDepents
            // when all calls are done remove that from queue, but all the results back to
            // queue

            String current = queue.poll();

            for (String dependent : graph.getDependents(current)) {

                if (!affectedModules.contains(dependent)) {

                    affectedModules.add(dependent);
                    queue.add(dependent);
                    rebuildReason.put(dependent, current);

                }
            }
        }
        return affectedModules;
    }

    public Map<String, String> getRebuildReason() {
        return rebuildReason;
    }

    public void printRebuildReasons(Set<String> affectedModules) {

        System.out.println("Affected modules:");
        for (String module : affectedModules) {
            System.out.println("- " + module);
        }
        System.out.println("Rebuild reasons: ");
        for (Map.Entry<String, String> entry : rebuildReason.entrySet()) {
            String module = entry.getKey();
            String reason = entry.getValue();

            if (reason.equals("changed directly")) {
                System.out.println("- " + module + "is " + reason);
            } else {
                System.out.println("- " + module + " depends on " + reason);
            }
        }

    }
}
