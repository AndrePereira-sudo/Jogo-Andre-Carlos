package com.logic.jogo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;

public class Inimigo {
    // Inicializar inimigos

    public Rectangle corpo;
    private MovimentoStrategy estrategia;

    public Inimigo(Rectangle corpo, MovimentoStrategy estrategia) {
        this.corpo = corpo;
        this.estrategia = estrategia;
    }
}

    /*
       public static ArrayList<Rectangle> criarInimigos() {
        ArrayList<Rectangle> inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = 200 + i * 100;
            inimigo.y = 200 + i * 50;
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
        }
        return inimigos;
    }
}
*/
