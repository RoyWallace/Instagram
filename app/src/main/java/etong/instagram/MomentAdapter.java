package etong.instagram;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/9/17.
 */
public class MomentAdapter extends BaseAdapter {

    private Context context;

    private List<String> momentList = new ArrayList<String>();

    public MomentAdapter(Context context, List<String> momentList) {
        this.context = context;
        this.momentList = momentList;
    }

    @Override
    public int getCount() {
        return momentList.size();
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_moment, null);
            holder = new ViewHolder();
            holder.moreIv = (ImageView) view.findViewById(R.id.more_imageView);
            holder.commentTv = (TextView) view.findViewById(R.id.comment_textView);
            holder.avatarIv = (ImageView) view.findViewById(R.id.avatar_imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ViewHolder finalHolder = holder;
        holder.moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.show();
            }
        });
        holder.commentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).addFragment(new CommentListFragment());
            }
        });

        holder.avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                ((MainActivity) context).addFragment(new ProfileFragment(location[0]+view.getWidth()/2, location[1]-(int)(48*Uitl.getDensity(context))));
            }
        });

        return view;
    }

    class ViewHolder {
        ImageView avatarIv;

        TextView userNmaeTv;

        ImageView pictureIv;

        TextView commentTv;

        ImageView favorIv;

        ImageView commentIv;

        ImageView moreIv;

        TextView favorNumberTv;

    }
}
