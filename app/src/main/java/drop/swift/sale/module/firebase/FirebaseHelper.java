package drop.swift.sale.module.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    public static final String TAG = FirebaseHelper.class.getSimpleName();

    public void readDatabaseOnce(FirebaseCallback firebaseCallback, String path) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(path);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firebaseCallback.onCallback(dataSnapshot);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the data here and handle it accordingly
                    Log.d(TAG, snapshot.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
