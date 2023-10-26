package drop.swift.sale.module.firebase;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseCallback {
    void onCallback(DataSnapshot dataSnapshot);
}
