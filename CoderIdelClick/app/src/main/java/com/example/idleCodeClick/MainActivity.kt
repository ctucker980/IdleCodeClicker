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
    val case = Upgrades("Case", 5.0, 0, 1.25)
    val motherboard = Upgrades("Motherboard", 20.00, 0, 10.00)

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
                            caseBuyButton.setOnClickListener {
                                makePurchase(case)
                                caseCost.text = case.cost.toString()
                                caseOwned.text = "Owned: ${case.count}"
                                caseCost.text = "Cost: $${String.format("%.2f", case.cost)}"
                            }

                            motherboardBuyButton.setOnClickListener {
                                makePurchase(motherboard)
                                motherboardCost.text = motherboard.cost.toString()
                                motherboardOwned.text = "Owned: ${motherboard.count}"
                                motherboardCost.text = "Cost: $${String.format("%.2f", motherboard.cost)}"
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
            if (item.name == "Case") {
                item.cost *= 1.25
            }
            if (item.name == "Motherboard") {
                item.cost *= 2.25
            }

            moneyMul += item.Addedmoney
    }
        else {
            Toast.makeText(this, "Not Enough Money", Toast.LENGTH_SHORT).show()
        }


    }
}
