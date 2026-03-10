import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ModuleGraph graph = new ModuleGraph();

        // Core dependencies
        graph.addDependency("ui", "main");
        graph.addDependency("auth", "main");

        // second layer dependencies
        graph.addDependency("service", "auth");
        graph.addDependency("app", "ui");

        // third layer dependencies
        graph.addDependency("tests", "service");

        // separate branch
        graph.addDependency("analytics", "metrics");


        BuildAnalyzer build = new BuildAnalyzer(graph);

        String changedModuel = "main";

        Set<String> affectedModules = build.findAffected(changedModuel);
        build.printRebuildReasons(affectedModules);

    }
}