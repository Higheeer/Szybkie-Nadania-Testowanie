package SzybkieNadania.Utils.ElementWrappers;

public interface PageElement {

    void fill(String value);

    PageElement sendKeys(String keys);

    String value();

    PageElement clear();

    boolean error();

}
