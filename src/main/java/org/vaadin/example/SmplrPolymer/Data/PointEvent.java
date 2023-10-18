package org.vaadin.example.SmplrPolymer.Data;

import java.util.EventObject;
import java.util.Map;

public class PointEvent extends EventObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1810495585042462525L;
	
	
	private String type;
    private Object id;
    private Map<String, Object> updates;

    public PointEvent(Object source, String type, Object id, Map<String, Object> updates) {
        super(source);
        this.type = type;
        this.id = id;
        this.updates = updates;
    }

    public String getType() {
        return type;
    }

    public Object getId() {
        return id;
    }

    public Map<String, Object> getUpdates() {
        return updates;
    }
}
