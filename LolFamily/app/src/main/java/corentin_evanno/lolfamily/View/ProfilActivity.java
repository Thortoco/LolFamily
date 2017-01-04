package corentin_evanno.lolfamily.View;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import corentin_evanno.lolfamily.Fragment.ChampionsFragment;
import corentin_evanno.lolfamily.Fragment.MatchHistoryFragment;
import corentin_evanno.lolfamily.Fragment.SummaryFragment;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.Summoner;

public class ProfilActivity extends AppCompatActivity {

    private Summoner summoner;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setElevation(0);
        if (getIntent().getSerializableExtra("Summoner") != null) {
            this.summoner = (Summoner) getIntent().getSerializableExtra("Summoner");
            actionBar.setTitle(summoner.getName());
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }}

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setSummoner(summoner);
        adapter.addFragment(new SummaryFragment(), "SUMMARY");
        adapter.addFragment(new MatchHistoryFragment(), "MATCH HISTORY");
        adapter.addFragment(new MatchHistoryFragment(), "CHAMPIONS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private Summoner summoner;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public void setSummoner(Summoner summoner) {
            this.summoner = summoner;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Summoner", summoner);
                    SummaryFragment summaryFragment = new SummaryFragment();
                    summaryFragment.setArguments(bundle);
                    return summaryFragment;
                case 1:
                    Bundle bundleHistory = new Bundle();
                    bundleHistory.putSerializable("Summoner", summoner);
                    MatchHistoryFragment historyFragment = new MatchHistoryFragment();
                    historyFragment.setArguments(bundleHistory);
                    return historyFragment;
                case 2:
                    Bundle bundleChampions = new Bundle();
                    bundleChampions.putSerializable("Summoner", summoner);
                    ChampionsFragment championsFragment = new ChampionsFragment();
                    championsFragment.setArguments(bundleChampions);
                    return championsFragment;
            }
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
