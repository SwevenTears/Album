package luoluna.sweven.album.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sweven.base.BaseRecyclerAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import luoluna.sweven.album.R;
import luoluna.sweven.album.entity.local.Image;

public class PictureLookAdapter extends BaseRecyclerAdapter<Image> {

    public PictureLookAdapter(Activity activity, List<Image> list) {
        super(activity, list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_picture_look, parent, false);
        return new PictureLookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PictureLookViewHolder holder = (PictureLookViewHolder) viewHolder;
        Image image = list.get(position);

        holder.picture.setImageURI(Uri.parse(image.getUri()));
    }

    public class PictureLookViewHolder extends ViewHolder {

        private ImageView picture;

        public PictureLookViewHolder(@NonNull View view) {
            super(view);
            picture = view.findViewById(R.id.picture);

            picture.setOnClickListener(v -> {
                if (onClickItemListener != null) {
                    onClickItemListener.onClick(list.get(getAdapterPosition()));
                }
            });
            picture.setOnLongClickListener(v -> {
                if (menuListener != null) {
                    menuListener.onMenu(v, list.get(getAdapterPosition()));
                }
                return true;
            });
        }
    }

    private onMenuListener menuListener;

    public void setOnMenuListener(onMenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public interface onMenuListener {
        void onMenu(View view, Image image);
    }
}
