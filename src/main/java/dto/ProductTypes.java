package dto;

/**
 * All product types which should be validated.
 */
public enum ProductTypes {
    ALL ("ALL"),
    SPOT ("SPOT"),
    FORWARD ("FORWARD"),
    OPTION ("VanillaOption");

    public String name;

    ProductTypes(String name) {
        this.name = name;
    }

    /**
     * The method returns name of trade product type.
     *
     * @return the name of trade product.
     */
    @Override
    public String toString() {
        return name;
    }
}
