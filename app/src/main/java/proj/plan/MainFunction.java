package proj.plan;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFunction {

    protected void DayVisi(RecyclerView day)
    {
        if(day.getVisibility() == View.VISIBLE) {
            day.setVisibility(View.GONE);
        }
        else {
            day.setVisibility(View.VISIBLE);
        }
    }

    protected void SetList(RecyclerView rv, RecyclerView.LayoutManager lm, Context context, RecyclerView.Adapter adapter)
    {
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(context);

        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
