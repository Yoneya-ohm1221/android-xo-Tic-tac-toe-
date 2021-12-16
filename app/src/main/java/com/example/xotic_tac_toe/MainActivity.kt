package com.example.xotic_tac_toe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.toColorInt
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    var a1:Button?=null
    var a2:Button?=null
    var a3:Button?=null
    var b1:Button?=null
    var b2:Button?=null
    var b3:Button?=null
    var c1:Button?=null
    var c2:Button?=null
    var c3:Button?=null

    var game:Array<Array<Int>>? = null
    var buttongame:Array<Button?>?=null
    var turn =false // fasle = X || true = o

    var txtX :TextView?=null
    var txtO:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtX=findViewById(R.id.txtXsc)
        txtO=findViewById(R.id.txtOsc)

        //top buttom
        a1= findViewById(R.id.a1)
        a2= findViewById(R.id.a2)
        a3= findViewById(R.id.a3)

        //middle
        b1= findViewById(R.id.b1)
        b2= findViewById(R.id.b2)
        b3= findViewById(R.id.b3)

        //under
        c1= findViewById(R.id.c1)
        c2= findViewById(R.id.c2)
        c3= findViewById(R.id.c3)

        play()
    }


    fun play(){
        buttongame= arrayOf(a1,a2,a3,b1,b2,b3,c1,c2,c3)
        game = arrayOf( // 1=x || 2= o
            arrayOf(0,0,0),  //a1,a2,a3
            arrayOf(0,0,0),  //b1,b2,b3
            arrayOf(0,0,0)   //c1,c2,c3
        )

        buttongame?.forEach {
            it?.setOnClickListener {
                check_trun(turn, it as Button)
                it.isClickable = false
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    fun check_trun(t:Boolean, b:Button){
        turn = if (!t){
            b.setText("X")
            case(b,1)
            txtO?.setTextColor(Color.parseColor("#FFFFFF"))
            txtX?.setTextColor(Color.parseColor("#989898"))
            true
        }else{
            b.setText("O")
            case(b,2)
            txtX?.setTextColor(Color.parseColor("#FFFFFF"))
            txtO?.setTextColor(Color.parseColor("#989898"))
            false
        }
    }

    fun case(b:Button,sta:Int){
        when(b){
            a1 -> game!![0][0] = sta
            a2 -> game!![0][1] = sta
            a3 -> game!![0][2] = sta
            b1 -> game!![1][0] = sta
            b2 -> game!![1][1] = sta
            b3 -> game!![1][2] = sta
            c1 -> game!![2][0] = sta
            c2 -> game!![2][1] = sta
            c3 -> game!![2][2] = sta

        }
    }

}