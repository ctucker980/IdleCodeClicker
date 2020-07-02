package com.example.idleCodeClick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var money = 5.0
    var moneyMul = 0.0


    //create items
    val pcCase = Upgrades("PC Case", 5.0, 0, 1.25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.scoreLabel)

        val t = object : Thread() {
            override fun run() {
                super.run()
                while (!isInterrupted) {
                    try {
                        sleep(1000)

                        runOnUiThread {
                            pcCaseUpgrade.setOnClickListener {
                                makePurchase(pcCase)
                                pcCaseTrueCostLabel.text = pcCase.cost.toString()
                                pcCaseOwnedCountLabel.text = pcCase.count.toString()
                                pcCaseTrueCostLabel.text = "Cost: $${String.format("%.2f", pcCase.cost)}"
                            }
                            money += moneyMul
                            scoreLabel.text= "$${String.format("%.2f", money)}"
                            perSecondLabel.text = "$${String.format("%.2f", moneyMul)}/sec"
                        }
                    } catch (e : InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }

        }

        t.start()
    }

    fun makePurchase( item : Upgrades) {
        if (money >= item.cost) {
            money -= item.cost
            item.count++
            item.cost *= 1.25
            moneyMul += item.Addedmoney
    }
        else {
            Toast.makeText(this, "Not Enough Money", Toast.LENGTH_SHORT).show()
        }


    }
}
