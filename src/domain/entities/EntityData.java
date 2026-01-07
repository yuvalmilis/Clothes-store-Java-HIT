package domain.entities;

import java.io.Serializable;

public abstract class EntityData implements Serializable {
    public String branchId;
    public String id;
    public abstract Entity fromData();

    protected EntityData() {}

    protected EntityData(String id, String branchId) {
        this.id = id;
        this.branchId = branchId;
    }
}
