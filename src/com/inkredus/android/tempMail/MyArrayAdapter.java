package com.inkredus.android.tempMail;

import java.util.List;
import com.example.testmail.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class MyArrayAdapter extends ArrayAdapter<Model>
{ 
    private final List<Model> list;
    private final Activity context;
 
    public MyArrayAdapter(Activity context, List<Model> list)
    {
        super(context, R.layout.list_layout, list);
        this.context = context;
        this.list = list;
    }
 
    static class ViewHolder 
    {
        protected TextView from, subject;
    
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = null;
        if (convertView == null) 
        {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.list_layout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.from = (TextView) view.findViewById(R.id.idfrom);
            viewHolder.from.setTextColor(Color.BLACK);
            viewHolder.subject = (TextView) view.findViewById(R.id.idsub);
            viewHolder.subject.setTextColor(Color.GRAY); 
            view.setTag(viewHolder);
        } 
        
        else 
        {
            view = convertView;
           
        }
        
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.from.setText(list.get(position).getEmailFrom());
        holder.subject.setText(list.get(position).getEmailSubject());
        return view;
    }
}
