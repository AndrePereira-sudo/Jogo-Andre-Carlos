package com.logic.jogo;

import com.badlogic.gdx.graphics.Texture;

public class InimigoEntidade extends Entidade {

    public InimigoEntidade(Texture textura, float x, float y) {
        super(String.valueOf(textura), x, y, 32, 32);
    }

    public void perseguir(JogadorEntidade jogador, float delta) {
        if (corpo.x < jogador.getCorpo().x) corpo.x += 100 * delta;
        else if (corpo.x > jogador.getCorpo().x) corpo.x -= 100 * delta;

        if (corpo.y < jogador.getCorpo().y) corpo.y += 100 * delta;
        else if (corpo.y > jogador.getCorpo().y) corpo.y -= 100 * delta;
    }
}
