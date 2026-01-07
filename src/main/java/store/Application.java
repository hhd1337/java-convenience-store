package store;

import store.controller.StoreController;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = new StoreConfig();
        StoreController storeController = storeConfig.storeController();
        storeController.process();
    }
}
