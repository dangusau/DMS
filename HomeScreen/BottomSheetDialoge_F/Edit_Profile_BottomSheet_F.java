package com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;

import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;
import com.dinosoftlabs.uber.Users_Chat.See_Full_Image_F;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.DMS_Cust.DMS.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Edit_Profile_BottomSheet_F extends BottomSheetDialogFragment implements View.OnClickListener {
   
    TextView tv_view_photo,tv_take_photo,tv_choose_from_gallery;

    Bitmap prof_pic;


    View view;

    Fragment_CallBack callBack;

    public Edit_Profile_BottomSheet_F(Fragment_CallBack callBack) {
        this.callBack = callBack;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.edit_profpic_bts_f, container, false);

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");


        METHOD_findviewbyid();


        return view;
    }





    private void METHOD_findviewbyid() {
        tv_view_photo = view.findViewById(R.id.tv_view_photo);
        tv_view_photo.setOnClickListener(this);

        tv_take_photo = view.findViewById(R.id.tv_take_photo);
        tv_take_photo.setOnClickListener(this);

        tv_choose_from_gallery = view.findViewById(R.id.tv_choose_from_gallery);
        tv_choose_from_gallery.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_view_photo:
                dismiss();
                METHOD_openFragment();
                break;

            case R.id.tv_take_photo:
                check_permissions(getActivity());
                break;

            case R.id.tv_choose_from_gallery:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
                break;
        }
    }



    private void METHOD_openFragment() {
        See_Full_Image_F f = new See_Full_Image_F();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);

        Bundle bundle = new Bundle();
        if(Variables.mPrefs.getBoolean(Variables.isloginwithfb,false))
            bundle.putString("image_url", Variables.mPrefs.getString(Variables.image,""));
        else
            bundle.putString("image_url", Api_url.url_pic+Variables.mPrefs
                    .getString(Variables.image,""));

        bundle.putString("chat_id", Variables.mPrefs.getString(Variables.User_id, ""));
        bundle.putBoolean("isfromchat", false);
        f.setArguments(bundle);

        ft.replace(R.id.fl_id,f).addToBackStack(null).commit();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            String image;
            if (requestCode == 1) {
                Matrix matrix = new Matrix();
                try {
                    ExifInterface exif = new ExifInterface(imageFilePath);
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            matrix.postRotate(90);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            matrix.postRotate(180);
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            matrix.postRotate(270);
                            break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri selectedImage =(Uri.fromFile(new File(imageFilePath)));


                InputStream imageStream = null;
                try {
                    imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);
                Bitmap rotatedBitmap = Bitmap
                        .createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(),
                                imagebitmap.getHeight(), matrix, true);

                Bundle bundle = new Bundle();
                bundle.putParcelable("bitmap", rotatedBitmap);
                callBack.On_Item_Click(0, bundle);

                dismiss();
            }else if (requestCode == 2){
                Uri targetUri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream =getActivity().getContentResolver().openInputStream(targetUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap imagebitmap = BitmapFactory.decodeStream(imageStream);

                String path = getPath(targetUri);

                Matrix matrix = new Matrix();
                ExifInterface exif = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    try {
                        exif = new ExifInterface(path);
                        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                        Log.d("respa", ""+orientation);
                        switch (orientation) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                matrix.postRotate(90);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                matrix.postRotate(180);
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                matrix.postRotate(270);
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                Bitmap rotatedBitmap = Bitmap
                        .createBitmap(imagebitmap, 0, 0, imagebitmap.getWidth(),
                                imagebitmap.getHeight(), matrix, true);

                Bundle bundle = new Bundle();
                bundle.putParcelable("bitmap", rotatedBitmap);
                callBack.On_Item_Click(0, bundle);

                dismiss();
            }
        }

    }








    //This METHOD convert image Bitmap to Base64
    private String METHOD_bitmap_to_bas64(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos .toByteArray();
        String base64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        return base64;
    }









    private boolean check_permissions(Activity context) {
        String[] PERMISSIONS = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (!hasPermissions(context, PERMISSIONS)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(PERMISSIONS, 202);
            }
        }else {
            openCameraIntent();
            return true;
        }

        return false;
    }






    private boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d("resp", "Permission granted");
                    return false;
                }
            }
        }
        return true;
    }










    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        getContext().getPackageName()+".fileprovider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, 1);
            }
        }
    }





    private String imageFilePath;
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir );

        imageFilePath = image.getAbsolutePath();
        return image;
    }





    private String getPath(Uri uri) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContext().getContentResolver()
                .query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst() ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }

        if(result == null) {
            result = "Not found";
        }

        return result;
    }


}
