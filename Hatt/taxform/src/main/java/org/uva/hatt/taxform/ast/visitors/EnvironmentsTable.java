package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.values.Undefined;
import org.uva.hatt.taxform.values.Value;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentsTable {

    private final Map<String, Value> values = new HashMap<>();

    public Value add(String id, Value value) {
        return values.put(id, value);
    }

    public Value find(String id) {
        if (!values.containsKey(id)) {
            return new Undefined();
        }

        return values.get(id);
    }

    public int size() {
        return values.size();
    }

}
