package com.example.youscrollapp

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.youscrollapp.databinding.ActivityFormBinding
import com.example.youscrollapp.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class FormActivity : AppCompatActivity() {

    private var mProfileUri: Uri? = null
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nextBtn.setOnClickListener {

            if(binding.password.editText!!.text.toString().isValidEmail()){
            binding.showForm.visibility = View.VISIBLE
            binding.fillForm.visibility = View.INVISIBLE
            binding.backBtn.visibility = View.VISIBLE
            binding.nextBtn.visibility = View.INVISIBLE

            binding.showName.text = binding.name.editText!!.text.toString()
            binding.showEmail.text = binding.password.editText!!.text.toString()
            if(mProfileUri != null){
                binding.userImageView.setImageURI(mProfileUri)
            }else{
                Toast.makeText(this,"Choose Image",Toast.LENGTH_LONG).show()
            }
            binding.addimageTv.visibility = View.INVISIBLE}
            else{
                binding.password.editText!!.error = "Error"
                return@setOnClickListener
            }
        }
        binding.backBtn.setOnClickListener {

            binding.addimageTv.visibility = View.INVISIBLE
            binding.showForm.visibility = View.INVISIBLE
            binding.fillForm.visibility = View.VISIBLE
            binding.nextBtn.visibility = View.VISIBLE
            binding.backBtn.visibility = View.INVISIBLE
        }
        binding.userImageView.setOnClickListener {
            ImagePicker.with(this)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!

                mProfileUri = fileUri

                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
}