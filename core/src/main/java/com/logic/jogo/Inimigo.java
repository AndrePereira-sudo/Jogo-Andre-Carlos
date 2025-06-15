package com.logic.jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;
import java.util.ArrayList;

public class Inimigo {
    // Inicializar inimigos
    public Rectangle corpo;
    private MovimentoStrategy estrategia;

    public Inimigo(Rectangle corpo, MovimentoStrategy estrategia) {
        this.corpo = corpo;
        this.estrategia = estrategia;
    }
    public static ArrayList<Rectangle> criarInimigos() {
        ArrayList<Rectangle> inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = MathUtils.random(0, Gdx.graphics.getWidth() - 32);
            inimigo.y = MathUtils.random(0, Gdx.graphics.getHeight() - 32);
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
        }
        return inimigos;
    }
}

