package etong.instagram;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/9/17.
 */
public class MomentAdapter extends BaseAdapter implements ViewSwitcher.ViewFactory{

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
            holder.favorBtn = (ImageView) view.findViewById(R.id.heart_imageView);

            holder.favorNumberTs = (TextSwitcher) view.findViewById(R.id.favorNo_textView);
            holder.favorNumberTs.setFactory(this);

            Animation in = AnimationUtils.loadAnimation(context,
                    R.anim.slide_in_top);
            Animation out = AnimationUtils.loadAnimation(context,
                    R.anim.slide_out_bottom);
            holder.favorNumberTs.setInAnimation(in);
            holder.favorNumberTs.setOutAnimation(out);
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

        final ViewHolder finalHolder1 = holder;
        holder.favorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalHolder1.favorNumberTs.setText("13245");
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

    @Override
    public View makeView() {
        TextView t = new TextView(context);
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextSize(16);
        t.setTextColor(context.getResources().getColor(R.color.theme_color));
        return t;
    }

    class ViewHolder {
        ImageView avatarIv;

        TextView userNmaeTv;

        ImageView pictureIv;

        ImageView favorBtn;

        TextView commentTv;

        ImageView favorIv;

        ImageView commentIv;

        ImageView moreIv;

        TextSwitcher favorNumberTs;

    }
}
