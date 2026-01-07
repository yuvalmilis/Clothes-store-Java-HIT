package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import domain.entities.Entity;
import domain.entities.EntityData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class Repository<E extends Entity, D extends EntityData> {

    private final String FILE_PATH;
    private final TypeToken<List<D>> typeToken;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Repository(Paths path, TypeToken<List<D>> typeToken) {
        FILE_PATH = path.getValue();
        this.typeToken = typeToken;
    }

    public List<E> load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Info: DB file does not exist! Creating new DB.");
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(FILE_PATH)) {
            List<D> list = gson.fromJson(reader, typeToken.getType());
            if (list == null) return new ArrayList<>();
            List<E> entities = new ArrayList<>(list.size());
            for (D d : list) {
                entities.add((E) d.fromData());
            }
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void save(List<E> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("Error: Invalid length of data to save!");
            return;
        }
        List<D> entityDataList = new ArrayList<>(data.size());
        for (E d : data) {
            entityDataList.add((D) d.toData());
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(entityDataList, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
