package graficos;

import java.awt.image.BufferedImage;



public class Assets {

    public static BufferedImage[] jugadorMario = new BufferedImage[30];
    public static BufferedImage[] enemigo = new BufferedImage[4];
    public static BufferedImage[] ladrillo = new BufferedImage[4];
    public static BufferedImage[] cajaPregunta = new BufferedImage[4];
    public static BufferedImage[] moneda = new BufferedImage[4];
    public static BufferedImage[] numeros = new BufferedImage[11];
    public static BufferedImage[] banderita = new BufferedImage[5];
    public static BufferedImage fondo ;
    public static BufferedImage ladrilloIndestructible;
    public static BufferedImage nube;
    public static BufferedImage castillo;
    public static BufferedImage colina;
    public static BufferedImage cajaVacia;
    public static BufferedImage vida;
    public static BufferedImage canoGrande;
    public static BufferedImage canoMediano;
    public static BufferedImage canoChico;
    public static BufferedImage poste;
    public static BufferedImage hongo;
    public static BufferedImage hongoVida;
    public static BufferedImage invisible;

    public static void iniciar(){
        try {
            jugadorMario = cargarSpriteDesdeHoja("/imagenes/mario/marioChiquito.gif", 480, 64, 32, 32, 30);
            ladrillo = cargarSpriteDesdeHoja("/imagenes/bricks/brick.gif", 32, 128, 32, 32, 4);
            enemigo = cargarSpriteDesdeHoja("/imagenes/enemigos/woopa.png", 128, 32, 32, 32, 4);
            moneda = cargarSpriteDesdeHoja("/imagenes/coins/coin3.gif", 32, 128, 32, 32, 4);
            cajaPregunta = cargarSpriteDesdeHoja("/imagenes/boxes/surpriseBox.gif", 32, 128, 32, 32, 4);
            banderita = cargarSpriteDesdeHoja("/imagenes/tiles/banderita.png", 361, 374, 72, 374, 5);

            fondo = cargarSprite("/imagenes/Backgrounds/mountains_a.png");
            ladrilloIndestructible = cargarSprite("/imagenes/bricks/ladrillosIndestructibles.png");
            nube = cargarSprite("/imagenes/tiles/nubes.png");
            castillo = cargarSprite("/imagenes/tiles/castillo.png");
            colina = cargarSprite("/imagenes/tiles/colina.png");
            cajaVacia = cargarSprite("/imagenes/boxes/voidBox.gif");
            vida = cargarSprite("/imagenes/numeros/mario-1.png");
            
            canoChico = cargarSprite("/imagenes/tiles/canoChico.png");
            canoMediano = cargarSprite("/imagenes/tiles/canoMediano.png");
            canoGrande = cargarSprite("/imagenes/tiles/canoGrande.png");
            poste = cargarSprite("/imagenes/tiles/poste.png");
            hongo = cargarSprite("/imagenes/items/hongo.png");
            hongoVida = cargarSprite("/imagenes/items/hongovida.png");
            invisible = cargarSprite("/imagenes/items/invisible.png");
            
            for(int i = 0; i < numeros.length; i++){
                numeros[i] = cargarSprite("/imagenes/numeros/"+i+".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage cargarSprite(String ruta){
        return CargarGraficos.cargarImagen(ruta);
    }

    private static BufferedImage[] cargarSpriteDesdeHoja(String ruta, int anchoHoja, int altoHoja, int anchoSprite, int altoSprite, int cantidad){
        BufferedImage hoja = cargarSprite(ruta);
        BufferedImage[] sprites = new BufferedImage[cantidad];
        int indiceSprite = 0;

        for(int y = 0; y < altoHoja; y += altoSprite){
            for(int x = 0; x < anchoHoja; x += anchoSprite){
                sprites[indiceSprite] = hoja.getSubimage(x, y, anchoSprite, altoSprite);
                indiceSprite++;

                if(indiceSprite >= cantidad){
                    break;
                }
            }
        }
        return sprites;
    }
}


