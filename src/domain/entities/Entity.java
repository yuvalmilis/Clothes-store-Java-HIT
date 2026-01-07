package domain.entities;

public abstract class Entity {
     protected String id;
     protected String branchId;

     public abstract EntityData toData();

     protected Entity() {}

     protected Entity(String id, String branchId) {
          this.id = id;
          this.branchId = branchId;
     }

     public String getId() {
          return this.id;
     }

     public boolean compare(String id) {
          return this.id.equals(id);
     }

     public String getBranchId() {
         return this.branchId;
     }
}
