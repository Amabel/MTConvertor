import java.util.Map;

public class VariableMapping {

    private String scope;
    private String key;
    private String varName;
    private Map<String, String> valueMap; // name, value
    
    public VariableMapping(String scope, String key, String varName, Map<String, String> valueMap) {
        super();
        this.scope = scope;
        this.key = key;
        this.varName = varName;
        this.valueMap = valueMap;
    }
    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    /**
     * @return the varName
     */
    public String getVarName() {
        return varName;
    }
    /**
     * @return the valueName
     */
    public Map<String, String> getValueMap() {
        return valueMap;
    }
    
}
