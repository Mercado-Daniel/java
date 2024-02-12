package graficos;

import java.awt.image.BufferedImage;



public class Assets {

    public static BufferedImage[] jugadorMario = new BufferedImage[30];
    public static BufferedImage[] enemigo = new BufferedImage[4];
    public static BufferedImage[] ladrillo = new BufferedImage[4];
    public static BufferedImage[] fondo = new BufferedImage[3];
    public static BufferedImage[] moneda = new BufferedImage[4];
    

    public static void iniciar(){
        try {
            jugadorMario = cargarSpriteDesdeHoja("/mario/marioChiquito.gif", 480, 64, 32, 32, 30);
            ladrillo = cargarSpriteDesdeHoja("/bricks/brick.gif", 32, 128, 32, 32, 4);
            enemigo = cargarSpriteDesdeHoja("/enemigos/woopa.png", 128, 32, 32, 32, 4);
            moneda = cargarSpriteDesdeHoja("/coins/coin3.gif", 32, 128, 32, 32, 4);
            //sprite1 = jugadorMario;
            //sprite1 = cargarSpriteDesdeHoja("/enemigos/sprite.png", 240, 240, 48, 48, 25);

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


