package store;

import store.controller.InputHandler;
import store.controller.IteratorInputTemplate;
import store.controller.StoreController;
import store.view.InputView;
import store.view.OutputView;

public class StoreConfig {

    private InputView inputView;
    private OutputView outputView;
    private IteratorInputTemplate iteratorInputTemplate;
    private InputHandler inputHandler;
    private StoreController storeController;

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public IteratorInputTemplate iteratorInputTemplate() {
        if (iteratorInputTemplate == null) {
            iteratorInputTemplate = new IteratorInputTemplate(outputView());
        }
        return iteratorInputTemplate;
    }

    public InputHandler iteratorInputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandler(inputView(), iteratorInputTemplate());
        }
        return inputHandler;
    }

    public StoreController storeController() {
        if (storeController == null) {
            storeController = new StoreController(iteratorInputHandler(), outputView());
        }
        return storeController;
    }
}
