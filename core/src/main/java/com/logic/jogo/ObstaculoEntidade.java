package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;

public class ObstaculoEntidade extends Entidade {
    private float x;
    //  public float x;
    //public Object x;
   // public int width;
   // public float y;
    //public float height;

    public ObstaculoEntidade(String textura, float x, float y) {
        super(textura, x, y, 48, 48);
    }

    public float getX() {
        float x = 0;
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

  //  @Override
    public boolean overlaps(Rectangle obstaculo) {
        return false;
    }
}
