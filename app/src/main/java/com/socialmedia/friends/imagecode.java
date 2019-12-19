package com.socialmedia.friends;

import com.socialmedia.friends.R;

public class imagecode {
    int code1 = R.drawable.boy;
    int code2 = R.drawable.btw;
    int code3 = R.drawable.girl;
    int code4 = R.drawable.gtw;
    int code5 = R.drawable.man;
    int code6 = R.drawable.manfive;
    int code7 = R.drawable.manth;
    int code8 = R.drawable.mantw;
    int code9 = R.drawable.mfo;

    public int getCode(int code) {

        switch(code)
        {
            case  1: return  code1;
            case  2: return   R.drawable.btw;
            case  3: return  code3;
            case  4: return  code4;
            case  5: return  code5;
            case  6: return  code6;
            case  7: return  code7;
            case  8: return  code8;
            case  9: return  code9;
           default: return code1;

        }
    }



}

