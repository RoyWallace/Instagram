package etong.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/9/17.
 */
public class CommentAdapter extends BaseAdapter {

    private Context context;

    private List<String> commentList = new ArrayList<String>();

    public CommentAdapter(Context context, List<String> commentList){
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if(view== null){
            view = LayoutInflater.from(context).inflate(R.layout.item_comment,null);
            holder = new ViewHolder();
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    class ViewHolder{
        ImageView avatarIv;

        TextView userNmaeTv;

        TextView commentTv;
    }
}
