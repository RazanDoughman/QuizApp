package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.Activity.MainActivity
import com.example.quizapp.databinding.FragmentCreateQuizBinding
import kotlin.random.Random

class SpinWheelActivity : AppCompatActivity() {

    val sectors = arrayOf(750, 500, 250, 750, 500, 250, 1000)

    val sectorDegrees = IntArray(sectors.size)
    var randomSectorIndex: Int = 0

    lateinit var wheel: ImageView
    var spinning: Boolean = false
    var earningsRecord: Int = 0
    val random = kotlin.random.Random

    private var _binding: SpinWheelActivity? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spin_wheel)



        // To spin
        wheel = findViewById(R.id.wheel)

        val belt: ImageView = findViewById(R.id.ring)

        generateSectorDegrees()

        belt.setOnClickListener {
            // Only if it is not spinning
            if (!spinning) {
                spin()
                spinning = true
            }
        }
        val withdrawnBtn: Button = findViewById(R.id.withdrawnBtn)
        withdrawnBtn.setOnClickListener {
            val text = "Ready to withdraw $earningsRecord coin"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            // Navigate back to the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun spin() {
        randomSectorIndex = random.nextInt(sectors.size)
        val randomDegree = generateRandomDegreeToSpinTo()
        val rotateAnimation = RotateAnimation(
            0f,
            randomDegree.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.setDuration(3600);
        rotateAnimation.fillAfter = true

        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Do nothing
            }

            override fun onAnimationEnd(animation: Animation) {
                val earnedCoins = sectors[sectors.size - (randomSectorIndex - 1)]

                saveEarnings(earnedCoins)

                spinning = false
            }

            override fun onAnimationRepeat(animation: Animation) {
                // Do nothing
            }
        })


        wheel.startAnimation(rotateAnimation);
    }

    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun saveEarnings(earnedCoins: Int) {
        earningsRecord += earnedCoins
        val tv: TextView = findViewById(R.id.earnings)
        tv.text = earningsRecord.toString()
    }


    private fun generateRandomDegreeToSpinTo(): Int {
        return (360 * sectors.size) + sectorDegrees[randomSectorIndex]
    }

    private fun generateSectorDegrees() {
        // For 1 sector
        val sectorDegree = 360 / sectors.size

        for (i in sectors.indices) {
            // Make it greater as much as you can
            sectorDegrees[i] = (i + 1) * sectorDegree
        }
    }




}