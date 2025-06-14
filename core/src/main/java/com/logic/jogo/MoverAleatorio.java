package com.logic.jogo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.inimigos.MovimentoStrategy;
import java.util.Random;

public class MoverAleatorio implements MovimentoStrategy {
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
    }
}
