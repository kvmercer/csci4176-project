package com.example.eyedroptracker.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager2.widget.ViewPager2
import com.example.eyedroptracker.R
import com.example.eyedroptracker.adapters.TabPageAdapter
import com.example.eyedroptracker.databinding.ActivityMainBinding
import com.example.eyedroptracker.service.AlarmService
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var tts: TextToSpeech
    private val RQ_SPEECH_REC = 102
    private var temp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)


//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        var alarmService: AlarmService = AlarmService(this)

//        binding.fab.setOnClickListener {
//            setAlarm { alarmService.setExactAlarm(it) }
//        }
        setUpTabBar()
        startinputflow()
    }

    private fun setUpTabBar() {
        val adapter = TabPageAdapter(this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //do nothing
            }

        })

    }
    fun speakout(input : String){
        tts = TextToSpeech(applicationContext,TextToSpeech.OnInitListener {
            if(it == TextToSpeech.SUCCESS){
                tts.language = Locale.getDefault()
                tts.setSpeechRate(1.0f)
                tts.speak(input,TextToSpeech.QUEUE_ADD,null)

            }
        })
    }
    //starting voice input output flow
     fun startinputflow(){
        //Shouting out if you can see the screen to turn off voice input output if user can see screen
        speakout("Can you see the screen if yes click on yes else just listen and chill")
        var bol = true
        val snack = Snackbar.make(findViewById(R.id.constraintLayoutFragmentHome),"Can you see me",Snackbar.LENGTH_LONG)
        snack.setAction("DISMISS", View.OnClickListener {
            // executed when DISMISS is clicked
            bol = false
            snack.dismiss()
        })
        snack.show()
        if(bol){
            startvoiceflow()
        }

    }
    //taking medicine input
    fun takemedicineinput(){
        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        while (true) {
            speakout("Enter your medicine name")
            startActivityForResult(i, RQ_SPEECH_REC)
            speakout("is the  medicine you intended to type $temp dont worry if its not exact ")
            startActivityForResult(i, RQ_SPEECH_REC)
            if (temp == "no") {
                //going again if not right
            }
            else{
                //taking the rest of the input
                var medicinename = temp
                speakout("Enter dosage just speak the number")
                startActivityForResult(i, RQ_SPEECH_REC)
                var dosage = temp.toInt()
                speakout("Enter custom description of your medicine")
                startActivityForResult(i, RQ_SPEECH_REC)
                var descr = temp
                speakout("Do you want to add a reminder for this")
                startActivityForResult(i, RQ_SPEECH_REC)
                if(temp == "yes"){
                    //function to set date reminder etc
                }
                break
            }
        }
    }
    //Handling voice input
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)  {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result = data?.getStringExtra((RecognizerIntent.EXTRA_RESULTS))
            var tempinput = result?.get(0).toString()
            temp = tempinput //storing the input in a temp private global variable
        }
    }
    fun startvoiceflow(){
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this,"Speech recognition is not available",Toast.LENGTH_LONG).show()
        }
        else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            takemedicineinput()
            speakout("Do you want to add more medicine")
            startActivityForResult(i, RQ_SPEECH_REC)
            if(temp == "yes"){
                takemedicineinput()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}