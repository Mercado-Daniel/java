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

    public Vector2D suma(Vector2D v){//suma dos vectores
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D mulPorEscalar(double escalar){//multiplica un vector por un escalar
         return new Vector2D(x * escalar, y * escalar);
    }

    public Vector2D limite(double valor){//hay que normalizar el vector para que este no pierda su velocidad
        if (getMagnitud() > valor) {
            
            return this.normalizar().mulPorEscalar(valor);
        }
        return this;
    }

    public Vector2D normalizar(){ //devuelve un vector normalizado
        return new Vector2D(x / getMagnitud(), y / getMagnitud());//divide cada componente entre su magnitud
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