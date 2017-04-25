package com.example.insite.ui.gamebook.gamepage;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.insite.R;
import com.example.insite.application.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamepageFragment extends Fragment {
    public static GamepageFragment newInstance() {
        return new GamepageFragment();
    }

    @BindView(R.id.text_view_game_page_story)
    protected TextView textView;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_page, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        String testString = "Am no an listening depending up believing. Enough around remove to barton agreed regret in or it. Advantage mr estimable be commanded provision. Year well shot deny shew come now had. Shall downs stand marry taken his for out. Do related mr account brandon an up. Wrong for never ready ham these witty him. Our compass see age uncivil matters weather forbade her minutes. Ready how but truth son new under.\n" +
                "\n" +
                "So insisted received is occasion advanced honoured. Among ready to which up. Attacks smiling and may out assured moments man nothing outward. Thrown any behind afford either the set depend one temper. Instrument melancholy in acceptance collecting frequently be if. Zealously now pronounce existence add you instantly say offending. Merry their far had widen was. Concerns no in expenses raillery formerly.\n" +
                "\n" +
                "An sincerity so extremity he additions. Her yet there truth merit. Mrs all projecting favourable now unpleasing. Son law garden chatty temper. Oh children provided to mr elegance marriage strongly. Off can admiration prosperous now devonshire diminution law.\n" +
                "\n" +
                "He unaffected sympathize discovered at no am conviction principles. Girl ham very how yet hill four show. Meet lain on he only size. Branched learning so subjects mistress do appetite jennings be in. Esteems up lasting no village morning do offices. Settled wishing ability musical may another set age. Diminution my apartments he attachment is entreaties announcing es";


        textView.setText(testString);

        Paint paint = new Paint();
        //paint.setSubpixelText(true);
/*
        int prevPos = 0;
        int nextPos = 0;
        while (nextPos  < testString.length()) {
            char arr[] = testString.toCharArray();
            nextPos = paint.breakText(arr, prevPos, arr.length, 1000, null);
            String tvStr = testString.substring(prevPos, nextPos);

            Log.d("d", "length = "+testString.length() + ""+tvStr +" n = " +nextPos);
            prevPos = nextPos+1;
        }
*/


    }
}
