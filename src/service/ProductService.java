package service;

import domain.inventory.Product;
import domain.inventory.ProductData;
import repository.Repository;

import java.util.List;

public class ProductService extends EntityService<Product, ProductData> {

    public ProductService(Repository<Product, ProductData> repository, String name) {
        super(repository, name);
    }

    private boolean modifyItemsInCategory(String id, String branchId, int amount) {
        List<Product> data = repository.load();
        Product p  = findEntity(data, id, branchId);
        if (p == null) {
            System.out.println("Error: Could not find ID " + id + " in inventory of branch " + branchId + "\nPlease create a category for this product.");
            return false;
        }
        int x = p.getQuantity();
        p.addQuantity(amount);
        if (p.getQuantity() < 0) {
            System.out.println("Error: Not enough items in " + id + " of " + branchId + " (" + x + " items) to remove " + amount + " item/s.");
            return false;
        }
        repository.save(data);
        System.out.println("Info: Successfully modified " + amount + " items in product " + p + " from " + x + " item/s.");
        return true;
    }

    public boolean reduceItemsFromCategory(String id, String branchId, int amount) { return modifyItemsInCategory(id, branchId, -amount); }

    public boolean addItemsToCategory(String id, String branchId, int amount) { return modifyItemsInCategory(id, branchId, amount); }
}