package blogic;

public enum AnimalType {
    BIRD("Птица"),
    MAMMAL("Бозайник"),
    REPTILE ("Влечуго"),
    FISH("Риба");

    private final String typeName;

    AnimalType(String type){
        this.typeName = type;
    }

    public String getTypeName() {
        return typeName;
    }
}
