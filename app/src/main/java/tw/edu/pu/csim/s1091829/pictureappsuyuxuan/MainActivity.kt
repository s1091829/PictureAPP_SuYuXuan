package tw.edu.pu.csim.s1091829.pictureappsuyuxuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),  GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener,
    View.OnTouchListener{

    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 6 //總共幾張圖片(假設僅顯示pu0-pu5)

    fun ShowPicture() {
        /*
        when (PictureNo) {
            0 -> img.setImageResource(R.drawable.pu0)
            1 -> img.setImageResource(R.drawable.pu1)
            2 -> img.setImageResource(R.drawable.pu2)
            3 -> img.setImageResource(R.drawable.pu3)
        }
        */
        txv.text = PictureNo.toString()
        var res:Int = getResources().getIdentifier("pu" + PictureNo.toString(),
            "drawable", getPackageName())
        img.setImageResource(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("pu" + countDrawables.toString(),
                "drawable", getPackageName());
        }
        TotalPictures = countDrawables
    }
    /*
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true

    }
    */

    override fun onDown(p0: MotionEvent?): Boolean { //1
        return true
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean { //2
        PictureNo = 0
        ShowPicture()
        return true
    }

    override fun onLongPress(p0: MotionEvent?) { //3
        PictureNo = TotalPictures - 1
        ShowPicture()
    }

    override fun onShowPress(p0: MotionEvent?) { //4
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean { //5
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean { //6
        if (e1!!.getX() < e2!!.getX()){  //向右快滑
            PictureNo++
            if (PictureNo == TotalPictures) {PictureNo = 0}
        }
        else{     //向左快滑
            PictureNo--;
            if (PictureNo < 0) {PictureNo = TotalPictures - 1 }
        }
        ShowPicture()
        return true
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean { //7
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean { //8
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean { //9
        return true
    }

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

}