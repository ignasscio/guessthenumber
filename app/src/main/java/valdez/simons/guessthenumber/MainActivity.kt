package valdez.simons.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.min
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var minValue:Int = 0
    var maxValue:Int = 100
    var num:Int = 0
    var won:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings:TextView = findViewById(R.id.guessings)
        val button_DOWN:Button = findViewById(R.id.button_DOWN)
        val button_UP:Button = findViewById(R.id.button_UP)
        val button_GENERATE:Button = findViewById(R.id.button_GENERATE)
        val button_GUESSED:Button = findViewById(R.id.button_GUESSED)

        button_GENERATE.setOnClickListener{
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            button_GENERATE.visibility = View.INVISIBLE
            button_GUESSED.visibility = View.VISIBLE
        }

        button_UP.setOnClickListener{
            minValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("It can't be :( You won")
            }

        }

        button_DOWN.setOnClickListener{
            maxValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("It can't be :( You won")
            }

        }

        button_GUESSED.setOnClickListener{
            if(!won) {
                guessings.setText("I've guessed your numer! It's $num")
                button_GUESSED.setText("Play again?")
                won = true
            }else{
                button_GENERATE.visibility = View.VISIBLE
                button_GUESSED.setText("GUESSED")
                button_GENERATE.setText("Tap on GENERATE to START")
                button_GUESSED.visibility = View.GONE
                reset()
            }
        }
    }

    fun reset():Unit{
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits():Boolean{
        return minValue != maxValue
    }
}