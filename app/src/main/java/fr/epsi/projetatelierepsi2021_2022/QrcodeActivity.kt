package fr.epsi.projetatelierepsi2021_2022

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import org.json.JSONObject

private const val CAMERA_REQUEST_CODE = 101

class QrcodeActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val tv_text_View:TextView = findViewById(R.id.tv_text_view)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val data = it.text
                val stringdata = """{"personne":[$data]}"""
               Log.v("stringdata", stringdata)
                    val qrcodes = arrayListOf<QrCode>()
                    val jsQrcodes = JSONObject(stringdata)
                    val jsArrayQrcodes= jsQrcodes.getJSONArray("personne")
                    for(i in 0 until jsArrayQrcodes.length()){
                        val jsQrcode = jsArrayQrcodes.getJSONObject(i)
                        val qrcode = QrCode(jsQrcode.optString("firstName",""),
                            jsQrcode.optString("lastName",""),
                            jsQrcode.optString("email",""),
                            jsQrcode.optString("address",""),
                            jsQrcode.optString("zipcode",""),
                            jsQrcode.optString("city",""),
                            jsQrcode.optString("cardRef","")
                        )
                        qrcodes.add(qrcode)
                        Log.v("qrcode",qrcode.lastName)
                    }
                    Log.v("qrcodes","${qrcodes.size}")
                    runOnUiThread(Runnable {
                        val newIntent = Intent(application, LoginActivity::class.java)
                        newIntent.putExtra("firstName",qrcodes[0].firstName)
                        newIntent.putExtra("lastName",qrcodes[0].lastName)
                        newIntent.putExtra("email",qrcodes[0].email)
                        newIntent.putExtra("address",qrcodes[0].address)
                        newIntent.putExtra("zipcode",qrcodes[0].zipcode)
                        newIntent.putExtra("city",qrcodes[0].city)
                        newIntent.putExtra("cardRef",qrcodes[0].cardRef)
                        startActivity(newIntent)
                    })

            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
                CAMERA_REQUEST_CODE->{
                    if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "You need the camera permissions to be able to use this application", Toast.LENGTH_SHORT).show()
                    }
                    else{//Successful
                }
            }
        }
    }
}

