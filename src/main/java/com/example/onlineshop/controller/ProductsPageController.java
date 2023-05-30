package com.example.onlineshop.controller;

import com.example.onlineshop.model.products.*;
import com.example.onlineshop.model.user.Admin;

import java.util.ArrayList;
import java.util.Objects;

public class ProductsPageController {
    private final Admin admin = Admin.getAdmin();
    private static int productNum = 0;  // I need it in do while part of productPage.viewProduct cuz need to check when we are at the end of the list
    private static int customerIndex = -1;

    public ProductsPageController() {
    }

    public static int getProductNum() {
        return productNum;
    }

    public static void setProductNumZero() {
        productNum = 0;
    }

    public static int getCustomerIndex() { // we don't need this
        return customerIndex;
    }

//    public static void setCustomerIndex(int customerIndex) {  //we change it by setProductNumZero and don't need this
//        ProductsPageController.customerIndex = customerIndex;
//    }

    //show product list
    public ArrayList<Product> showNextPageOfProduct(ArrayList<Product> products) {
        if (products.size() - productNum >= 5) {
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum; i < productNum + 5; i++) {
                pageProducts.add(products.get(i));
            }
            productNum = productNum + 5;
            return pageProducts;
        } else if (products.size() - productNum < 5 && products.size() - productNum > 0) {
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum; i < products.size(); i++) {
                pageProducts.add(products.get(i));
            }
            productNum = products.size();
            return pageProducts;
        }
        return null;
    }

    public ArrayList<Product> showPreviousPageOfProduct(ArrayList<Product> products) {
        if (productNum >= 5 && productNum % 5 == 0) { //when you are in full page
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum - 10; i < productNum - 5; i++) {
                pageProducts.add(products.get(i));
            }
            productNum = productNum - 5;
            return pageProducts;
        } else if (productNum >= 5) { //productNum%5 != 0
            ArrayList<Product> pageProducts = new ArrayList<>();
            for (int i = productNum - (5 + (productNum % 5)); i < productNum - (productNum % 5); i++) {
                pageProducts.add(products.get(i));
            }
            productNum = productNum - (productNum % 5);
            return pageProducts;
        } else
            return null;
    }

    //search products by name
    public ArrayList<Product> searchProductByName(String productName) {
        ArrayList<Product> findProduct = new ArrayList<>();
        for (Product element : admin.getProductsList()) {
            if (Objects.equals(element.getProductName(), productName))
                findProduct.add(element);
        }
        return findProduct;
    }

    //select products by ID
    public Product selectProductByID(String productID) {
        for (Product element : admin.getProductsList()) {
            if (Objects.equals(element.getProductID(), productID))
                return element;
        }
        return null;
    }

    //filters-----------------------------------------------------------------------------------------------------------
    //filter Category Digital
    public ArrayList<Product> filterDigitalCategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getProductCategory(), ProductCategory.Digital))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter Category Stationery
    public ArrayList<Product> filterStationeryCategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getProductCategory(), ProductCategory.Stationery))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter Category Edible
    public ArrayList<Product> filterEdibleCategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getProductCategory(), ProductCategory.Edible))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter Category Vehicle
    public ArrayList<Product> filterVehicleCategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getProductCategory(), ProductCategory.Vehicle))
                filterProduct.add(element);
        }
        return filterProduct;
    }
    //filter subcategory Information Storage
    public ArrayList<Product> filterInformationStorageSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof  InformationStorageEquipment)
                filterProduct.add(element);
        }
        return filterProduct;
    }
    //filter subcategory SSD
    public ArrayList<Product> filterSSDSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "SSD"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory FlashMemory
    public ArrayList<Product> filterFlashMemorySubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "FlashMemory"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory PC
    public ArrayList<Product> filterPCSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "PC"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory Car
    public ArrayList<Product> filterCarSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "Car"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory Bicycle
    public ArrayList<Product> filterBicycleSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "Bicycle"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory Pen
    public ArrayList<Product> filterPenSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "Pen"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory Pencil
    public ArrayList<Product> filterPencilSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "Pencil"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter subcategory NoteBook
    public ArrayList<Product> filterNoteBookSubcategory(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getSubcategory(), "NoteBook"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter by available status
    public ArrayList<Product> filterByStatus(ArrayList<Product> products) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (Objects.equals(element.getProductStatus(), "Available"))
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter by price range
    public ArrayList<Product> filterByPrice(ArrayList<Product> products, double start, double end) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element.getProductPrice() >= start && element.getProductPrice() <= end)
                filterProduct.add(element);
        }
        return filterProduct;
    }

    //filter by RAM memory
    public ArrayList<Product> filterByRAMMemory(ArrayList<Product> products, int RAMMemory) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof PersonalComputer) {
                PersonalComputer tmp = (PersonalComputer) element;
                if (tmp.getRAMMemory() >= RAMMemory)
                    filterProduct.add(element);
            }
        }
        return filterProduct;
    }

    //filter by CPU com.example.onlineshop.model
    public ArrayList<Product> filterByCPUModel(ArrayList<Product> products, String CPUModel) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof PersonalComputer) {
                PersonalComputer tmp = (PersonalComputer) element;
                if (Objects.equals(tmp.getCPUModel(), CPUModel))
                    filterProduct.add(element);
            }
        }
        return filterProduct;
    }

    //filter by reed speed range
    public ArrayList<Product> filterByReedSpeed(ArrayList<Product> products, int startReedSpeed, int endReedSpeed) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof SSD) {
                SSD tmp = (SSD) element;
                if (tmp.getReadSpeed() >= startReedSpeed && tmp.getReadSpeed() <= endReedSpeed)
                    filterProduct.add(element);
            }
        }
        return filterProduct;
    }

    //filter by write speed range
    public ArrayList<Product> filterByWriteSpeed(ArrayList<Product> products, int startWriteSpeed, int endWriteSpeed) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof SSD) {
                SSD tmp = (SSD) element;
                if (tmp.getWriteSpeed() >= startWriteSpeed && tmp.getWriteSpeed() <= endWriteSpeed)
                    filterProduct.add(element);
            }
        }
        return filterProduct;
    }

    //filter by USB type for flash memory
    public ArrayList<Product> filterByUSBType(ArrayList<Product> products, String USBType) {
        ArrayList<Product> filterProduct = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof FlashMemory) {
                FlashMemory tmp = (FlashMemory) element;
                if (Objects.equals(tmp.getUSBType(), USBType))
                    filterProduct.add(element);
            }
        }
        return filterProduct;
    }
}
