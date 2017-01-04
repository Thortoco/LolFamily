package corentin_evanno.lolfamily.View;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.API.StaticDataAPI;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText inputSummonerName;
    Spinner regionServer;
    Button searchSummoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSummonerName = (EditText) findViewById(R.id.summoners_name);
        searchSummoner = (Button) findViewById(R.id.search_summoner);

        regionServer = (Spinner) findViewById(R.id.server_region);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.region_server, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionServer.setAdapter(adapter);
        ApiManager.init(this.getApplicationContext());
        ApiManager.setSummonerName("");
        StaticDataAPI.getInstance();
        searchSummoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                String summonerName = inputSummonerName.getText().toString().trim();
                if (summonerName.isEmpty()) {
                    showSnackBar("You must type a summoner's name");
                } else {
                    searchSummoner.setEnabled(false);
                    String server = regionServer.getSelectedItem().toString().toLowerCase();
                    getSummonersByName(summonerName, server);
                }
            }
        });
    }

    public void hideKeyBoard() {
        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.coordinator_layout);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(coordinatorLayout.getWindowToken(), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (inputSummonerName != null && inputSummonerName.isFocused()) {
                inputSummonerName.clearFocus();
                hideKeyBoard();
            }
        }
        return super.dispatchTouchEvent(event);
    }

    void showSnackBar(String message) {
        LinearLayout coordinatorLayout = (LinearLayout) findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void getSummonersByName(String summonerName, final String server) {
        summonerName = summonerName.replaceAll("\\s", "");
        ApiManager.setSummonerName(summonerName);
        String url = "https://" + server + ".api.pvp.net/api/lol/" + server + "/v1.4/summoner/by-name/" + summonerName;

        ApiManager.get().getSummonerByName(url, ApiManager.getApiKey()).enqueue(new Callback<Summoner>() {
            @Override
            public void onResponse(Call<Summoner> call, Response<Summoner> response) {
                if (response.isSuccessful()) {
                    ApiManager.setRegion(server);
                    Intent mIntent = new Intent(getApplicationContext(), ProfilActivity.class);
                    Summoner summoner = response.body();
                    mIntent.putExtra("Summoner", summoner);
                    startActivity(mIntent);
                } else {
                    searchSummoner.setEnabled(true);
                    showSnackBar("Summoner doesn't exist");
                }
            }

            @Override
            public void onFailure(Call<Summoner> call, Throwable t) {
                searchSummoner.setEnabled(true);
                System.out.println("[Failure] getSummonersByName : " + t.getMessage());
                showSnackBar("Network problem");
            }
        });
    }
}
