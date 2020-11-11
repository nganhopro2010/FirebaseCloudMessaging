package com.shapeecloudjsc.firebasecloudmessaging

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var  list : ArrayList<TourList>
    private lateinit var pDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lvTour)
        fab = findViewById(R.id.fab)
        val url = "http://ducthinh-bestbus.000webhostapp.com/api/getTour.php?from=&to=os"
        fab.setOnClickListener {
            ReadJSON().execute(url)
        }
    }

    inner class ReadJSON : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog = ProgressDialog(this@MainActivity)
            pDialog.setMessage("Đang tải dữ liệu")
            pDialog.setCancelable(false)
            pDialog.show()
        }

        override fun doInBackground(vararg url: String?): String {
            val res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use {
                    it.reader().use { reader -> reader.readText() }
                }
            } finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
//            Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
            if (pDialog.isShowing)
                pDialog.dismiss()
            jsonResult(result)
        }

        private fun jsonResult(result: String?) {
            val jsonArray = JSONArray(result)
            list = ArrayList()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                list.add(
                    TourList(
                        jsonObject.getInt("id"),
                        jsonObject.getString("tourName"),
                        jsonObject.getDouble("oldPrice"),
                        jsonObject.getDouble("price"),
                        jsonObject.getDouble("vat"),
                        jsonObject.getString("startTime"),
                        jsonObject.getString("time"),
                        jsonObject.getString("fromCity"),
                        jsonObject.getString("toCity"),
                        jsonObject.getInt("seatQuantity"),
                        jsonObject.getInt("count"),
                        jsonObject.getString("seatSelected")

                    )
                )
            }
            val adapter = TourAdapter(this@MainActivity, list)
            listView.adapter = adapter
        }

    }


}