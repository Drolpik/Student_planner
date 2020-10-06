package proj.plan;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFunction {

    /**
     * Ustawianie widoczności widoku
     * @param day Wybrany widok
     */
    protected void DayVisi(RecyclerView day)
    {
        if(day.getVisibility() == View.VISIBLE) {
            day.setVisibility(View.GONE);
        }
        else {
            day.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Ukrywanie list
     * @param rv1
     * @param rv2
     * @param rv3
     * @param rv4
     */
    protected void GoneRV(RecyclerView rv1, RecyclerView rv2, RecyclerView rv3, RecyclerView rv4)
    {
        rv1.setVisibility(View.GONE);
        rv2.setVisibility(View.GONE);
        rv3.setVisibility(View.GONE);
        rv4.setVisibility(View.GONE);
    }

    /**
     * Ustawiania adapterów, managerów widoku
     * @param rv
     * @param lm
     * @param context
     * @param adapter
     */
    protected void SetList(RecyclerView rv, RecyclerView.LayoutManager lm, Context context, RecyclerView.Adapter adapter)
    {
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(context);

        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
