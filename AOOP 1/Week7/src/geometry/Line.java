package geometry;

import javafx.scene.Group;

public class Line {
    private Point sPoint;
    private Point ePoint;

    public Line() {
        this(new Point(), new Point(new double[]{1, 1}));
    }

    public Line(Point sPoint, Point ePoint) {
        setePoint(ePoint);
        setsPoint(sPoint);
    }

    public Line(Line line) {
        this(line.getsPoint(), line.getePoint());
    }

    public Point getsPoint() {
        return new Point(sPoint);
    }

    public void setsPoint(Point sPoint) {
        if (sPoint != null) {
            this.sPoint = new Point(sPoint);
        } else {
            this.sPoint = new Point();
        }
    }

    public Point getePoint() {
        return new Point(ePoint);
    }

    public void setePoint(Point ePoint) {
        if (ePoint != null) {
            this.ePoint = new Point(ePoint);
        } else {
            this.ePoint = new Point();
        }
    }

    public void draw(Group pane) {
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(sPoint.getCoordinates()[0], sPoint.getCoordinates()[1], ePoint.getCoordinates()[0], ePoint.getCoordinates()[1]);

        pane.getChildren().add(line);
    }

    @Override
    public String toString() {
        return String.format("Starting point coords: %s, Ending point coords: %s", sPoint, ePoint);
    }
}
