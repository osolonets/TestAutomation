package PageObjects.REX.MarketingSite;

import PageObjects.Page;
import Utils.Helpers.Helper;

public abstract class WordPressREX extends Page{

    protected WordPressREX(String currentPage, boolean entryPoint) {
        super(currentPage, Helper.getREX_URLStart(WordPressREX.class), entryPoint);
    }
}
