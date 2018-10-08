package example.homework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Intent intent;
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Item> models;
    private Item model;

    public Adapter(Context context, List<Item> models) {
        this.context = context;
        this.models = models;
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
        model = models.get(position);
        holder.ivLogo.setImageBitmap(model.getImage());
        holder.tvName.setText(model.getName());
        holder.tvLocation.setText(model.getLocation());
        holder.tvFoundingDate.setText(model.getFoundingDate());
        holder.tvType.setText(model.getType());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivLogo;
        private final TextView tvName, tvLocation, tvFoundingDate, tvType;
        private Item model;
        private int position;

        ViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvFoundingDate = itemView.findViewById(R.id.tvFoundingDate);
            tvType = itemView.findViewById(R.id.tvType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                model = models.get(position);
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