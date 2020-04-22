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

    protected void VisibleRV(RecyclerView rv)
    {
        rv.setVisibility(View.VISIBLE);
    }

    protected void GoneRV(RecyclerView rv1, RecyclerView rv2, RecyclerView rv3, RecyclerView rv4)
    {
        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.GONE);
    }

    protected void SetList(RecyclerView rv, RecyclerView.LayoutManager lm, Context context, RecyclerView.Adapter adapter)
    {
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(context);

        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
