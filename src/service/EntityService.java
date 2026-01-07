package service;

import domain.entities.Entity;
import domain.entities.EntityData;

import repository.Repository;

import java.util.List;

public class EntityService<E extends Entity, D extends EntityData> {
    protected final Repository<E, D> repository;
    protected final String name;
    
    public EntityService(Repository<E, D> repository, String name) {
        this.repository = repository;
        this.name = name;
    }

    public List<E> getAllEntities() {
        List<E> data = repository.load();
        System.out.println("Info: Successfully loaded all entities from " + name + " repository.");
        return data;
    }

    public boolean addEntity(E entity) {
        if (entity == null) {
            System.out.println("Error: Cannot add \"null\" entity!");
            return false;
        }
        List<E> data = repository.load();
        E e = findEntity(data, entity.getId(), entity.getBranchId());
        if (e != null) {
            System.out.println("Error: Entity " + entity + " with the same ID and branch ID already exists in " + name + " repository!");
            return false;
        }
        data.add(entity);
        repository.save(data);
        System.out.println("Info: Successfully added " + entity + " to " + name + " repository.");
        return true;
    }

    public E findEntity(String value, String branchId) {
        List<E> data = repository.load();
        return findEntity(data, value, branchId);
    }

    public E findEntity(E entity) {
        if (entity == null) {
            System.out.println("Error: Cannot search for \"null\" value!");
            return null;
        }
        List<E> data = repository.load();
        return findEntity(data, entity.getId(), entity.getBranchId());
    }

    public E findEntity(List<E> data, String id, String branchId) {
        System.out.println("Info: Searching for ID" + id + " and branch ID" + branchId + " from " + data.size() + " entities in " + name + " repository.");
        for (E e : data) {// Will not run if data = null or data.size() = 0
            if (e.compare(id) && e.getBranchId().equals(branchId)) {
                System.out.println("Info: Successfully found " + e + ".");
                return e;
            }
        }
        System.out.println("Info: Could not find ID " + id + " with branch ID " + branchId + " in " + name + " repository!");
        return null;
    }

    public boolean removeEntity(String id, String branchId) {
        List<E> data = repository.load();
        E e = findEntity(data, id, branchId);
        if (e == null) {
            System.out.println("Error: Cannot find entity with ID of " + id + " and branch ID " + branchId + " in " + name + " repository!");
            return false;
        }
        data.removeIf(entity -> entity.compare(id) &&  entity.getBranchId().equals(branchId));
        repository.save(data);
        System.out.println("Info: Successfully removed " + e + " from " + name + " repository.");
        return true;
    }

    public boolean removeEntity(E entity) {
        if (entity == null) {
            System.out.println("Error: Cannot remove \"null\" value!");
            return false;
        }
        List<E> data = repository.load();
        E e = findEntity(data, entity.getId(), entity.getBranchId());
        if (e == null) {
            System.out.println("Error: Entity " + entity + " with the same ID and branch ID does not exist in " + name + " repository!");
            return false;
        }
        data.removeIf(en -> en.compare(e.getId()));
        repository.save(data);
        System.out.println("Info: Successfully removed " + e + " from " + name + " repository.");
        return true;
    }
}