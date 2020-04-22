package proj.plan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private ArrayList<PlanItem> mPlanList;
    private OnLongItemClickListener mListener;

    public interface OnLongItemClickListener
    {
        boolean onLongItemClick(int position, View view);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener listener)
    {
        mListener = listener;
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextTime;
        public TextView mTextSubject;
        public TextView mTextType;
        public TextView mTextTeacher;
        public TextView mTextRoom;

        public PlanViewHolder(@NonNull View itemView, final OnLongItemClickListener listener) {
            super(itemView);
            mTextTime = itemView.findViewById(R.id.tvTime);
            mTextSubject = itemView.findViewById(R.id.tvSubject);
            mTextType = itemView.findViewById(R.id.tvType);
            mTextTeacher = itemView.findViewById(R.id.tvTeacher);
            mTextRoom = itemView.findViewById(R.id.tvRoom);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    View src = (View) v.getParent();
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onLongItemClick(position, src);
                        }
                    }

                    return true;
                }
            });
        }
    }

    public PlanAdapter(ArrayList<PlanItem> planList)
    {
        mPlanList = planList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        PlanViewHolder evh = new PlanViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanItem currentItem = mPlanList.get(position);

        holder.mTextTime.setText(currentItem.getmTextTime());
        holder.mTextSubject.setText(currentItem.getmTextSubject());
        holder.mTextType.setText(currentItem.getmTextType());
        holder.mTextTeacher.setText(currentItem.getmTextTeacher());
        holder.mTextRoom.setText(currentItem.getmTextRoom());
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }
}
