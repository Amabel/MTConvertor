import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Parser {
    
    public static void parsePlainDeclaration(String string, Map<String, VariableMapping> map, String scope) {
        
        String[] strings = string.split(" ");
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
        
        String varKey = null;
        String initValue = null;
        String varName = null;
        Map<String, String> valueMap = null;
        
        if (stringList.contains("=")) {
            int indexEqual = stringList.indexOf("=");
            varKey = stringList.get(indexEqual - 1);
            initValue = stringList.get(indexEqual + 1);
            
            if (stringList.contains("/*")) {
                int indexComment = stringList.indexOf("/*");
                varName = stringList.get(indexComment + 1);
            }
        }
        
        VariableMapping vMap = new VariableMapping(scope, varKey, varName, initValue, valueMap);
        
        map.put(varKey, vMap);        
    }
    
}
