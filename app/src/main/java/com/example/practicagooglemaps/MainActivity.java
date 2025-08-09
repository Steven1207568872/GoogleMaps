package com.example.practicagooglemaps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    GoogleMap mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0125, -79.4673), 17);
        mapa.moveCamera(camUpd1);

        LatLng madrid = new LatLng(-1.0127170686629148, -79.46734141278269);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(17)
                .bearing(180) //noreste arriba
                .tilt(50) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);

        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.0116618584938093, -79.4718440119601))
                .add(new LatLng(-1.0132937960719788, -79.4715529203234))
                .add(new LatLng(-1.0136604681786106, -79.46711619484665))
                .add(new LatLng(-1.0123724310753752, -79.46682580200175))
                .add(new LatLng(-1.0116618584938093, -79.4718440119601));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);

        LatLng punto = new LatLng(-1.012206424409027, -79.46965183080614);
        mapa.addMarker(new
                MarkerOptions().position(punto)
                .title("UTEQ"));



    }
}