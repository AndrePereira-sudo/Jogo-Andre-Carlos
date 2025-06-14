//package com.logic.jogo;
package com.mygdx.game.inimigos;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;


public class MoverAleatorio implements Mover {
    private float velocidade;
    private Random random = new Random();
    public MoverAleatorio(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void mover(Rectangle inimigo, Rectangle jogador) {
// Gera um movimento aleatório dentro de um intervalo
        inimigo.x += (random.nextFloat() - 0.5f) * 2 * velocidade;
        inimigo.y += (random.nextFloat() - 0.5f) * 2 * velocidade;

        /*
        // Gera um movimento aleatório dentro de um intervalo
        float deltaX = (random.nextFloat() - 0.5f) * velocidade; // Movimento aleatório no eixo X
        float deltaY = (random.nextFloat() - 0.5f) * velocidade; // Movimento aleatório no eixo Y

        // Aplica o movimento ao inimigo
        inimigo.x += deltaX;
        inimigo.y += deltaY;

        // Limita o movimento para que o inimigo não saia da tela
        if (inimigo.x < 0) inimigo.x = 0;
        if (inimigo.y < 0) inimigo.y = 0;
        if (inimigo.x + inimigo.width > Gdx.graphics.getWidth()) inimigo.x = Gdx.graphics.getWidth() - inimigo.width;
        if (inimigo.y + inimigo.height > Gdx.graphics.getHeight()) inimigo.y = Gdx.graphics.getHeight() - inimigo.height;
    */
    }


}
