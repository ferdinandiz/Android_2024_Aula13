package com.fer.aula13_variveispersistentes;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class CirculoRotateAnimation extends Animation {
   private final View view;
   private float cx, cy, prevX, prevY;

   private final float r;
   private float prevDx;
   private float prevDy;

   public CirculoRotateAnimation(ImageView imagem, int raio) {
      this.view = imagem;
      this.r = raio;
   }

   @Override
   public boolean willChangeBounds() {
      return true;
   }

   @Override
   public void initialize(int width, int height, int parentWidth, int parentHeight) {
      int cxImage = width/2;
      int cyImage = height/2;
      cx = view.getLeft() + cxImage;
      cy = view.getTop()+cyImage;
      prevX = cx;
      prevY = cy;
   }

   @Override
   protected void applyTransformation(float interpolatedTime, Transformation t) {
      if(interpolatedTime ==0 ){
         t.getMatrix().setTranslate(prevDx, prevDy);
         return;
      }
      float angleDeg = (interpolatedTime*360f+90)%360;
      float angleRad = (float)Math.toRadians(angleDeg);

      float x = (float) (cx+r*Math.cos(angleRad));
      float y = (float) (cy+r*Math.sin(angleRad));

      float dx = prevX - x;
      float dy = prevY - y;

      prevX = x;
      prevY = y;
      prevDx = dx;
      prevDy = dy;

      //aplicar a animação circular
      t.getMatrix().setTranslate(dx,dy);
   }
}
