import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ModuleGraph graph = new ModuleGraph();

        graph.addDependency("app", "ui"); // ex. app depends on ui
        graph.addDependency("app", "service");
        graph.addDependency("ui", "shared");
        graph.addDependency("service", "auth");
        graph.addDependency("service", "database");
        graph.addDependency("auth", "shared");
        graph.addDependency("database", "logging");
        graph.addDependency("tests", "service");
        graph.addDependency("tests", "shared");

        BuildAnalyzer build = new BuildAnalyzer(graph);

        String changedModuel = "shared";

        Set<String> affectedModules = build.findAffected(changedModuel);
        build.printRebuildReasons(affectedModules);

    }
}