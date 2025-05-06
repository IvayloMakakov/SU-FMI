package blogic;

public class AnimalInstance {
    private int animalKey;
    private String animalName;
    private int animalAge;

    private final String INSTANCE_ID;
    private static int counter = 0;

    public AnimalInstance(int animalKey, String animalName, int animalAge) {
        setAnimalKey(animalKey);
        setAnimalName(animalName);
        setAnimalAge(animalAge);

        INSTANCE_ID = String.format("%-20s%03d", animalName, counter++);
    }

    public int getAnimalKey() {
        return animalKey;
    }

    public void setAnimalKey(int animalKey) {
        if (animalKey >= 0) {
            this.animalKey = animalKey;
        } else {
            this.animalKey = 0;
        }
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        if (animalName != null && !animalName.equals("")) {
            this.animalName = animalName;
        } else {
            this.animalName = "Unknown";
        }
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        if (animalAge >= 0) {
            this.animalAge = animalAge;
        } else {
            this.animalAge = 1;
        }
    }

    public String getINSTANCE_ID() {
        return INSTANCE_ID;
    }

    @Override
    public String toString() {
        return String.format("%s , Възраст: %3d",
                INSTANCE_ID, animalAge);
    }
}