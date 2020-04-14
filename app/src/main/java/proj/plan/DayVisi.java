package proj.plan;

import android.view.View;
import android.widget.ListView;

public class DayVisi
{
    protected void DayFunc(ListView day)
    {
        if(day.getVisibility() == View.VISIBLE) {
            day.setVisibility(View.GONE);
        }
        else {
            day.setVisibility(View.VISIBLE);
        }
    }
}
