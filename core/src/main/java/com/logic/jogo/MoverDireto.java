//package com.logic.jogo;
package com.mygdx.game.inimigos;
import com.badlogic.gdx.math.Rectangle;


public class MoverDireto implements com.mygdx.game.inimigos.MovimentoStrategy {
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

/*        // Alternativa: Move o inimigo diretamente na direção do jogador
        float deltaX = jogador.x - inimigo.x;
        float deltaY = jogador.y - inimigo.y;
        float distancia = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Normaliza a direção
        if (distancia > 0) {
            deltaX /= distancia;
            deltaY /= distancia;
        }

        // Move o inimigo na direção do jogador
        inimigo.x += deltaX * velocidade;
        inimigo.y += deltaY * velocidade;
       */
    }
}
