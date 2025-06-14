//package com.logic.jogo;
package com.mygdx.game.inimigos;
import com.badlogic.gdx.math.Rectangle;
import java.awt.*;

 // Interface que define a estratégia de movimento dos inimigos no jogo.
 //   específica para diferentes tipos de inimigos.

public interface MovimentoStrategy {
    void mover(Rectangle inimigo, Rectangle jogador);

}
