package com.navarro.lectorph;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    public TextView hexValue, rgbValue, phValue;
    public ImageView color_display;
    public String rgbcolor, hexcolor, ph;
    Button mCaptureBtn;
    ImageView mImageView;

    Uri image_uri;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image_view);
        mCaptureBtn = findViewById(R.id.Capture_image_btn);

        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();
                    }
                } else {
                    openCamera();
                }
            }
        });
        phValue = findViewById(R.id.ph);
        hexValue = findViewById(R.id.hexcolor);
        rgbValue = findViewById(R.id.rgbcolor);
        color_display = findViewById(R.id.color_display);

        mImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    //get touch event
                    final int action = motionEvent.getAction();
                    final int evX = (int) motionEvent.getX();//get x coordinate
                    final int evY = (int) motionEvent.getY();//get y coordinate
                    //get color from the pixel
                    int touchColor = getColor(mImageView, evX, evY);
                    //get R,G,B value of the pixal
                    int r = (touchColor >> 16) & 0xFF;
                    int g = (touchColor >> 8) & 0xFF;
                    int b = (touchColor >> 0) & 0xFF;
                    if(r>195 && r<260) {
                        if(g>50 && g<70) {
                            if(b>70 && b<90) {
                                ph = "0";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>110 && r<120) {
                        if(g>15 && g<45) {
                            if(b>60 && b<80) {
                                ph = "1";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>120 && r<140) {
                        if(g>20 && g<45) {
                            if(b>60 && b<80) {
                                ph = "2";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>130 && r<160) {
                        if(g>50 && g<70) {
                            if(b>60 && b<80) {
                                ph = "3";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>155 && r<170) {
                        if(g>100 && g<130) {
                            if(b>60 && b<80) {
                                ph = "4";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>170 && r<190) {
                        if(g>150 && g<190) {
                            if(b>60 && b<90) {
                                ph = "5";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>190 && r<210) {
                        if(g>190 && g<210) {
                            if(b>90 && b<120) {
                                ph = "6";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>150 && r<170) {
                        if(g>160 && g<190) {
                            if(b>70 && b<100) {
                                ph = "7";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>110 && r<130) {
                        if(g>130 && g<150) {
                            if(b>60 && b<80) {
                                ph = "8";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>60 && r<100) {
                        if(g>100 && g<130) {
                            if(b>70 && b<90) {
                                ph = "9";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>10 && r<30) {
                        if(g>30 && g<60) {
                            if(b>80 && b<100) {
                                ph = "10";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>0 && r<10) {
                        if(g>20 && g<40) {
                            if(b>110 && b<130) {
                                ph = "11";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>10 && r<20) {
                        if(g>20 && g<40) {
                            if(b>130 && b<160) {
                                ph = "12";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>10 && r<20) {
                        if(g>15 && g<30) {
                            if(b>110 && b<130) {
                                ph = "13";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else if(r>20 && r<40) {
                        if(g>20 && g<40) {
                            if(b>70 && b<95) {
                                ph = "14";
                                phValue.setText("PH:   " + ph);
                            }
                        }
                    }
                    else{
                        phValue.setText("Color incorrecto");
                    }
                    rgbcolor = String.valueOf(r) + "," + String.valueOf(g) + "," + String.valueOf(b) + ",";
                    rgbValue.setText("RGB:   " + rgbcolor);

                    //get hax color from rgb value
                    hexcolor = Integer.toHexString(touchColor);
                    if (hexcolor.length() > 2) {
                        hexcolor = hexcolor.substring(2, hexcolor.length());//remove alfa from value
                    }
                    if (action == MotionEvent.ACTION_UP) {
                        //set touch event
                        color_display.setBackgroundColor(touchColor);
                        hexValue.setText("HEX:   #" + hexcolor);
                    }
                } catch (Exception e) {
                }

                return true;
            }
        });
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private int getColor(ImageView selectedImage, int evX, int evY) {
        selectedImage.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(selectedImage.getDrawingCache());
        selectedImage.setDrawingCacheEnabled(false);
        return bitmap.getPixel(evX, evY);//it will return selected pixal
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mImageView.setImageURI(image_uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.picker_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_copy:
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Clip", "#" + hexcolor);
                Toast.makeText(this, "copied #" + hexcolor, Toast.LENGTH_SHORT).show();
                clipboardManager.setPrimaryClip(clip);
                break;

            case R.id.btn_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "ImageColorPicker");
                    String s = "\nColorPicker: Pic any color from Image\n\n";
                    s = s + "https://play.google.com/store/app/details?id=com.androchunk.imagecolorpicker";
                    i.putExtra(Intent.EXTRA_TEXT, s);
                    startActivity(Intent.createChooser(i, "Share App"));

                } catch (Exception e) {

                }
                break;

            case R.id.btn_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
