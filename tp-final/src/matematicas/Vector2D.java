package matematicas;

public class Vector2D {
    private double ejeX, ejeY;

    public Vector2D(double ejeX, double ejeY){
        this.ejeX = ejeX;
        this.ejeY = ejeY;
    }

    public Vector2D(){
        ejeX = 0;
        ejeY = 0;
    }

    public Vector2D resta(Vector2D v){//resta de vectores
        return new Vector2D(ejeX - v.getEjeX(), ejeY - v.getEjeY());
    }

    public double getMagnitud(){
        return Math.sqrt(ejeX * ejeX + ejeY * ejeY);
    }


    public void setEjeX(double ejeX){
        this.ejeX = ejeX;
    }

    public double getEjeX(){
        return this.ejeX;
    }

    public void setEjeY(double ejeY){
        this.ejeY = ejeY;
    }

    public double getEjeY(){
        return this.ejeY;
    }
}
