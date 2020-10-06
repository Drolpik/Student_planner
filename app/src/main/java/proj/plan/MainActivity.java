package proj.plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static java.time.LocalDate.now;



public class MainActivity extends AppCompatActivity implements View.OnClickListener, SubDialog.SubDialogListener, PlanAdapter.OnLongItemClickListener, EditDialog.EditDialogListener {

    /**
     * Pola tekstowe z nazwami dni tygodnia
     */
    private TextView tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday;

    /**
     * Kontekst aplikacji do niektórych metod wbudowanych w SDK
     */
    private Context context;

    /**
     * Przyciski "plus"
     */
    private ImageView ivAdd, ivAdd2, ivAdd3, ivAdd4, ivAdd5;

    /**
     * Widoki zawierające listy przedmiotów oraz obiekt tymczasowy wybranego widoku
     */
    private RecyclerView rvMonday, rvTuesday, rvWednesday, rvThursday, rvFriday;
    private RecyclerView choiceRv;

    /**
     * Numer wybranego dnia
     */
    private int chosen;

    /**
     * Adapter zarządza danymi w listach oraz informuje o zmianach oraz obiekt tymczasowy wybranego adaptera
     */
    private PlanAdapter adapterMonday, adapterTuesday, adapterWednesday, adapterThursday, adapterFriday;
    private PlanAdapter choiceAdapter;

    /**
     * -- TODO --
     */
    private RecyclerView.LayoutManager lmMonday, lmTuesday, lmWednesday, lmThursday, lmFriday;

    /**
     * Listy zawierające przedmioty posegregowane według dni tygodnia oraz obiekty tymczasowe wybranej listy do dodawania, usuwania i edycji
     */
    private ArrayList<PlanItem> planMonday, planTuesday, planWednesday, planThursday, planFriday;
    private ArrayList<PlanItem> choiceList;
    private ArrayList<PlanItem> choiceListAction;

    /**
     * Obiekt "belki" pojawiającej się w programie, gdy użytkownik zmienia lub usuwa elmenty planu
     */
    private ActionMode mActionMode;

    /**
     * Identyfikator pozycji wybranego elementu listy
     */
    private int position_item;

    /**
     * Obiekt zarządzający wyświetlaniem i ukrywaniem list
     */
    private MainFunction rvVisi = new MainFunction();

    /**
     * Ciąg tekstowy identyfikujący użytkownika przy połączeniu z bazą danych
     */
    private static String USERID;

    /**
     * Tabele list, widoków i adapterów
     */
    final ArrayList[] a = new ArrayList[5];
    final RecyclerView[] b = new RecyclerView[5];
    final PlanAdapter[] AD = new PlanAdapter[5];

    /**
     * Numer dnia do automatycznego pokazywania listy zajęć z danego dnia
     */
    public static int dayOf = now().getDayOfWeek().getValue();

    /**
     * Obiekt ProgressBar
     */
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        USERID = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        pb = findViewById(R.id.indeterminateBar);
        pb.setVisibility(View.VISIBLE);

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
        alTuesday.SetList(rvTuesday, lmTuesday, this, adapterTuesday);

        //Wednesday Arraylist
        MainFunction alWednesday = new MainFunction();
        planWednesday = new ArrayList<>();
        adapterWednesday = new PlanAdapter(planWednesday);
        alWednesday.SetList(rvWednesday, lmWednesday, this, adapterWednesday);

        //Wednesday Arraylist
        MainFunction alThursday = new MainFunction();
        planThursday = new ArrayList<>();
        adapterThursday = new PlanAdapter(planThursday);
        alThursday.SetList(rvThursday, lmThursday, this, adapterThursday);

        //Friday Arraylist
        MainFunction alFriday = new MainFunction();
        planFriday = new ArrayList<>();
        adapterFriday = new PlanAdapter(planFriday);
        alFriday.SetList(rvFriday, lmFriday, this, adapterFriday);

        //deleting and editing plan
        adapterMonday.setOnLongItemClickListener(this);
        adapterTuesday.setOnLongItemClickListener(this);
        adapterWednesday.setOnLongItemClickListener(this);
        adapterThursday.setOnLongItemClickListener(this);
        adapterFriday.setOnLongItemClickListener(this);

        a[0] = planMonday;
        a[1] = planTuesday;
        a[2] = planWednesday;
        a[3] = planThursday;
        a[4] = planFriday;

        b[0] = rvMonday;
        b[1] = rvTuesday;
        b[2] = rvWednesday;
        b[3] = rvThursday;
        b[4] = rvFriday;

        AD[0] = adapterMonday;
        AD[1] = adapterTuesday;
        AD[2] = adapterWednesday;
        AD[3] = adapterThursday;
        AD[4] = adapterFriday;

        planMonday.clear();
        planTuesday.clear();
        planWednesday.clear();
        planThursday.clear();
        planFriday.clear();

