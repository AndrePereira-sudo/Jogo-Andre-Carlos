package com.logic.jogo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

    public abstract class Entidade {
        protected String textura;
        protected Rectangle corpo;

        public Entidade(String textura, float x, float y, float largura, float altura) {
            this.textura = String.valueOf(textura);
            this.corpo = new Rectangle(x, y, largura, altura);
        }

        public void desenhar(SpriteBatch batch) {
         //   batch.draw(textura, corpo.x, corpo.y, corpo.width, corpo.height);
        }

        public boolean colide(Entidade outra) {
            return corpo.overlaps(outra.corpo);
        }

        public Rectangle getCorpo() {
            return corpo;
        }
    }


