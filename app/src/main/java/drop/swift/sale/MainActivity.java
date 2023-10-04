package drop.swift.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationRail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationRail = findViewById(R.id.navigation_rail);

        if (savedInstanceState == null) {
            openFragment(new HomeFragment());
            navigationRail.setSelectedItemId(R.id.home_tab);
        }

        navigationRail.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_tab:
                    openFragment(new HomeFragment());
                    return true;
                case R.id.order_tab:
                    openFragment(new OrderFragment());
                    return true;
                case R.id.report_tab:
                    openFragment(new ReportFragment());
                    return true;
            }
            return false;
        });

    }

    private void openFragment(Fragment destination) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, destination).commit();
    }
}