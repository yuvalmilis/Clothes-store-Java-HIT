package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.users.EmployeeData;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private static final String FILE_PATH = "data/employees.json";


    private final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();


    public List<EmployeeData> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<List<EmployeeData>>(){}.getType();
            List<EmployeeData> list = gson.fromJson(reader, type);
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void save(List<EmployeeData> data) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
