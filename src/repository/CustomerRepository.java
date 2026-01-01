package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.customers.CustomerData;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String FILE_PATH = "data/customers.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<CustomerData> load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<List<CustomerData>>(){}.getType();
            List<CustomerData> list = gson.fromJson(reader, type);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void save(List<CustomerData> data) {
        new File("data").mkdirs(); // וודא שהתיקייה קיימת
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}