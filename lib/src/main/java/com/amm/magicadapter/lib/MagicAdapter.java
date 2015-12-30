package com.amm.magicadapter.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import java.util.HashMap;
import java.util.List;
/**
 * Created by Alejandro Martínez Martínez on 30/12/2015.
 *
 * Clase que encapsula toda la funcionalidad de Magic Adapter
 */
public abstract class MagicAdapter extends RecyclerView.Adapter<MagicAdapter.MagicViewHolder> {

    protected Context context;
    private List<Object> objectList;
    private int lastPosition;
    private int height;
    private boolean withAnimation;
    private boolean animationFlag = true;
    private boolean holderCreate;
    private boolean holderBind;


    public void initializeMagicAdapter(Context context, List<Object> objectList, boolean withAnimation) {
        this.context = context;
        this.objectList = objectList;
        this.withAnimation = withAnimation;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = windowManager.getDefaultDisplay().getHeight();
    }

    @Override
    public MagicViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        String layoutName = getLayoutName();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getResourceIdByName(layoutName, "layout"), viewGroup, false);
        holderCreate = true;
        return new MagicViewHolder(view);
    }

    private Drawable getDrawableByName(String name) {
        int drawableId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        Drawable drawable = null;
        if (drawableId > 0) {
            drawable = context.getResources().getDrawable(drawableId);
        }
        return drawable;
    }


    private int getResourceIdByName(String name, String type) {
        int drawableId = context.getResources().getIdentifier(name, type, context.getPackageName());
        return drawableId;
    }

    public abstract String getLayoutName();

    public abstract void setInfoToViews(Object object, int position, MagicViewHolder magicViewHolder);

    @Override
    public void onBindViewHolder(MagicViewHolder viewHolder, int i) {
        holderBind = true;
        if (holderCreate)
            animationFlag = false;
        else
            animationFlag = true;
        holderCreate = false;
        viewHolder.bindObject(objectList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    private void initAnimation(int position, View itemView) {
        AnimationSet animationSet = null;

        if (position >= lastPosition) {
            animationSet = new AnimationSet(true);
            animationSet.setDuration(400);

            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            animationSet.addAnimation(alphaAnimation);
            if (animationFlag) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, (float) (height * 0.4), 0);
                animationSet.addAnimation(translateAnimation);
            }

            if (position == getItemCount() - 1) {
                lastPosition = getItemCount();
            } else {
                lastPosition = position;
            }

        }

        if (animationSet != null) {
            itemView.startAnimation(animationSet);
        }
    }


    public class MagicViewHolder extends RecyclerView.ViewHolder {

        //Vistas de cada card.
        private HashMap<String, View> viewsMap;
        private View itemView;

        public View getViewByName(String name) {
            return viewsMap.get(name);
        }


        public MagicViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            viewsMap = new HashMap<String, View>();
            getallViews(itemView);
        }

        private void getallViews(View itemView) {

            if (itemView instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) itemView).getChildCount(); i++) {
                    View childView = ((ViewGroup) itemView).getChildAt(i);
                    if (childView != null) {
                        if (childView instanceof ViewGroup)
                            getallViews(childView);
                        else {
                            String name = (String) childView.getTag();
                            viewsMap.put(name, childView);
                        }
                    }
                }
            } else {
                String name = context.getResources().getResourceName(itemView.getId());
                viewsMap.put(name, itemView);
            }
        }


        public void bindObject(final Object object, int position) {
            setInfoToViews(object, position, this);
            if (withAnimation)
                initAnimation(position, itemView);
        }
    }


}
