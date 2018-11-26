package example.homework.classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import example.homework.R;
import example.homework.activities.DetailActivity;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private ListItem model;
    private Intent intent;
    private Context context;
    private LayoutInflater inflater;
    private List<ListItem> items;

    public CarListAdapter(Context context, List<ListItem> items) {
        this.items = items;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        intent = new Intent(context, DetailActivity.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        model = items.get(position);
        holder.ivLogo.setImageBitmap(model.getImage());
        holder.tvName.setText(model.getName());
        holder.tvLocation.setText(model.getLocation());
        holder.tvFoundingDate.setText(model.getFoundingDate() + "");
        holder.tvType.setText(model.getType());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivLogo;
        private final TextView tvName, tvLocation, tvFoundingDate, tvType;
        private ListItem model;
        private int position;

        ViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.iv_logo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvFoundingDate = itemView.findViewById(R.id.tv_founding_date);
            tvType = itemView.findViewById(R.id.tv_type);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                model = items.get(position);
                intent.putExtra("imageId", model.getImageId());
                intent.putExtra("name", model.getName());
                intent.putExtra("location", model.getLocation());
                intent.putExtra("foundingDate", model.getFoundingDate());
                intent.putExtra("keyFigures", model.getKeyFigures());
                context.startActivity(intent);
            }
        }
    }
}
