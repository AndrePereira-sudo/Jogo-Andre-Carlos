package com.logic.jogo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;

public class MoverHorizontal implements MovimentoStrategy {
    private float velocidade;

    public MoverHorizontal(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void mover(Rectangle inimigo, Rectangle jogador) {
        if (inimigo.x < jogador.x) inimigo.x += velocidade;
        if (inimigo.x > jogador.x) inimigo.x -= velocidade;
    }
}
