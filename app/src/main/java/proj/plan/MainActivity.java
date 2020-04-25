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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SubDialog.SubDialogListener, PlanAdapter.OnLongItemClickListener, EditDialog.EditDialogListener {

    //TextView
    private TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday;

    //ImageView
    private ImageView ivAdd, ivAdd2, ivAdd3, ivAdd4, ivAdd5;

    //RecyclerView
    private RecyclerView rvMonday, rvTuesday, rvWednesday, rvThursday, rvFriday;
    private RecyclerView choiceRv;

    //Adapter
    private PlanAdapter adapterMonday, adapterTuesday, adapterWednesday, adapterThursday, adapterFriday;
    private PlanAdapter choiceAdapter;

    //LayoutManager
    private RecyclerView.LayoutManager lmMonday, lmTuesday, lmWednesday, lmThursday, lmFriday;;

    //ArrayList
    private ArrayList<PlanItem> planMonday, planTuesday, planWednesday, planThursday, planFriday;
    private ArrayList<PlanItem> choiceList;
    private ArrayList<PlanItem> choiceListAction;

    //ActionMode
    private ActionMode mActionMode;

    //to set item position in onLongClick
    private int position_item;

    //to run VisibleRV method
    private MainFunction rvVisi = new MainFunction();

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
    public void openSubDialog()
    {
        SubDialog subDialog = new SubDialog();
        subDialog.show(getSupportFragmentManager(), "subject dialog");
    }

    //receives from SubDialog completed data by the user and adds to the plan
    @Override
    public void applyTexts(String Name, String Teacher, String Time, String Type, String Room) {
        choiceList.add(new PlanItem(Time, Name, Type, Teacher, Room));
        rvVisi.VisibleRV(choiceRv);
    }

    //The dialog opens after pressing the edit option
    public void openEditDialog(ArrayList<PlanItem> choiceListAction, int position_item)
    {
        EditDialog editDialog = new EditDialog(choiceListAction, position_item);
        editDialog.show(getSupportFragmentManager(),"edit dialog");
    }

    //receives from EditDialog completed data by the user and saves changes
    @Override
    public void saveEditTexts(String Name, String Teacher, String Time, String Type, String Room) {
        choiceListAction.get(position_item).changeSubject(Name);
        choiceListAction.get(position_item).changeTeacher(Teacher);
        choiceListAction.get(position_item).changeTime(Time);
        choiceListAction.get(position_item).changeType(Type);
        choiceListAction.get(position_item).changeRoom(Room);
        choiceAdapter.notifyDataSetChanged();
    }

    //OnClick methods
    @Override
    public void onClick(View v) {
        MainFunction day = new MainFunction();
        MainFunction gone = new MainFunction();
        switch (v.getId())
        {
            case R.id.tvMonday:
                day.DayVisi(rvMonday);
                gone.GoneRV(rvTuesday, rvWednesday, rvThursday, rvFriday);
                break;
            case R.id.tvTuesday:
                day.DayVisi(rvTuesday);
                gone.GoneRV(rvMonday ,rvWednesday, rvThursday, rvFriday);
                break;
            case R.id.tvWednesday:
                day.DayVisi(rvWednesday);
                gone.GoneRV(rvMonday, rvTuesday, rvThursday, rvFriday);
                break;
            case R.id.tvThursday:
                day.DayVisi(rvThursday);
                gone.GoneRV(rvMonday, rvTuesday, rvWednesday, rvFriday);
                break;
            case R.id.tvFriday:
                day.DayVisi(rvFriday);
                gone.GoneRV(rvMonday, rvTuesday, rvWednesday, rvThursday);
                break;
            case R.id.ivAdd1:
                openSubDialog();
                choiceList = planMonday;
                choiceRv = rvMonday;
                break;
            case R.id.ivAdd2:
                openSubDialog();
                choiceList = planTuesday;
                choiceRv = rvTuesday;
                break;
            case R.id.ivAdd3:
                openSubDialog();
                choiceList = planWednesday;
                choiceRv = rvWednesday;
                break;
            case R.id.ivAdd4:
                openSubDialog();
                choiceList = planThursday;
                choiceRv = rvThursday;
                break;
            case R.id.ivAdd5:
                openSubDialog();
                choiceList = planFriday;
                choiceRv = rvFriday;
                break;
        }
    }

    //=============================================================================
    //ACTIONMODE:
    //=============================================================================
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.long_touch_menu, menu);
            mode.setTitle(R.string.select_option);
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
                    choiceListAction.remove(position_item);
                    choiceAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, R.string.delete_toast, Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.option_edit:
                    Toast.makeText(MainActivity.this, R.string.edit_toast, Toast.LENGTH_SHORT).show();
                    openEditDialog(choiceListAction, position_item);
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
    //=============================================================================

    //OnLongItemClick methods
    @Override
    public boolean onLongItemClick(int position, View parent) {
        //checks if actionmode is currently open
        if(mActionMode != null)
        {
            return false;
        }

        position_item = position;

        switch (parent.getId())
        {
            case R.id.rvMonday:
                choiceListAction = planMonday;
                choiceAdapter = adapterMonday;
                break;
            case R.id.rvTuesday:
                choiceListAction = planTuesday;
                choiceAdapter = adapterTuesday;
            case R.id.rvWednesday:
                choiceListAction = planWednesday;
                choiceAdapter = adapterWednesday;
                break;
            case R.id.rvThursday:
                choiceListAction = planThursday;
                choiceAdapter = adapterThursday;
                break;
            case R.id.rvFriday:
                choiceListAction = planFriday;
                choiceAdapter = adapterFriday;
                break;
        }

        mActionMode = startActionMode(mActionModeCallback);
        return true;
    }
}