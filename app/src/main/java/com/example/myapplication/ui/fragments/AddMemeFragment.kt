package com.example.myapplication.ui.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_addmeme.*
import kotlinx.android.synthetic.main.toolbar_add_meme.*
import android.graphics.Bitmap
import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import java.io.File


class AddMemeFragment : Fragment() {

    lateinit var cameraFileUri: Uri
    private val REQUEST_CAMERA = 22
    private val REQUEST_GALLERY = 21

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.myapplication.R.layout.fragment_addmeme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            cameraFileUri = createUri(it)
        }
        initViews()
    }

    fun createUri(context: Context): Uri {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val outFile = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "share_image_" + System.currentTimeMillis() + ".png"
        )
        return Uri.fromFile(outFile)
    }

    val onChangeTitleListener = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.toString().trim().isNotEmpty() && p0.toString().trim().length <= 140) {
                enableCreateButton()
            } else disableCreateButton()
        }
    }

    private fun initViews(){
        addMeme_button.setOnClickListener {
            activity?.let{
                LoadImageDialog.showDialog(it,this::photoFromCamera,this::photoFromGallery)
            }
        }
        addMeme_title.addTextChangedListener(onChangeTitleListener)
    }

    fun photoFromCamera(){
        if (checkCameraPermission()) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CAMERA)
        }
    }

    fun checkCameraPermission() : Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity!!.checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.CAMERA),
                    3
                )
                return false
            }
        } else {
            return true
        }
    }

    fun photoFromGallery(){
        if (checkWritePermission()) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GALLERY)
        }
    }

    fun checkWritePermission() : Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    3
                )
                return false
            }
        } else {
            return true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==RESULT_OK)
        when (requestCode) {
            REQUEST_GALLERY -> {
                data?.data?.let {
                    addMeme_image.setImageURI(it)
                }
            }
            REQUEST_CAMERA -> {
                val photo = data?.extras?.get("data") as Bitmap
                addMeme_image.setImageBitmap(photo)
            }
        }
    }

    fun enableCreateButton() {
        button_create_meme.apply {
            isEnabled = true
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        }
    }

    fun disableCreateButton() {
        button_create_meme.apply {
            isEnabled = false
            setTextColor(ContextCompat.getColor(context, R.color.disabledButton))
        }
    }

}