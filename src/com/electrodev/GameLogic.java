package com.electrodev;

import java.util.Random;

/**
 *
 * @author inspiron
 */

public class GameLogic {
protected static int x;
protected static int y;
protected static int actualAns;
private int wrongAns[] = new int[4];
private int ansViewAraay[] = new int[2];
protected static int wAns;
protected static int finalwrongAns;
protected static int ansView;
protected static int finalAnsView;

   GameLogic(){
   Random r = new Random();
   this.x =r.nextInt(10)+1;// الرقم الأول الذي يظهر للاعب
   this.y= r.nextInt(10)+1;//الرقم الثاني الذي يظهر للاعب
   actualAns = x+y; //الإجابة الصحيحة

    wrongAns[0]= actualAns+1;
    wrongAns[1]= actualAns-1;
    wrongAns[2]= actualAns+2;
    wrongAns[3]= actualAns-2;

    wAns = r.nextInt(wrongAns.length);
    finalwrongAns = wrongAns[wAns];
    ansViewAraay[0] = finalwrongAns;
    ansViewAraay[1] = actualAns;
    ansView = r.nextInt(ansViewAraay.length);
    finalAnsView = ansViewAraay[ansView];// هذا الناتج يظهر كناتج نهائي ليتم اختبار اللاعب فيه !
    //System.out.println(finalAnsView);

}

}