        load();
    }

    /**
     * Funkcja pobierająca z bazy informacje i wyświetlająca je po uruchomieniu programu
     */
    public void load() {

        planMonday.clear();
        planTuesday.clear();
        planWednesday.clear();
        planThursday.clear();
        planFriday.clear();

        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://planer.nosiu.pl/index.php?uid=" + USERID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //planMonday.add(new PlanItem("0", "a", "b", "c", "d"));
                        try {
                            JSONObject jo = new JSONObject(response);
                            for (int i = 1; i <= 5; i++) {
                                a[i - 1].clear();
                                b[i - 1].setVisibility(View.GONE);
                                JSONArray ja = jo.getJSONArray(String.valueOf(i));
                                for (int j = 0; j < ja.length(); j++) {
                                    JSONObject o = ja.getJSONObject(j);
                                    //System.out.println(o.getString("teacher"));
                                    String sub = o.getString("subject");
                                    String[] subs;
                                    if (sub.matches("(\\w*\\s)+")) {
                                        subs = sub.split("\\s");
                                        sub = "";
                                        for (int k = 0; k < subs.length; k++) {
                                            if (subs[k].length() > 2)
                                                sub += subs[k].substring(0, 3);
                                        }
                                    }
                                    //else sub = sub.substring(0,8);

                                    a[i - 1].add(new PlanItem(o.getString("start").substring(0, 5),
                                            sub,
                                            o.getString("type"),
                                            o.getString("teacher").replaceFirst("[a-ząćęółżź]+", ""),
                                            o.getString("classroom"),
                                            o.getString("day"),
                                            o.getString("id"))
                                    );
                                }
                            }

                            if (dayOf > 0 && dayOf < 6) {
                                rvVisi.DayVisi(b[dayOf - 1]);
                            }

                            pb.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Blad sieci", Toast.LENGTH_SHORT);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue rq = Volley.newRequestQueue(context);
        rq.add(sr);
    }

    /**
     * Otwiera okienko dodające przedmiot do listy
     */
    public void openSubDialog() {
        SubDialog subDialog = new SubDialog();
        subDialog.show(getSupportFragmentManager(), "subject dialog");
    }

    /**
     * Wprowadza zapisane dane do edytowanego zajęcia w widoku oraz przekazuje do bazy
     * @param Name Nazwa przedmiotu
     * @param Teacher Inicjały nauczyciela
     * @param Time Czas rozpoczęcia
     * @param Type Rodzaj zajęć
     * @param Room Sala
     */
    @Override
    public void applyTexts(final String Name, final String Teacher, final String Time, final String Type, final String Room) {

        rvVisi.DayVisi(choiceRv);
        pb.setVisibility(View.VISIBLE);

        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://planer.nosiu.pl/index.php?add&uid=" + USERID
                        + "&subject=" + Name
                        + "&teacher=" + Teacher
                        + "&type=" + Type
                        + "&start=" + Time + ":00"
                        + "&day=" + String.valueOf(chosen)
                        + "&classroom=" + Room,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        final String textTeacher = (Teacher.substring(0, 1).toUpperCase() + Teacher.substring(1)).replaceFirst("[a-ząćęółżź]+", "");
                        for (int ins = 0; ins < choiceList.size(); ins++) {
                            int war_1 = Integer.parseInt(choiceList.get(ins).getmTextTime().replace(":", ""));
                            int war_2 = Integer.parseInt(Time.replace(":", ""));
                            if (war_1 > war_2) {
                                a[chosen - 1].add(ins, new PlanItem(
                                        Time, Name, Type, textTeacher, Room, String.valueOf(chosen), response
                                ));
                                pb.setVisibility(View.GONE);
                                AD[chosen - 1].notifyDataSetChanged();
                                return;
                            }
                        }
                        a[chosen - 1].add(new PlanItem(
                                Time, Name, Type, textTeacher, Room, String.valueOf(chosen), response
                        ));
                        AD[chosen - 1].notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                        return;
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Blad sieci", Toast.LENGTH_SHORT);
            }
        });

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }

    /**
     * Otwiera okienko edytujące zajęcie
     * @param choiceListAction Wybrana lista
     * @param position_item Pozycja na liście
     */
    public void openEditDialog(ArrayList<PlanItem> choiceListAction, int position_item) {
        EditDialog editDialog = new EditDialog(choiceListAction, position_item);
        editDialog.show(getSupportFragmentManager(), "edit dialog");
    }

    /**
     * Pobiera od użytkownika dane do zapisania
     * @param Name Nazwa przedmiotu
     * @param Teacher Inicjały nauczyciela
     * @param Time Czas rozpoczęcia
     * @param Type Rodzaj zajęć
     * @param Room Sala
     */
    @Override
    public void saveEditTexts(final String Name, final String Teacher, final String Time, final String Type, final String Room) {

        pb.setVisibility(View.VISIBLE);

        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://planer.nosiu.pl/index.php?edit&uid=" + USERID
                        + "&id=" + choiceListAction.get(position_item).getmID()
                        + "&subject=" + Name
                        + "&teacher=" + Teacher
                        + "&type=" + Type
                        + "&start=" + Time + ":00"
                        + "&day=" + choiceListAction.get(position_item).getmDay()
                        + "&classroom=" + Room,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("OK")) {
                            choiceListAction.get(position_item).changeSubject(Name);
                            choiceListAction.get(position_item).changeTeacher(Teacher);
                            choiceListAction.get(position_item).changeTime(Time);
                            choiceListAction.get(position_item).changeType(Type);
                            choiceListAction.get(position_item).changeRoom(Room);
                            choiceAdapter.notifyDataSetChanged();

                            pb.setVisibility(View.GONE);
                            rvVisi.DayVisi(choiceRv);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Blad sieci", Toast.LENGTH_SHORT);
            }
        });

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }

    /**
     * Pokazuje użytkownikowi wybrany dzień, zamykając resztę widoków po tapnięciu
     * @param v Wybarny widok (lista)
     */
    @Override
    public void onClick(View v) {
        MainFunction day = new MainFunction();
        MainFunction gone = new MainFunction();
        switch (v.getId()) {
            case R.id.tvMonday:
                day.DayVisi(rvMonday);
                gone.GoneRV(rvTuesday, rvWednesday, rvThursday, rvFriday);
                chosen = 1;
                break;
            case R.id.tvTuesday:
                day.DayVisi(rvTuesday);
                gone.GoneRV(rvMonday, rvWednesday, rvThursday, rvFriday);
                chosen = 2;
                break;
            case R.id.tvWednesday:
                day.DayVisi(rvWednesday);
                gone.GoneRV(rvMonday, rvTuesday, rvThursday, rvFriday);
                chosen = 3;
                break;
            case R.id.tvThursday:
                day.DayVisi(rvThursday);
                gone.GoneRV(rvMonday, rvTuesday, rvWednesday, rvFriday);
                chosen = 4;
                break;
            case R.id.tvFriday:
                day.DayVisi(rvFriday);
                gone.GoneRV(rvMonday, rvTuesday, rvWednesday, rvThursday);
                chosen = 5;
                break;
            case R.id.ivAdd1:
                openSubDialog();
                choiceList = planMonday;
                choiceRv = rvMonday;
                chosen = 1;
                break;
            case R.id.ivAdd2:
                openSubDialog();
                choiceList = planTuesday;
                choiceRv = rvTuesday;
                chosen = 2;
                break;
            case R.id.ivAdd3:
                openSubDialog();
                choiceList = planWednesday;
                choiceRv = rvWednesday;
                chosen = 3;
                break;
            case R.id.ivAdd4:
                openSubDialog();
                choiceList = planThursday;
                choiceRv = rvThursday;
                chosen = 4;
                break;
            case R.id.ivAdd5:
                openSubDialog();
                choiceList = planFriday;
                choiceRv = rvFriday;
                chosen = 5;
                break;
        }
    }

    /**
     * Funkcja zwrotna ActionMode
     */
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.long_touch_menu, menu);
            mode.setTitle(R.string.select_option);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        /**
         * Pobiera od użytkownika akcję przycisku usuń wraz z pozycją obiektu, następnie usuwa go i przekazuje informacje do serwera
         * @param mode Referencja ActionMode
         * @param item Wybrany obiekt
         * @return
         */
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.option_delete:

                    final PlanItem ids = (PlanItem) a[chosen - 1].get(position_item);
                    StringRequest sr = new StringRequest(Request.Method.GET,
                            "https://planer.nosiu.pl/index.php?remove&uid=" + USERID + "&id=" + ids.getmID(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.contains("OK")) {
                                        a[chosen - 1].remove(position_item);
                                        choiceAdapter.notifyDataSetChanged();
                                        Toast.makeText(MainActivity.this, R.string.delete_toast, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });

                    RequestQueue rq = Volley.newRequestQueue(context);
                    rq.add(sr);


                    mode.finish();
                    return true;
                case R.id.option_edit:
                    Toast.makeText(MainActivity.this, R.string.edit_toast, Toast.LENGTH_SHORT).show();
                    openEditDialog(a[chosen - 1], position_item);
                    choiceAdapter.notifyDataSetChanged();
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

    /**
     * Akcje długiego przytrzymania
     * @param position Pozycja obiektu na liście
     * @param parent Rodzic obiektu (lista)
     * @return
     */
    @Override
    public boolean onLongItemClick(int position, View parent) {
        //checks if actionmode is currently open
        if (mActionMode != null) {
            return false;
        }

        position_item = position;

        switch (parent.getId()) {
            case R.id.rvMonday:
                choiceListAction = planMonday;
                choiceAdapter = adapterMonday;
                chosen = 1;
                break;
            case R.id.rvTuesday:
                choiceListAction = planTuesday;
                choiceAdapter = adapterTuesday;
                chosen = 2;
                break;
            case R.id.rvWednesday:
                choiceListAction = planWednesday;
                choiceAdapter = adapterWednesday;
                chosen = 3;
                break;
            case R.id.rvThursday:
                choiceListAction = planThursday;
                choiceAdapter = adapterThursday;
                chosen = 4;
                break;
            case R.id.rvFriday:
                choiceListAction = planFriday;
                choiceAdapter = adapterFriday;
                chosen = 5;
                break;
        }

        mActionMode = startActionMode(mActionModeCallback);
        return true;
    }
}