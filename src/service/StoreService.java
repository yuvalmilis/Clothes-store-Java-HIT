package service;

import domain.story.Store;
import domain.story.StoreData;
import repository.Repository;

public class StoreService extends EntityService<Store, StoreData> {

    public StoreService(Repository<Store, StoreData> repository, String name) {
        super(repository, name);
    }
}
