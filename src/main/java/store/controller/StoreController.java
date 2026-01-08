package store.controller;

import store.domain.PromotionCatalog;
import store.domain.Stock;
import store.io.FileReader;
import store.view.OutputView;

public class StoreController {

    private final InputHandler inputHandler;
    private final OutputView outputView;

    public StoreController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void process() {
        FileReader fileReader = new FileReader();
        PromotionCatalog promotionCatalog = fileReader.readPromotionCatalogFromFile();
        Stock stock = fileReader.readStockFromFile(promotionCatalog);

        outputView.printHelloAndStock(stock.getProductCountMap());

        
    }


}
