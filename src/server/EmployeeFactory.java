package server;

import com.google.gson.Gson;
import domain.users.Employee;
import domain.users.EmployeeData;

public final class EmployeeFactory {

    private static final Gson gson = new Gson();

    public static Employee parseString(String message) {
        return gson.fromJson(message, EmployeeData.class).fromData();
    }

    public static String toString(Employee e) {
        return gson.toJson(e.toData());
    }
}
