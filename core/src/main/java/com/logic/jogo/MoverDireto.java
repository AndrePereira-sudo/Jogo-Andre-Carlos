package com.logic.jogo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;

public class MoverDireto implements MovimentoStrategy {
    private float velocidade;

    public MoverDireto(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void mover(Rectangle inimigo, Rectangle jogador) {
        // Calcula a direção do movimento do inimigo em direcção ao jogador
            if (inimigo.x < jogador.x) inimigo.x += velocidade;
            if (inimigo.x > jogador.x) inimigo.x -= velocidade;
            if (inimigo.y < jogador.y) inimigo.y += velocidade;
            if (inimigo.y > jogador.y) inimigo.y -= velocidade;


    }
}
