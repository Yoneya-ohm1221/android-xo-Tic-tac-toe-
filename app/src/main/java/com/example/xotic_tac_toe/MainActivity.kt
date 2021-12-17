package com.example.xotic_tac_toe

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
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

    var re:ImageView?=null
    var score_x:Int=0
    var score_O:Int=0
    var game:Array<Array<Int>>? = null
    var buttongame:Array<Button?>?=null
    var turn =false // fasle = X || true = o

    var txtX :TextView?=null
    var txtO:TextView?=null

    var Xscore:TextView?=null
    var Oscore:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtX=findViewById(R.id.txtXsc)
        Xscore=findViewById(R.id.txtXscore)
        Oscore=findViewById(R.id.txtOscore)
        txtO=findViewById(R.id.txtOsc)
        re=findViewById(R.id.imageViewre)

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

        re?.setOnClickListener {
            restart()
        }

        play()
    }

    fun play(){
        buttongame= arrayOf(a1,a2,a3,b1,b2,b3,c1,c2,c3)
        game = arrayOf( //   1=x || 2= o
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
    fun gameover(){
        buttongame?.forEach { it?.isClickable=false }
        Xscore?.text = "score X: $score_x"
        Oscore?.text = "score X: $score_O"
    }

    @SuppressLint("ResourceAsColor")
    fun check_trun(t:Boolean, b:Button){
        turn = if (!t){
            b.setText("X")
            case(b,1)
            game_end(1)
            txtO?.setTextColor(Color.parseColor("#FFFFFF"))
            txtX?.setTextColor(Color.parseColor("#BEBEBE"))
            true
        }else{
            b.setText("O")
            case(b,2)
            game_end(2)
            txtX?.setTextColor(Color.parseColor("#FFFFFF"))
            txtO?.setTextColor(Color.parseColor("#BEBEBE"))
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
    fun game_end(sta:Int){
        var win = 0

        if ( game!![0][0] == sta && game!![0][1] == sta && game!![0][2]== sta){// a1 a2 a3 = win
            setcolorwin(a1!!,a2!!,a3!!)
            win = sta
        }
        if ( game!![1][0] == sta && game!![1][1] == sta && game!![1][2]== sta){// b1 b2 b3 = win
            setcolorwin(b1!!,b2!!,b3!!)
            win = sta
        }
        if ( game!![2][0] == sta && game!![2][1] == sta && game!![2][2]== sta){// b1 b2 b3 = win
            setcolorwin(c1!!,c2!!,c3!!)
            win = sta
        }
        /*------------------------------------------------------------------------------------------*/
        if ( game!![0][0] == sta && game!![1][0] == sta && game!![2][0]== sta){// a1 b1 c1 = win
            setcolorwin(a1!!,b1!!,c1!!)
            win = sta
        }
        if ( game!![0][1] == sta && game!![1][1] == sta && game!![2][1]== sta){// a2 b2 c2 = win
            setcolorwin(a2!!,b2!!,c2!!)
            win = sta
        }
        if ( game!![0][2] == sta && game!![1][2] == sta && game!![2][2]== sta){// a3 b3 c3 = win
            setcolorwin(a3!!,b3!!,c3!!)
            win = sta
        }
        /*------------------------------------------------------------------------------------------*/
        if ( game!![0][0] == sta && game!![1][1] == sta && game!![2][2]== sta){// a1 b2 c3 = win
            setcolorwin(a1!!,b2!!,c3!!)
            win = sta
        }
        if ( game!![0][2] == sta && game!![1][1] == sta && game!![2][0]== sta){// c1 b2 a3 = win
            setcolorwin(c1!!,b2!!,a3!!)
            win = sta
        }
        /*------------------------------------------------------------------------------------------*/

        if(win>0){
            if (win ==1){
                Dialogplayerwin("X")
                score_x+=1
            }else{
                Dialogplayerwin("O")
                score_x+=2
            }
            gameover()
        }
    }
    fun setcolorwin(button1: Button,button2: Button,button3: Button){

        button1.setBackgroundColor(Color.parseColor("#FF5722"))
        button2.setBackgroundColor(Color.parseColor("#FF5722"))
        button3.setBackgroundColor(Color.parseColor("#FF5722"))
    }


    fun Dialogplayerwin(win:String){
        var alertDialomenug: AlertDialog
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.item_win, null)
        val txtwin:TextView = dialogView.findViewById(R.id.playerwin)
        txtwin.text=win

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setOnDismissListener { }
        dialogBuilder.setView(dialogView)

        alertDialomenug = dialogBuilder.create();
        alertDialomenug.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialomenug.show()
    }

    fun restart(){
        buttongame?.forEach {
            it?.isClickable =true
            it?.setText("")
            it?.setBackgroundColor(Color.parseColor("#3DD342"))
        }
        turn =false
        txtX?.setTextColor(Color.parseColor("#FFFFFF"))
        txtO?.setTextColor(Color.parseColor("#BEBEBE"))
        play()
    }

}