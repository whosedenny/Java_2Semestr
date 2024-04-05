package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;



public class Main {
    private static List<Product> products;

    private static List<Category> categories;
    private static Cart cart;
    private static List<Order> orders;

    public static void main(String[] args) {
        
        Category electronics = new Category(1, "Electronics");
        Category smartphones = new Category(2, "Smartphones");
        Category accessories = new Category(3, "Accessories");

        Product product1 = new Product(1, "Laptop HP", 19589.99, "A high-performance laptop for work and play", electronics);
        Product product2 = new Product(2, "Smartphone Samsung", 10999.50, "Smartphone with nice AMOLED-display", smartphones);
        Product product3 = new Product(3, "Headphones Samsung", 5399.00, "Wireless headphones", accessories);
        Product product4 = new Product(4, "Laptop Razer", 35000.00, "A high-performance laptop for work and play", electronics);
        Product product5 = new Product(5, "Smartphone Google Pixel", 19999.00, "Smartphone with nice camera", smartphones);
        Product product6 = new Product(6, "Headphones HyperX", 1499.00, "Wired gaming headphones", accessories);
        products = new ArrayList<>();
        cart = new Cart();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        orders = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Viewing products.");
            System.out.println("2. Add to cart.");
            System.out.println("3. Cart overview");
            System.out.println("4. Remove from cart");
            System.out.println("5. Create order");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    
                    for (Product product : products) {
                        System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Category: " + product.getCategory().getName());
                    }
                    break;
                case 2:
                    
                    System.out.println("Select the product ID to add it to the cart:");
                    int id = scanner.nextInt();
                    for (Product product : products) {
                        if (product.getId() == id) {
                            cart.addProduct(product);
                            System.out.println(product.getName() + " added to cart. " + "with id: " +product.getId() );
                            break;
                        }
                    }
                    break;
                case 3:
                   
                    cart.viewCart();
                    break;
                case 4:
                    
                    System.out.println("Enter the product ID to remove it from the cart:");
                    int id_for_del = scanner.nextInt();
                    for (Product product : cart.getProducts()) {
                        if (product.getId() == id_for_del) {
                            cart.removeProduct(product);
                            System.out.println(product.getName() + " Removed from cart.");
                            break;
                        }
                    }
                    break;
                case 5:
                    
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(orders.size() + 1, new Date(), new ArrayList<>(cart.getProducts()));
                        orders.add(order);
                        System.out.println("You have created the order:");
                        for (Product product : order.getProducts()) {
                            System.out.println(product.getName()+" for "+ product.getPrice() );
                        }
                        cart.getProducts().clear();
                    } else {
                        System.out.println("The cart is empty.");
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("There is no such function, please select what is indicated on the screen ");
            }
        }

        scanner.close();
    }
}