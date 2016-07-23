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
import android.widget.RelativeLayout;
import android.widget.Spinner;

import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.Summoner;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    EditText inputSummonerName;
    Spinner regionServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSummonerName = (EditText) findViewById(R.id.summoners_name);
        Button searchSummoner = (Button) findViewById(R.id.search_summoner);

        regionServer = (Spinner) findViewById(R.id.server_region);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.region_server, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionServer.setAdapter(adapter);

        ApiManager.init(this.getApplicationContext());
        searchSummoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//hide keyboard on click
                hideKeyBoard();
               /* InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);*/


                String summonerName = inputSummonerName.getText().toString().trim();
                if (summonerName.isEmpty()) {
                   showSnackBar("You must type a summoner's name");
                } else {
                    String server = regionServer.getSelectedItem().toString().toLowerCase();
                    getSummonersByName(summonerName, server);
                }
            }
        });
    }

    public void hideKeyBoard() {
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.coordinator_layout);
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
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void getSummonersByName(String summonerName, String server) {

        summonerName = summonerName.replaceAll("\\s","");
        //Retrofit section start from here...
        ApiManager.setRegion(server);
        ApiManager.setSummonerName(summonerName);
        //System.out.println("Summoner name = " + summonerName);
        ApiManager.get().getSummonerByName(server, summonerName, ApiManager.getApiKey()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Summoner>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        if(e.getMessage().equals("404 Not Found")) {
                            showSnackBar("The summoner doesn't exist");
                        }

                    }

                    @Override
                    public final void onNext(Summoner response) {
                        /*System.out.println("Response = ");
                        System.out.println("{Name = \"" + response.getName() + "\",");
                        System.out.println("Id = \"" + response.getId() + "\",");
                        System.out.println("ProfileIconId = \"" + response.getProfileIconId() + "\",");
                        System.out.println("RevisionDate = \"" + response.getRevisionDate() + "\",");
                        System.out.println("SumonnerLevel = \"" + response.getSummonerLevel() + "\"}");*/
                        Intent mIntent = new Intent(getApplicationContext(), ProfilActivity.class);
                        mIntent.putExtra("Summoner", response);
                        startActivity(mIntent);
                    }
                });

    }
}
