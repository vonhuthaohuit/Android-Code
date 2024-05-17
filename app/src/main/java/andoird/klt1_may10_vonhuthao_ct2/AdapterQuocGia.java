package andoird.klt1_may10_vonhuthao_ct2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterQuocGia extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<QuocGia> quocGias;
    int layoutItem;
    public AdapterQuocGia(Activity context, int
            layoutItem, ArrayList<QuocGia> quocGias) {
        this.layoutInflater = context.getLayoutInflater();
        this.quocGias = quocGias;
        this.layoutItem = layoutItem;
    }
    @Override
    public int getCount() {
        return this.quocGias.size();
    }

    @Override
    public Object getItem(int position) {
        return this.quocGias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuocGia quocGia = quocGias.get(position);
        View rowView = layoutInflater.inflate(layoutItem,null,true);
        ImageView img_Truyen = rowView.findViewById(R.id.image_QuocGia);
        img_Truyen.setImageResource(quocGia.getHinh());
        TextView txt_TenQuocGia = (TextView)
                rowView.findViewById(R.id.txt_TenQuocGia);
        txt_TenQuocGia.setText((quocGia.getTen()));
        return rowView;
    }
}
