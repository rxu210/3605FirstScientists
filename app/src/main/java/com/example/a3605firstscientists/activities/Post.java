package com.example.a3605firstscientists.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.a3605firstscientists.HomeActivity;
import com.example.a3605firstscientists.MapActivity;
import com.example.a3605firstscientists.R;
import com.example.a3605firstscientists.UpdatedLearnActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.a3605firstscientists.Donate;
import com.example.a3605firstscientists.LearnActivity;
import com.example.a3605firstscientists.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;


public class Post extends AppCompatActivity {

    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2;


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    Dialog popAddPost;

    ImageView popupUserImage, popupPostImage,popupAddBtn;
    Button popupEditBtn;
    TextView popupTitle, popupLocation, popupDescription;
    private Uri pickedImgUri = null;

    PostAdapter postAdapter;
    RecyclerView postRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Posting> postingList;

    // Variables for post location, initialised with user's current coordinates
    double postLatitude = Login.latitude;
    double postLongitude = Login.longitude;
    String postAddress = Login.userAddress;

    // Lists of locations where posts have been made to show on the Map
    public static List<LatLng> postLocations = new ArrayList<LatLng>();
    public static List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        iniPopup();
        setupPopupImageClick();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popAddPost.show();
            }
        });


        postRecyclerView = findViewById(R.id.postRV);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        postRecyclerView.setLayoutManager(layoutManager);

        postRecyclerView.setHasFixedSize(true);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");

        iniRv();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                   /* case R.id.ic_home:
                        Intent intent1 = new Intent(Post.this, HomeActivity.class);;
                        startActivity(intent1);
                        break;*/

                    case R.id.ic_learn:
                        Intent intent2 = new Intent(Post.this, UpdatedLearnActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_map:
                        Intent intent3 = new Intent(Post.this, MapActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_blog:
                        break;
                }
                return false;
            }
        });

    }


    public void iniRv(){


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postingList = new ArrayList<>();
                for (DataSnapshot postsnap: snapshot.getChildren()){


                    Posting post = postsnap.getValue(Posting.class);
                    postingList.add(post);

                    // Add coordinates and title to list
                    postLocations.add(new LatLng(post.getLatitude(), post.getLongitude()));
                    titleList.add(post.getTitle());

                }

                postAdapter = new PostAdapter(getApplicationContext(),postingList);

                postRecyclerView.setAdapter(postAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void setupPopupImageClick() {

        popupPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndRequestForPermission();

            }
        });
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(Post.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Post.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Post.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            }

            else{

                ActivityCompat.requestPermissions(Post.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode );

            }

        }

        else{
            openGallery();
        }



    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode ==RESULT_OK && requestCode == REQUESCODE && data !=null) {

            pickedImgUri = data.getData();
            popupPostImage.setImageURI(pickedImgUri);



        }
        else if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            postAddress = data.getStringExtra("postAddress");
            postLatitude = data.getDoubleExtra("postLatitude", Login.latitude);
            postLongitude = data.getDoubleExtra("postLongitude", Login.longitude);

            popupLocation.setText("Post Location: " + postAddress);
        }

    }

    private void iniPopup() {

        popAddPost = new Dialog(this);

        popAddPost.setContentView((R.layout.popup_add_post));
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.TOP;

        popupUserImage = popAddPost.findViewById(R.id.popup_user_image);
        popupPostImage = popAddPost.findViewById(R.id.popup_img);
        popupTitle = popAddPost.findViewById(R.id.popup_title);
        popupLocation = popAddPost.findViewById(R.id.tvPostLocation);
        popupLocation.setText("Post location: " + postAddress);
        popupDescription = popAddPost.findViewById(R.id.popup_description);
        popupAddBtn = popAddPost.findViewById(R.id.popup_add);
        popupEditBtn = popAddPost.findViewById(R.id.btnEdit);

        Glide.with(Post.this).load(currentUser.getPhotoUrl()).into(popupUserImage);

        popupEditBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int GET_POST_LOCATION = 1;
                Intent intent = new Intent(Post.this, com.example.a3605firstscientists.AddActivity.class);
                intent.putExtra("from", "add Post");
                startActivityForResult(intent, GET_POST_LOCATION);


            }

        });

        popupAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!popupTitle.getText().toString().isEmpty()
                        && !popupLocation.getText().toString().isEmpty()
                && !popupDescription.getText().toString().isEmpty()
                        && pickedImgUri != null) {

                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("blog_images");
                StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imageDownloadLink = uri.toString();


                                Posting posting = new Posting(popupTitle.getText().toString(),
                                        postAddress, postLatitude, postLongitude,
                                        popupDescription.getText().toString(),
                                        imageDownloadLink,
                                        currentUser.getUid(),
                                        currentUser.getPhotoUrl().toString());


                                addPost(posting);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                showMessage(e.getMessage());


                            }
                        });

                    }
                });




                }

                else {
                    showMessage("Please have a title, location, description and image");

                }




            }
        });


    }

    private void addPost(Posting posting) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();


        String key = myRef.getKey();
        posting.setPostKey(key);

        myRef.setValue(posting).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("Post Added Successfully");
                popAddPost.dismiss();
            }
        });


    }

    private void showMessage(String message) {

        Toast.makeText(Post.this,message,Toast.LENGTH_LONG).show();
    }

}