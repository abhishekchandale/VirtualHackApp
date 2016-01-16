package com.login.abhishekchandale.virtualhackapp1.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.login.abhishekchandale.virtualhackapp1.R;
import com.login.abhishekchandale.virtualhackapp1.database.DbAccess;
import com.login.abhishekchandale.virtualhackapp1.ui.PreviousComplaintsActivity;

import java.util.LinkedList;

/**
 * Created by admi on 9/9/15.
 */
public class ComplaintAdapter extends BaseAdapter{

    String[] compMessage;
    byte[] compImage;
    String[] compDate;
    Context context;
    boolean[] animationStates;
    private DbAccess dbAccess;
    private static LayoutInflater layoutInflater=null;
    private  Boolean flag=true;
    private  Cursor cursor;
    private int selectedIndex;
    private LinkedList<Integer> selectedIndeces=null;

    public ComplaintAdapter(PreviousComplaintsActivity pComp, String[] compMessageList, byte[] icon, String[] applicationName)
    {

        animationStates = new boolean[100];
        compMessage=compMessageList;
        context=pComp;
        compImage=icon;
        compDate=applicationName;
        dbAccess=new DbAccess(context);
        selectedIndeces=new LinkedList<>();
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public void changeSelectedPositions(int pos) {
        int index = this.selectedIndeces.indexOf(pos);
        if (index != -1) {
            // image button in this row was selected
            this.selectedIndeces.remove(index);
        } else {
            // mark position of the image button as selected
            this.selectedIndeces.add(pos);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return compMessage.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  class Holder{

        TextView compMessage;
        TextView compDate;
        ImageView img;
        //ImageButton lockBtn;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder=new Holder();
        View rowView;
        rowView = layoutInflater.inflate(R.layout.previous_comp_row ,null);
                holder.compMessage=(TextView) rowView.findViewById(R.id.txt_card_comp);
        holder.img=(ImageView)rowView.findViewById(R.id.comp_Image);
        holder.compDate=(TextView)rowView.findViewById(R.id.txt_card_date);
        holder.compMessage.setText(compMessage[position]);
        //holder.img.setImageBitmap();
        holder.compDate.setText(compDate[position]);
        Animation animation=null;
        animation = AnimationUtils.loadAnimation(context, R.anim.pushup_in);
        animation.setDuration(500);
        rowView.startAnimation(animation);
        animation = null;
        return rowView;
    }
}
