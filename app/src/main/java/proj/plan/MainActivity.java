package proj.plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SubDialog.SubDialogListener, PlanAdapter.OnLongItemClickListener {

    //TextView
    private TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday;

    //ImageView
    private ImageView ivAdd, ivAdd2, ivAdd3, ivAdd4, ivAdd5;

    //RecyclerView
    private RecyclerView rvMonday, rvTuesday, rvWednesday, rvThursday, rvFriday;

    //Adapter
    //private RecyclerView.Adapter adapterMonday, adapterTuesday, adapterWednesday, adapterThursday, adapterFriday;
    private PlanAdapter adapterMonday, adapterTuesday, adapterWednesday, adapterThursday, adapterFriday;
    private PlanAdapter choiceAdapter;

    //LayoutManager
    private RecyclerView.LayoutManager lmMonday, lmTuesday, lmWednesday, lmThursday, lmFriday;;

    //ArrayList
    private ArrayList<PlanItem> planMonday, planTuesday, planWednesday, planThursday, planFriday;
    private ArrayList<PlanItem> choiceList = new ArrayList<>();
    private ArrayList<PlanItem> choiceListAction = new ArrayList<>();

    //ActionMode
    private ActionMode mActionMode;
    private int position_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView findViewById
        rvMonday = findViewById(R.id.rvMonday);
        rvTuesday = findViewById(R.id.rvTuesday);
        rvWednesday = findViewById(R.id.rvWednesday);
        rvThursday = findViewById(R.id.rvThursday);
        rvFriday = findViewById(R.id.rvFriday);

        //TextView findViewById
        tvMonday = findViewById(R.id.tvMonday);
        tvTuesday = findViewById(R.id.tvTuesday);
        tvWednesday = findViewById(R.id.tvWednesday);
        tvThursday = findViewById(R.id.tvThursday);
        tvFriday = findViewById(R.id.tvFriday);

        //ImageView findViewById
        ivAdd = findViewById(R.id.ivAdd1);
        ivAdd2 = findViewById(R.id.ivAdd2);
        ivAdd3 = findViewById(R.id.ivAdd3);
        ivAdd4 = findViewById(R.id.ivAdd4);
        ivAdd5 = findViewById(R.id.ivAdd5);

        //sets the RecyclerView start visibility to GONE
        rvMonday.setVisibility(View.GONE);
        rvTuesday.setVisibility(View.GONE);
        rvWednesday.setVisibility(View.GONE);
        rvThursday.setVisibility(View.GONE);
        rvFriday.setVisibility(View.GONE);

        //opens the given day of the week from the plan
        tvMonday.setOnClickListener(this);
        tvTuesday.setOnClickListener(this);
        tvWednesday.setOnClickListener(this);
        tvThursday.setOnClickListener(this);
        tvFriday.setOnClickListener(this);

        //Adding a new activity to the plan
        ivAdd.setOnClickListener(this);
        ivAdd2.setOnClickListener(this);
        ivAdd3.setOnClickListener(this);
        ivAdd4.setOnClickListener(this);
        ivAdd5.setOnClickListener(this);

        //Monday ArrayList
        MainFunction alMonday = new MainFunction();
        planMonday = new ArrayList<>();
        adapterMonday = new PlanAdapter(planMonday);
        alMonday.SetList(rvMonday, lmMonday, this, adapterMonday);

        //Tuesday Arraylist
        MainFunction alTuesday = new MainFunction();
        planTuesday = new ArrayList<>();
        adapterTuesday = new PlanAdapter(planTuesday);
        alTuesday.SetList(rvTuesday, lmTuesday,this, adapterTuesday);

        //Wednesday Arraylist
        MainFunction alWednesday = new MainFunction();
        planWednesday = new ArrayList<>();
        adapterWednesday = new PlanAdapter(planWednesday);
        alWednesday.SetList(rvWednesday, lmWednesday,this, adapterWednesday);

        //Wednesday Arraylist
        MainFunction alThursday = new MainFunction();
        planThursday = new ArrayList<>();
        adapterThursday = new PlanAdapter(planThursday);
        alThursday.SetList(rvThursday, lmThursday,this, adapterThursday);

        //Friday Arraylist
        MainFunction alFriday = new MainFunction();
        planFriday = new ArrayList<>();
        adapterFriday = new PlanAdapter(planFriday);
        alFriday.SetList(rvFriday, lmFriday,this, adapterFriday);

        //deleting and editing plan
        adapterMonday.setOnLongItemClickListener(this);
        adapterTuesday.setOnLongItemClickListener(this);
        adapterWednesday.setOnLongItemClickListener(this);
        adapterThursday.setOnLongItemClickListener(this);
        adapterFriday.setOnLongItemClickListener(this);
    }

    //The dialog opens after pressing the add button
    public void openDialog()
    {
        SubDialog subDialog = new SubDialog();
        subDialog.show(getSupportFragmentManager(), "subject dialog");
    }

    //receives from SubDialog completed data by the user and adds to the plan
    @Override
    public void applyTexts(String Name, String Teacher, String Time, String Type, String Room) {
        choiceList.add(new PlanItem(Time, Name, Type, Teacher, Room));
    }

    //OnClick methods
    @Override
    public void onClick(View v) {
        MainFunction day = new MainFunction();

        switch (v.getId())
        {
            case R.id.tvMonday:
                day.DayVisi(rvMonday);
                break;
            case R.id.tvTuesday:
                day.DayVisi(rvTuesday);
                break;
            case R.id.tvWednesday:
                day.DayVisi(rvWednesday);
                break;
            case R.id.tvThursday:
                day.DayVisi(rvThursday);
                break;
            case R.id.tvFriday:
                day.DayVisi(rvFriday);
                break;
            case R.id.ivAdd1:
                openDialog();
                choiceList = planMonday;
                break;
            case R.id.ivAdd2:
                openDialog();
                choiceList = planTuesday;
                break;
            case R.id.ivAdd3:
                openDialog();
                choiceList = planWednesday;
                break;
            case R.id.ivAdd4:
                openDialog();
                choiceList = planThursday;
                break;
            case R.id.ivAdd5:
                openDialog();
                choiceList = planFriday;
                break;
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.long_touch_menu, menu);
            mode.setTitle("Wybierz opcję");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.option_delete:
                    //planMonday.remove(position_item);
                    //adapterMonday.notifyDataSetChanged();
                    choiceListAction.remove(position_item);
                    choiceAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Usunięto", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.option_edit:
                    Toast.makeText(MainActivity.this, "Otwarto edycje", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    public boolean onLongItemClick(int position, View view) {
        //checks if actionmode is currently open
        if(mActionMode != null)
        {
            return false;
        }

        position_item = position;

        switch (view.getId())
        {
            case R.id.rvMonday:
                choiceListAction = planMonday;
                choiceAdapter = adapterMonday;
                Toast.makeText(this, "AAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rvTuesday:

            case R.id.rvWednesday:

                break;
            case R.id.rvThursday:

                break;
            case R.id.rvFriday:

                break;
        }

        mActionMode = startActionMode(mActionModeCallback);
        return true;
    }
}