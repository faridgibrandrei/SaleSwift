package drop.swift.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.firebase.FirebaseApp;

import drop.swift.sale.manager.OngoingOrderManager;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationRail;
    public static OngoingOrderManager ongoingOrderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        navigationRail = findViewById(R.id.navigation_rail);
        navigationRail.setItemActiveIndicatorHeight(130);
        navigationRail.setItemActiveIndicatorWidth(120);
        navigationRail.setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 16).build());

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
                case R.id.menu_tab:
                    openFragment(new MenuFragment());
                    return true;
                case R.id.report_tab:
                    openFragment(new ReportFragment());
                    return true;
            }
            return false;
        });

        // Universal Object
        ongoingOrderManager= new OngoingOrderManager();
    }

    private void openFragment(Fragment destination) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, destination).commit();
    }
}