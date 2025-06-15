package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;

public class JogadorEntidade extends Entidade {
    private Viewport viewport;

    public JogadorEntidade(Texture textura, float x, float y, Viewport viewport) {
        super(String.valueOf(textura), x, y, 32, 32);
        this.viewport = viewport;
    }

    public void mover(float delta) {
        float speed = 200 * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) corpo.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) corpo.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) corpo.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) corpo.y -= speed;

        corpo.x = Math.max(0, Math.min(corpo.x, viewport.getWorldWidth() - corpo.width));
        corpo.y = Math.max(0, Math.min(corpo.y, viewport.getWorldHeight() - corpo.height));
    }
}
