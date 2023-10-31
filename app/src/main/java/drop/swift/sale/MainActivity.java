package drop.swift.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.firebase.FirebaseApp;

import drop.swift.sale.manager.OngoingOrderManager;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationRail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        navigationRail = findViewById(R.id.navigation_rail);
        navigationRail.setItemActiveIndicatorHeight(130);
        navigationRail.setItemActiveIndicatorWidth(120);
        navigationRail.setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 16).build());

        HomeFragment homeFragment = new HomeFragment();
        OrderFragment orderFragment = new OrderFragment();
        MenuFragment menuFragment = new MenuFragment();
        ReportFragment reportFragment = new ReportFragment();

        if (savedInstanceState == null) {
            //openFragment(new HomeFragment());
            openFragment(homeFragment);
            navigationRail.setSelectedItemId(R.id.home_tab);
        }

        navigationRail.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_tab:
                    //openFragment(HomeFragment());
                    openFragment(homeFragment);
                    return true;
                case R.id.order_tab:
                    //openFragment(new OrderFragment());
                    openFragment(orderFragment);
                    return true;
                case R.id.menu_tab:
                    //openFragment(new MenuFragment());
                    openFragment(menuFragment);
                    return true;
                case R.id.report_tab:
                    //openFragment(new ReportFragment());
                    openFragment(reportFragment);
                    return true;
            }
            return false;
        });
    }

    private void openFragment(Fragment destination) {
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, destination).commit();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Hide all existing fragments
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            transaction.hide(fragment);
        }

        if (destination.isAdded()) {
            // If the fragment is already added, show it
            transaction.show(destination);
        } else {
            // If the fragment is not added, add it
            transaction.add(R.id.fragment_container, destination);
        }

        // Commit the transaction
        transaction.commit();
    }
}