package com.example.activitytest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private  lateinit var binding: FirstLayoutBinding

    private val actionViewLauncherA = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
       if (result.resultCode == RESULT_OK) {
           val returnedData = result.data?.getStringExtra("data_return")
           Log.d("FirstActivity actionViewLauncherA", "returned data is $returnedData")
       }
    }

    private val actionViewLauncherB = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val returnedData = result.data?.getStringExtra("data_return")
            Log.d("FirstActivity actionViewLauncherB", "returned data is $returnedData")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity", this.toString())
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener { Toast.makeText(this, "You clicked Button1", Toast.LENGTH_SHORT).show() }
        binding.button2.setOnClickListener { finish() }
        binding.button3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            val intent = Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            startActivity(intent)
        }
        binding.button5.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
        binding.button6.setOnClickListener {
            val intent = Intent("android.intent.action.VIEW")
            intent.data = Uri.parse("https://www.bing.com")
            startActivity(intent)
        }

        // 系统拨号
        binding.button7.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }

        // 传递字符串
        binding.button8.setOnClickListener {
            val data = "Hello SecondActivity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data_string", data)
            startActivity(intent)
        }

        // 传递整型
        binding.button9.setOnClickListener {
            val data = 123456
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data_int", data)
            startActivity(intent)
        }

        // 传递布尔型
        binding.button10.setOnClickListener {
            val data = true
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data_boolean", data)
            startActivity(intent)
        }

        // 返回时，向前一个 Activity 传递数据
        binding.button11A.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            actionViewLauncherA.launch(intent)
        }

        binding.button11B.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            actionViewLauncherB.launch(intent)
        }

        binding.button12A.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.button12B.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 2)
        }

        binding.button13.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("FirstActivity", "onRestart")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("onActivityResult", requestCode.toString())

        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data_return")
                Log.d("onActivityResult 12-A", "returned data is $returnData")
            }
            2 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data_return")
                Log.d("onActivityResult 12-B", "returned data is $returnData")
            }
        }
    }
}