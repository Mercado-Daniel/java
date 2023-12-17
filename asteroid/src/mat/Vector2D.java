package mat;

public class Vector2D{
    private double x,y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        x = 0;
        y = 0;
    }

    public double getMagnitud(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDireccion(double angulo){
        return new Vector2D(Math.cos(angulo) * getMagnitud(), Math.sin(angulo) * getMagnitud());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}