package com.juliettegonzalez.breakthroughapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.juliettegonzalez.breakthroughapp.AI.Player;

/**
 * A placeholder fragment containing a simple view.
 */
public class PawnSelectionFragment extends Fragment {

    ImageButton mDragonPawnBtn, mGrandpaPawnBtn, mKingPawnBtn, mWizardPawnBtn;
    ImageButton mSelectedPawnBtn = null;
    public static Player.PawnType mSelectedPawn = null;

    public PawnSelectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_new_game).setVisible(false);
        menu.findItem(R.id.action_quit_game).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pawn_selection, container, false);


        mDragonPawnBtn = (ImageButton) view.findViewById(R.id.dragon_pawn_btn);
        mGrandpaPawnBtn = (ImageButton) view.findViewById(R.id.grandpa_pawn_btn);
        mKingPawnBtn = (ImageButton) view.findViewById(R.id.king_pawn_btn);
        mWizardPawnBtn = (ImageButton) view.findViewById(R.id.wizard_pawn_btn);

        final Button startGameBtn = (Button) view.findViewById(R.id.start_game_btn);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                BoardFragment boardFragment = BoardFragment.newInstance(mSelectedPawn);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, boardFragment)
                        .addToBackStack("pawnSelection")
                        .commit();
            }
        });

        mDragonPawnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPawnBtn != null) mSelectedPawnBtn.setBackgroundResource(R.drawable.white_rounded_button);
                mSelectedPawnBtn = mDragonPawnBtn;
                mSelectedPawn = Player.PawnType.DRAGON;
                mDragonPawnBtn.setBackgroundResource(R.drawable.white_rounded_button_selected);
                startGameBtn.setEnabled(true);
            }
        });

        mGrandpaPawnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPawnBtn != null) mSelectedPawnBtn.setBackgroundResource(R.drawable.white_rounded_button);
                mSelectedPawnBtn = mGrandpaPawnBtn;
                mSelectedPawn = Player.PawnType.GRANDPA;
                mGrandpaPawnBtn.setBackgroundResource(R.drawable.white_rounded_button_selected);
                startGameBtn.setEnabled(true);
            }
        });

        mKingPawnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPawnBtn != null) mSelectedPawnBtn.setBackgroundResource(R.drawable.white_rounded_button);
                mSelectedPawnBtn = mKingPawnBtn;
                mSelectedPawn = Player.PawnType.KING;
                mKingPawnBtn.setBackgroundResource(R.drawable.white_rounded_button_selected);
                startGameBtn.setEnabled(true);
            }
        });

        mWizardPawnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPawnBtn != null) mSelectedPawnBtn.setBackgroundResource(R.drawable.white_rounded_button);
                mSelectedPawnBtn = mWizardPawnBtn;
                mSelectedPawn = Player.PawnType.WIZARD;
                mWizardPawnBtn.setBackgroundResource(R.drawable.white_rounded_button_selected);
                startGameBtn.setEnabled(true);
            }
        });





        return view;
    }

}