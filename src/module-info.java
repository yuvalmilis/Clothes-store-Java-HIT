module Hit.project1 {

    requires com.google.gson;
    requires java.xml.crypto;


    exports domain.entities to com.google.gson;
    exports domain.users to com.google.gson;
    exports domain.customers to com.google.gson;
}
