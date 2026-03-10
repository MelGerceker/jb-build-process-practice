import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModuleGraph {
    // stores reverse dependencies
    // app-> ui means app depends on ui
    // we store ui-> app

    private final Map<String, Set<String>> reverseDependencies;

    public ModuleGraph(){
        reverseDependencies = new HashMap<>();
    }

    public void addModule(String module){
      reverseDependencies.putIfAbsent(module, new HashSet<String>());
      // init w/ empty arraylist
    }

    public void addDependency(String module, String dependsOn){
        addModule(module);
        addModule(dependsOn);

        // for storing reverse dependencies: dependsOn -> module
        reverseDependencies.get(dependsOn).add(module);
           
    }

    public Set<String> getDependents(String module){
        //returns values: list of modules that depend on module param
        return reverseDependencies.get(module);

    }


}
