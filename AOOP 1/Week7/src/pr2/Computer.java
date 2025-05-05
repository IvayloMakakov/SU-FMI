package pr2;

import java.util.Arrays;

public class Computer {
    private String type;
    private double procSpeed;
    private String[] files;

    public Computer(String type, double procSpeed, String[] files) {
        setType(type);
        setProcSpeed(procSpeed);
        setFiles(files);
    }

    public Computer() {
        this("no type", 0.0, new String[0]);
    }

    public Computer(Computer computer) {
        this(computer.getType(), computer.getProcSpeed(), computer.getFiles());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            this.type = "no type";
        } else {
            this.type = type;
        }
    }

    public double getProcSpeed() {
        return procSpeed;
    }

    public void setProcSpeed(double procSpeed) {
        if (procSpeed < 0.1) {
            this.procSpeed = 0.1;
        } else {
            this.procSpeed = procSpeed;
        }
    }

    public String[] getFiles() {
        String[] copy = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            copy[i] = files[i];
        }

        return copy;
    }

    public void setFiles(String[] files) {
        if (files != null) {
            this.files = new String[files.length];

            for (int i = 0; i < files.length; i++) {
                this.files[i] = files[i];
            }
        } else {
            this.files = new String[0];
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "type='" + type + '\'' +
                ", procSpeed=" + procSpeed +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
