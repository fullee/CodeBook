package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icngor.codebook.R;

import java.util.List;

import DataBeans.BooksInfo;

/**
 * Created by root on 2015/12/4.
 */
public class MainAdapter extends BaseAdapter {
    private List<BooksInfo> list;
    private Context context;
    public MainAdapter(Context context,List<BooksInfo> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BooksInfo booksInfo = list.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.indexbeanitem,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        imageView.setImageResource(booksInfo.getPic());
        textView.setText(booksInfo.getTitle());
        return view;
    }
}
