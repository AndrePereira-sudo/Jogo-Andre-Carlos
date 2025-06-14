package com.logic.jogo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;

public class MoverVertical implements MovimentoStrategy {
    private float velocidade;

    public MoverVertical(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void mover(Rectangle inimigo, Rectangle jogador) {
        if (inimigo.y < jogador.y) inimigo.y += velocidade;
        if (inimigo.y > jogador.y) inimigo.y -= velocidade;
    }
}
