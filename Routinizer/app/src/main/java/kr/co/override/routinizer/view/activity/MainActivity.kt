package kr.co.override.routinizer.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.override.routinizer.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}