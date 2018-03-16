package PageObjects.VC5;

import PageObjects.Page;

public abstract class VC5Page extends Page{

    private static final String URLStart = "";

    protected VC5Page(String currentPage, boolean entryPoint) {
        super(currentPage, URLStart, entryPoint);
    }
}
